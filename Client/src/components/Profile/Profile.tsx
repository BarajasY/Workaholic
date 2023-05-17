import React, { useEffect } from "react";
import "./Profile.css";
import Cookies from "universal-cookie";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { resetUser } from "../../redux/userSlice";
import { motion } from "framer-motion";
import { PostingType, userType } from "../../types";
import { useQuery } from "@tanstack/react-query";

const Profile = () => {
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
    switch (role) {
      case "admin":
        return "var(--golden)";
        break;
      case "worker":
        return "var(--teal)";
        break;
      case "company":
        return "var(--cobaltblue)";
      default:
        break;
    }
  }

  return (
    <div className="profileContainer">
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
