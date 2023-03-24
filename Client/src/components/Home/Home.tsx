import React from 'react'
import {Link} from 'react-router-dom'
import './Home.css'

const Home = () => {
  return (
    <div className="homeContainer">
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
            <Link to="/browse">Buscar empleo</Link>
        </div>
    </div>
  )
}

export default Home