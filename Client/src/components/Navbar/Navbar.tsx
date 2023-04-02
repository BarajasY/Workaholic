import React from 'react'
import {useSelector } from 'react-redux';
import {Link} from 'react-router-dom'
import './Navbar.css';
import { workerStateInterface } from '../../types';

const Navbar = () => {
/*   const worker = useSelector((state: workerStateInterface) => state.worker); */
  const Logged = useSelector((state:any) => state.worker);
  console.log(Logged);

  return (
    <div className="navbarContainer">
        <div className="logo">
            <Link to="/home">Workaholic</Link>
        </div>
        <div className="links">
          {Logged === 'true'
          ?
          <>
            <h1>Hola</h1>
          </>
          :
          <>
            <Link to="./Login" id="login">Login</Link>
            <Link to="./Signup" id="signup">Signup</Link>
          </>
          }
        </div>
    </div>
  )
}

export default Navbar