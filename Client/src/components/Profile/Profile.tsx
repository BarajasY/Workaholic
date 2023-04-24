import React, { useEffect } from 'react';
import './Profile.css';
import Cookies from 'universal-cookie';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { resetUser } from '../../redux/workerSlice';
import { motion } from 'framer-motion';
import { userType } from '../../types';

const Profile = () => {
    const navigate = useNavigate();
    const User = useSelector((state: userType) => state.worker);
    const dispatch = useDispatch();
    const cookies = new Cookies();

    useEffect(() => {
        if(cookies.get("Logged") === "false" || !User.Logged) {
            navigate("/home");
        }
    }, [])

    const Logout = () => {
        cookies.remove("Logged");
        cookies.remove("Email");
        cookies.remove("FName");
        cookies.remove("LName");
        cookies.remove("Country");
        cookies.remove("Tags");
        cookies.remove("CompanyLocation");
        cookies.remove("CompanyName");
        cookies.remove("CompanyOwner");
        cookies.remove("Role");
        dispatch(resetUser())
        navigate("/home")
    }

    function renderColor(role: String) {
        switch (role) {
            case "admin":
                return "var(--golden)"
                break;
            case "worker":
                return "var(--teal)"
                break;
            case "company":
                return "var(--cobaltblue)"
            default:
                break;
        }
    }

  return (
    <div className="profileContainer">
        <div className="profileContent">

            <div className="profileSidebar">
            </div>
            {User.Role === "worker" 
                ?
                <div className="profileWorkerInformation">
                    <h1>{User.FName} {User.LName}</h1>
                    <p>{User.Email}</p>
                </div> 
                :
                null
            }
            <div className="profileRole">
                <motion.h1 
                style={{color:renderColor(User.Role), borderColor:renderColor(User.Role)}}
                >{User.Role}</motion.h1>
            </div>
        </div>
        <button onClick={() => Logout()}>Logout</button>
    </div>
  )
}

export default Profile