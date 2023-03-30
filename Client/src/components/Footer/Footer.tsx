import React from 'react';
import {Link} from 'react-router-dom'
import uanl from '../../assets/uanl.png'
import fime from '../../assets/FIMELOGO.png'
import './Footer.css';

const Footer = () => {
  return (
    <div className="footerContainer">
      <div className="logos">
        <img src={uanl} alt="UANL" />
        <img src={fime} alt="FIME" />
        <Link to="/about">Sobre</Link>
      </div>
    </div>
  )
}

export default Footer