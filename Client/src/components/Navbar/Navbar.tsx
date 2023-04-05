import React, {useEffect} from 'react'
import {CgProfile} from 'react-icons/cg';
import {useDispatch, useSelector } from 'react-redux';
import {Link} from 'react-router-dom'
import './Navbar.css';
import { WorkerType, workerStateInterface } from '../../types';
import Cookies from 'universal-cookie';
import { storeWorker } from '../../redux/workerSlice';

const Navbar = () => {
    const cookies = new Cookies();
    const allCokies = cookies.getAll();
    const dispatch = useDispatch();
    dispatch(storeWorker(allCokies));
    const worker = useSelector((state: WorkerType) => state.worker);
  

  return (
    <div className="navbarContainer">
        <div className="logo">
            <Link to="/home">Workaholic</Link>
        </div>
        <div className="links">
          {worker.Logged === 'true'
          ?
          <>
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