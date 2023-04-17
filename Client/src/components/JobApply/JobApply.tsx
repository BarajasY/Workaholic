import React from "react";
import "./JobApply.css";
import { useSelector } from "react-redux";
import { userType } from "../../types";
import { useQuery } from "@tanstack/react-query";

const JobApply = () => {
  const user = useSelector((state: userType) => state.worker);
  const email = user.Email;

  const { isLoading, error, data } = useQuery({
    queryKey: ["getFile", email],
    queryFn: async ({ queryKey }) => {
      const data = await fetch(
        `http://localhost:8080/api/v1/upload/${queryKey[1]}`
      );
      const blob = await data.blob();
      const pdfurl = URL.createObjectURL(blob);
      return pdfurl;
    },
  });

  return (
    <div className="jobApplyContainer">
      <div className="jobApplyContent">
        <h1>Usted aplicará al puesto con la siguiente información</h1>
        <div className="jobApplyNames">
          <section>
            <h1>
              <span>Name: </span>{user.FName} {user.LName}
            </h1>
          </section>
        </div>
        <div className="jobApplyEmail">
          <h1>
            <span>Email: </span>{user.Email}
          </h1>
        </div>
        <div className="jobApplyResume">
          <h1>Resume</h1>
          <iframe src={data} width="500px" height="500px"></iframe>
        </div>
        <div className="jobApplyButton">
          <button>Aplicar</button>
        </div>
      </div>
    </div>
  );
};

export default JobApply;
{
  /*           <a download={data} href={data}>wasd</a> */
}
