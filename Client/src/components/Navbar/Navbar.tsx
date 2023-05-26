import React, {useEffect, useState} from 'react'
import {CgClose, CgList, CgProfile} from 'react-icons/cg';
import {useDispatch, useSelector } from 'react-redux';
import {Link, useNavigate} from 'react-router-dom'
import './Navbar.css';
import { userType} from '../../types';
import Cookies from 'universal-cookie';
import { AiOutlineMenu, AiOutlineMenuFold, AiOutlineSetting } from 'react-icons/ai';
import { storeUser } from '../../redux/userSlice';
import { useQuery } from '@tanstack/react-query';

const Navbar = () => {

  const [Menu, setMenu] = useState(false)

  const dispatch = useDispatch();
  const navigate = useNavigate()
  const cookies = new Cookies();
  const allCokies = cookies.getAll();
  dispatch(storeUser(allCokies));
  // Do not mind following console.logs, as they solve some bug that i really don't know how it works.
  // My theory is that console.log is updating allCokies variable so it works in the whole page.
  console.log(allCokies)
  console.clear()

  const responsiveNavigate = (route:string) => {
    setMenu(false)
    navigate(route)
  }

  return (
    <div className="navbarContainer">
        <div className="logo">
            <Link to={allCokies.logged ? "/home" : "/home"}>Workaholic</Link>
        </div>
        <div className="links">
          {allCokies.logged
          ?
          <>
            {allCokies?.role.name === "company" 
              ? 
              <Link to="./add" id="add">Add job</Link>
              :
              null
            }
            {allCokies?.role.name === "admin" 
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
          {Menu 
          ?
          <CgClose className="responsiveMenu" onClick={() => setMenu(!Menu)}/> 
          :
          <AiOutlineMenu className="responsiveMenu" onClick={() => setMenu(!Menu)}/>
          }
        </div>
        {Menu &&
        <div className="responsiveMenuContainer">
          {allCokies.logged
          ?
          <>
            {allCokies?.role.name === "company" 
              ? 
              <Link to="./add" id="add" onClick={() => responsiveNavigate("./add")}>Add job</Link>
              :
              null
            }
            {allCokies?.role.name === "admin" 
             ?
             <Link to="/settings" onClick={() => responsiveNavigate("./settings")}>Settings</Link>
             :
             null
            }
            <Link to="./browse" id="browse" onClick={() => responsiveNavigate("./browse")}>Browse</Link>
            <Link to="./profile" id="profile" onClick={() => responsiveNavigate("./profile")}>Profile</Link>
          </>
          :
          <>
            <Link to="./login" id="login" onClick={() => responsiveNavigate("./login")}>Login</Link>
            <Link to="./signup" id="signup" onClick={() => responsiveNavigate("./signup")}>Signup</Link>
          </>
          }
        </div>
        }
    </div>
  )
}

export default Navbar