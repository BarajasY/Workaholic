import React, { useState } from "react";
import "./JobApplications.css";
import { useQuery } from "@tanstack/react-query";
import { useParams } from "react-router-dom";
import { PostingType, jobApplicationInterface } from "../../types";
import { useSelector } from "react-redux";
import { AiFillFilePdf } from "react-icons/ai";
import { AnimatePresence, motion } from "framer-motion";

const JobApplications = () => {
  const id = useParams();
  const post = useSelector((state: PostingType) => state.posting);
  const [FileURL, setFileURL] = useState("");
  const [ShowCV, setShowCV] = useState(false);

  const { isLoading, error, data } = useQuery({
    queryKey: ["applicationsQuery", id.id],
    queryFn: async ({ queryKey }) => {
      const get = await fetch(
        `http://localhost:8080/api/v1/jobapplication/application/${queryKey[1]}`
      );
      const json = await get.json();
      return json;
    },
  });
  if (isLoading) return <h1>Loading</h1>;
  if (error) console.log(error);

  const showCv = async (email: string) => {
    const get = await fetch(`http://localhost:8080/api/v1/upload/${email}`);
    const blob = await get.blob();
    const pdfurl = URL.createObjectURL(blob);
    setFileURL(pdfurl);
    setShowCV(true);
  };

  return (
    <div className="applicationsContainer">
      <AnimatePresence>
        {ShowCV && (
          <>
            <motion.div
              initial={{ opacity: 0 }}
              whileInView={{ opacity: 1 }}
              exit={{ opacity: 0 }}
              className="applicationCV"
            >
              <motion.iframe
                initial={{ opacity: 0 }}
                whileInView={{ opacity: 1 }}
                exit={{ opacity: 0 }}
                src={FileURL}
                width="500px"
                height="600px"
              ></motion.iframe>
              <button
                className="closeCVButton"
                onClick={() => setShowCV(false)}
              >
                Close
              </button>
            </motion.div>
          </>
        )}
      </AnimatePresence>
      <div className="applicationsContent">
        <h1>
          Job applications for <span>{post.title}</span>
        </h1>
        <div className="applicationsColumns">
          <h1>Name</h1>
          <h1>Country</h1>
          <h1>Email</h1>
          <h1>CV</h1>
        </div>
        {data.length > 0 ? (
          <>
            {data.map((app: jobApplicationInterface) => (
              <div className="application">
                <h1>{app.user.name}</h1>
                <h1>{app.user.country.name}</h1>
                <h1>{app.user.email}</h1>
                <AiFillFilePdf
                  className="applicationIcon"
                  onClick={() => showCv(app.user.email)}
                />
              </div>
            ))}
          </>
        ) : (
          <h1>No applications found</h1>
        )}
      </div>
    </div>
  );
};

export default JobApplications;
