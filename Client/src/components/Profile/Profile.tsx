import React, { useEffect } from 'react';
import './Profile.css';
import Cookies from 'universal-cookie';
import { useNavigate } from 'react-router-dom';

const Profile = () => {
    const navigate = useNavigate();
    const cookies = new Cookies();

    useEffect(() => {
        if(cookies.get("Logged") === "false") {
            navigate("/home");
        }
        console.log(cookies.getAll())
    }, [])
    


    const Logout = () => {
        cookies.remove("Logged");
        cookies.remove("Email");
        cookies.remove("FName");
        cookies.remove("LName");
        cookies.remove("Country");
        cookies.remove("Tags");
        window.location.reload()
    }

  return (
    <div className="profileContainer">
        <button onClick={() => Logout()}>Logout</button>
    </div>
  )
}

export default Profile