import React, {useEffect} from 'react';
import './AdminSettings.css';
import { CgAdd } from 'react-icons/cg';
import { useNavigate } from 'react-router-dom';
import Cookies from 'universal-cookie';
import { useSelector } from 'react-redux';
import { userType } from '../../types';

const AdminSettings = () => {
    const navigate = useNavigate();
    const cookies = new Cookies();
    const User = useSelector((state:userType) => state.user)

    useEffect(() => {
      if (cookies.get("Logged") === "false" || !User.logged || User.role.name !== "admin") {
        navigate("/home");
      }
    }, [])
    

  return (
    <div className="adminContainer">
        <div className="adminContent">
            <h1>Admin Dashboard</h1>
            <button id='addAdmin'><CgAdd className='addIcon'/> Create admin</button>
            <div className="adminButtons">
                <button id='postings' onClick={() => navigate('./posts')}>Postings</button>
                <button id='user' onClick={() => navigate('./users')}>User</button>
                <button id='jobApp' onClick={() => navigate('./jobApp')}>Job Applications</button>
            </div>
        </div>
    </div>
  )
}

export default AdminSettings