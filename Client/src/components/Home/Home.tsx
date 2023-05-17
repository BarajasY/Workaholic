import React, { useEffect } from 'react'
import {Link, useNavigate} from 'react-router-dom'
import './Home.css'

const Home = () => {
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
            <h1>Your place to look out for </h1>
            <Link to="/signup">Jobs</Link>
        </div>
    </div>
  )
}

export default Home