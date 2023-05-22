import React, { useEffect } from 'react'
import {Link, useNavigate} from 'react-router-dom'
import './Home.css'
import { AiFillFilePdf } from 'react-icons/ai'

const Home = () => {
  return (
    <div className="homeContainer">
      <div className="homeContent1">
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
      <div className="homeContent2">
        <section>
          <h1><span>2</span> Simple Steps</h1>
        </section>
        <section>
          <h1>Prepare your <span>Resume</span></h1>
          <AiFillFilePdf className='homeIcon'/>
        </section>
        <section>
          <h1>And create an <span>account</span> with us!</h1>
        </section>
        <section>
          <h1>Then you're ready to begin your <span>job hunt</span>.</h1>
        </section>
      </div>
    </div>
  )
}

export default Home