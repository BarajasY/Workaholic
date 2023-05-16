import React, { useEffect } from 'react'
import {Link, useNavigate} from 'react-router-dom'
import './Home.css'
import { useSelector } from 'react-redux';
import { userType } from '../../types';

const Home = () => {
  const navigate = useNavigate();
  const user = useSelector((state:userType) => state.user)

/*   useEffect(() => {
    if(user.logged) {
      navigate('/browse')
    }
  }, []) */
  

  return (
    <div className="homeContainer">
      <p>Presenting</p>
        <h1>
            <span>W</span>
            <span>o</span>
            <span>r</span>
            <span>k</span>
            <span>a</span>
            <span>h</span>
            <span>o</span>
            <span>l</span>
            <span>i</span>
            <span>c</span>
        </h1>
        <div className="homeButtons">
            <h1>Your place to look out for 
              <Link to="/signup">Jobs</Link></h1>
        </div>
    </div>
  )
}

export default Home