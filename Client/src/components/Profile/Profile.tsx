import React, { useEffect, useState } from "react";
import "./Profile.css";
import Cookies from "universal-cookie";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { resetUser } from "../../redux/userSlice";
import { AnimatePresence, motion } from "framer-motion";
import { PostingType, userType } from "../../types";
import { useQuery } from "@tanstack/react-query";
import { AiFillEdit, AiFillDelete } from "react-icons/ai";

const Profile = () => {
  const [MessageActive, setMessageActive] = useState(false)
  const [EditActive, setEditActive] = useState(false)
  const [Message, setMessage] = useState("")
  const navigate = useNavigate();
  const User = useSelector((state: userType) => state.user);
  const dispatch = useDispatch();
  const cookies = new Cookies();

  useEffect(() => {
    if (cookies.get("Logged") === "false" || !User.logged) {
      navigate("/home");
    }
  }, []);

  const {isLoading, error, data} = useQuery({
    queryKey : ["companyPosts", User.id],
    queryFn: async ({queryKey}) => {
        const data = await fetch(`http://localhost:8080/api/v1/postings/user/${queryKey[1]}`)
        const json = await data.json();
        return json;
    },
  });

  const Logout = () => {
    cookies.remove("id");
    cookies.remove("name");
    cookies.remove("email");
    cookies.remove("country");
    cookies.remove("role");
    cookies.remove("logged");
    cookies.remove("password");
    dispatch(resetUser());
    navigate("/home");
  };

  function renderColor(role: String) {
    if(role === "admin") return "var(--golden)";
    if(role === "worker") return "var(--teal)";
    if(role === "company") return "var(--cobaltblue)";
  }

  const deletePost = async(post:PostingType) => {
    const request = await fetch(`http://localhost:8080/api/v1/postings/delete/`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        post_id: post.id,
        company: post.user
      })
    });
    if(request.status === 400) {
      DisappearingMessage("Bad petition")
    } else {
      navigate(0)
      setTimeout(() => {
        DisappearingMessage("Post eliminated")
      }, 2000)
    }
  }

  const DisappearingMessage = (message:string) => {
    setMessageActive(true)
    setMessage(message)
    setTimeout(() => {
      setMessageActive(false)
      setMessage("")
    }, 2000)
  }

  return (
    <div className="profileContainer">
      <AnimatePresence>
        {MessageActive && 
          <motion.h1 
          initial={{opacity: 0, y: 0}}
          animate={{opacity: 1, y: -100}}
          exit={{opacity: 0}}
          transition={{duration: 2}}
          className="disappearingMessage">{Message}</motion.h1>
        }
        {EditActive &&
        <>
          <motion.div 
          className="editShade"
          initial={{opacity: 0}}
          whileInView={{opacity: 1}}
          exit={{opacity: 0}}></motion.div>
          <motion.div 
          className="editContent"
          initial={{opacity: 0}}
          whileInView={{opacity: 1}}
          exit={{opacity: 0}}>
            <div className="editButtons">
              <button>Finish</button>
              <button onClick={() => setEditActive(false)}>Cancel</button>
            </div>
          </motion.div>
        </>
        }
      </AnimatePresence>
      <div className="profileContent">
        <div className="profileWorkerInformation">
          <div className="profileWorkerName">
            <h1 style={{ color: renderColor(User.role.name) }}>{User.name} </h1>
            <h1
              style={{
                color: renderColor(User.role.name),
                borderColor: renderColor(User.role.name),
              }}
            >
              {User.role.name}
            </h1>
          </div>
          <p style={{ color: renderColor(User.role.name) }}>{User.email}</p>
          <p>{User.country.name}</p>
        </div>
        {User.role.name === "company" && 
        <>
            {data === undefined 
            ? <h1 style={{marginTop: "10px"}}>No job postings were found</h1> 
            :
            <>
            <h1 style={{marginTop: "10px"}}>Posts Made</h1>
                <div className="profileCompanyData">
                    {data.map((post:PostingType) => (
                        <div className="profileCompanyPost">
                            <h1>{post.title}</h1>
                            <h1>{post.salary} {post.currency.code} /{post.rate.rateName}</h1>
                            <h1>{post.date}</h1>
                            <div className="postIcons">
                              <AiFillDelete className="icon" onClick={() => deletePost(post)}/>
                              <AiFillEdit className="icon" onClick={() => setEditActive(true)}/>
                            </div>
                        </div>
                    ))}
                </div>
            </>
            }
        </>
        }
      </div>
      <div className="profileLogout">
        <button onClick={() => Logout()}>Logout</button>
      </div>
    </div>
  );
};

export default Profile;
