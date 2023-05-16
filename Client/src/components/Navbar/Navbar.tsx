import React, {useEffect, useState} from 'react'
import {CgProfile} from 'react-icons/cg';
import {useDispatch, useSelector } from 'react-redux';
import {Link} from 'react-router-dom'
import './Navbar.css';
import { userType} from '../../types';
import Cookies from 'universal-cookie';
import { AiOutlineSetting } from 'react-icons/ai';
import { storeUser } from '../../redux/userSlice';

const Navbar = () => {
    const cookies = new Cookies();
    const allCokies = cookies.getAll();
    const dispatch = useDispatch();
    dispatch(storeUser(allCokies));
    const User = useSelector((state:userType) => state.user);
    
  return (
    <div className="navbarContainer">
        <div className="logo">
            <Link to={User.logged ? "/browse" : "/home"}>Workaholic</Link>
        </div>
        <div className="links">
          {User.logged
          ?
          <>
            {User?.role.name === "company" 
              ? 
              <Link to="./add" id="add">Add job</Link>
              :
              null
            }
            {User?.role.name === "admin" 
             ?
             <Link to="/settings"><AiOutlineSetting /></Link>
             :
             null
             }
            <Link to="./browse" id="browse">Browse</Link>
            <Link to="./profile" id="profile"><CgProfile/></Link>
          </>
          :
          <>
            <Link to="./login" id="login">Login</Link>
            <Link to="./signup" id="signup">Signup</Link>
          </>
          }
        </div>
    </div>
  )
}

export default Navbar