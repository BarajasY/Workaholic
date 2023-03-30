import React from 'react'
import {Link} from 'react-router-dom'
import './Navbar.css';

const Navbar = () => {
  return (
    <div className="navbarContainer">
        <div className="logo">
            <Link to="/home">Workaholic</Link>
        </div>
        <div className="links">
            <Link to="./Login" id="login">Login</Link>
            <Link to="./Signup" id="signup">Signup</Link>
        </div>
    </div>
  )
}

export default Navbar