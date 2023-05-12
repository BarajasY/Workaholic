import React, { useState } from "react";
import "./JobApply.css";
import { useSelector } from "react-redux";
import { userType } from "../../types";
import { useQuery } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";

const JobApply = () => {
  const navigate = useNavigate();
  const [ApplicationSent, setApplicationSent] = useState(false)
  const [CoverLetter, setCoverLetter] = useState("")
  const [ErrorMessage, setErrorMessage] = useState("")

  const user = useSelector((state: userType) => state.user);
  const posting = useSelector((state: any) => state.posting);
  const email = user.email;

  if(user.role.name !== "worker") {
    navigate("/home")
  }

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

  const verifyApplication = async() => {
    const get = await fetch(`http://localhost:8080/api/v1/jobapplication/verify/${user.id}/${posting.id}`)
    if(get.status === 409) {
      setErrorMessage("Ya aplicaste a este puesto.")
      window.scrollTo({top: 0, behavior: "smooth"})
    } else if (get.status === 200) {
      sendApplication()
    }
  }

  const sendApplication = async () => {
    const post = await fetch("http://localhost:8080/api/v1/jobapplication/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        worker_id: user.id,
        company_id: posting.company.id,
        posting_id: posting.id,
        coverLetter: CoverLetter
      })
    })
    if(post.status === 200) {
      setApplicationSent(true)
      window.scrollTo({top: 0, behavior:"smooth"})
      setTimeout(() => {
        navigate("/browse")
      }, 1500)
    }
  }

  if(ApplicationSent) {
    return (
      <div className="jobApplyCompleted">
        <h1>Postulación enviada!</h1>
      </div>
    )
  }

  return (
    <div className="jobApplyContainer">
      <div className="jobApplyContent">
        <h1>Usted aplicará al puesto <span>{posting.title}</span> con la siguiente información</h1>
        <p>{ErrorMessage}</p>
        <div className="jobApplyNames">
          <section>
            <h1>
              <span>Name: </span>{user.name}
            </h1>
          </section>
        </div>
        <div className="jobApplyEmail">
          <h1>
            <span>Email: </span>{user.email}
          </h1>
        </div>
        <div className="jobApplyResume">
          <h1>Resume</h1>
          <iframe src={data} width="500px" height="500px"></iframe>
        </div>
        <div className="jobApplyLetter">
          <h1>Escribir carta de presentación <span>(Recomendado)</span> </h1>
          <textarea cols={70} rows={20} onChange={(e) => setCoverLetter(e.target.value)} placeholder="Carta de presentación"></textarea>
        </div>
        <div className="jobApplyButton">
          <button onClick={() => verifyApplication()}>Aplicar</button>
        </div>
        {ApplicationSent && 
          <div>
            <h1>Hecho</h1>
          </div>
        }
      </div>
    </div>
  );
};

export default JobApply;
{
  /*           <a download={data} href={data}>wasd</a> */
}
