import React, { useEffect } from 'react';
import './Profile.css';
import Cookies from 'universal-cookie';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { resetUser } from '../../redux/workerSlice';
import { WorkerType, userType } from '../../types';

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

  return (
    <div className="profileContainer">
        <h1>Este perfil es de un(a){User.Role}</h1>
        <h1>Este es el perfil</h1>
        <button onClick={() => Logout()}>Logout</button>
    </div>
  )
}

export default Profile