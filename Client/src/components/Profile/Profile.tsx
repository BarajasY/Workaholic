import React, { useEffect, useState } from "react";
import "./Profile.css";
import Cookies from "universal-cookie";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { resetUser } from "../../redux/userSlice";
import { AnimatePresence, motion } from "framer-motion";
import { PostingInterface, PostingType, userType } from "../../types";
import { useQuery } from "@tanstack/react-query";
import { AiFillEdit, AiFillDelete, AiOutlineBars } from "react-icons/ai";
import { storePosting } from "../../redux/postingSlice";

const Profile = () => {
  const [Post, setPost] = useState<PostingInterface>()
  const [EditTitle, setEditTitle] = useState("")
  const [EditDescription, setEditDescription] = useState("")
  const [EditSalary, setEditSalary] = useState(0)
  const [EditRate, setEditRate] = useState("")
  const [EditCurrency, setEditCurrency] = useState("")
  const [EditDuration, setEditDuration] = useState(0)
  const [EditJobType, setEditJobType] = useState([""])
  const [EditActive, setEditActive] = useState(false)

  const jobType = ["Full-Time", "Part-Time", "Freelance"]
  
  const [MessageActive, setMessageActive] = useState(false)
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

  const deletePost = async(post:PostingInterface) => {
    const request = await fetch(`http://localhost:8080/api/v1/postings/delete/`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        post_id: post.id
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

  const editPost = async() => {
    const post = await fetch("http://localhost:8080/api/v1/postings/edit/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id: Post?.id,
        title: EditTitle,
        description: EditDescription,
        salary: EditSalary,
        rate: EditRate,
        currency: EditCurrency,
        duration: EditDuration,
        jobType: EditJobType
      })
    })
    if(post.status === 200) {
      setEditActive(false)
      navigate(0)
      DisappearingMessage("Post edited successfully")
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

  const EditFunction = (post:PostingInterface) => {
    setPost(post);
    setEditActive(true)
    setEditJobType(post.jobTypes.map(t => t.type))
    setEditTitle(post.title)
    setEditDescription(post.description)
    setEditSalary(post.salary)
    setEditRate(post.rate.rateName)
    setEditCurrency(post.currency.code)
    setEditDuration(post.duration)
  }

  const JobTypeFunction = (type:string) => {
    const JobTypeAlreadyIn = EditJobType.includes(type)
    if(JobTypeAlreadyIn) {
      setEditJobType(EditJobType.filter(t => t !== type))
    } else {
      setEditJobType([...EditJobType, type])
    }
  }

  const goToApplications = (post:PostingInterface) => {
    navigate(`./applications/${post.id}`)
    dispatch(storePosting(post))
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
            <section>
              <p>Title</p>
              <input 
              type="text" 
              placeholder={Post?.title}
              onChange={(e) => setEditTitle(e.target.value)}/>
            </section>
            <section className="editDescription">
              <p>Description</p>
              <textarea rows={4} placeholder={Post?.description} onChange={(e) => setEditDescription(e.target.value)}/>
            </section>
            <section id="editSalary">
              <p>Salary</p>
              <article>
                <input 
                  type="number" 
                  placeholder={Post?.salary.toString()}
                  onChange={(e) => setEditSalary(Number(e.target.value))}/>
                <select 
                name="EditSalaryRate" 
                onChange={(e) => setEditRate(e.target.value)} 
                value={EditRate}
                placeholder={Post?.rate.rateName}>
                  <option value="hr">/hr</option>
                  <option value="day">/day</option>
                  <option value="week">/week</option>
                  <option value="biweekly">/biweekly</option>
                  <option value="month">/month</option>
                  <option value="year">/year</option>
                </select>
                <select name="EditSalaryCurrency" 
                onChange={(e) => setEditCurrency(e.target.value)} 
                value={EditCurrency}
                placeholder={Post?.currency.code}>
                  <option value="MXN">$MXN</option>
                  <option value="USD">$USD</option>
                  <option value="CAD">$CAD</option>
                  <option value="GBP">$GBP</option>
                  <option value="EUR">$EUR</option>
                  <option value="ARS">$ARS</option>
                  <option value="CLP">$CLP</option>
                </select>
              </article>
            </section>
            <section id="editJobType">
              <p>Job Type</p>
              <article>
                {jobType.map((type, i) => (
                  <h1 
                  key={i} 
                  className={EditJobType.includes(type) ? "selectedJob" : ""}
                  onClick={() => JobTypeFunction(type)}>{type}</h1>
                  ))}
              </article>
            </section>
            <section id="editDuration">
              <p>Duration <span>(0 months = Indefinite)</span></p>
              <input 
              type="number" 
              placeholder={Post?.duration.toString()}
              onChange={(e) => setEditDuration(Number(e.target.value))}/> Months
            </section>
            <div className="editButtons">
              <button id="finishButton" onClick={() => editPost()}>Finish</button>
              <button 
              id="cancelButton"
              onClick={() => setEditActive(false)}>Cancel</button>
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
                    {data.map((post:PostingInterface) => (
                        <div className="profileCompanyPost" key={post.id}>
                            <h1 onClick={() => navigate(`/browse/${post.id}`)}>{post.title}</h1>
                            <h1>{post.salary} {post.currency.code} /{post.rate.rateName}</h1>
                            <h1>{post.date}</h1>
                            <div className="postIcons">
                              <AiFillDelete className="icon" onClick={() => deletePost(post)}/>
                              <AiFillEdit className="icon" onClick={() => EditFunction(post)}/>
                              <AiOutlineBars className="icon" onClick={() => goToApplications(post)}/>
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
