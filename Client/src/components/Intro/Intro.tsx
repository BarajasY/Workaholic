import { motion, AnimatePresence } from 'framer-motion';
import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './Intro.css';

const Intro = () => {
    const [FadeLetters, setFadeLetters] = useState(false)
    const navigate = useNavigate()

    useEffect(() => {
      setTimeout(() => {
        setFadeLetters(true)
      }, 1000)
      setTimeout(() => {
        navigate('/home')
      }, 1500)
    }, [])
    

  return (
    <div className="introContainer">
        <h1>
            <AnimatePresence>
                {!FadeLetters && (
                <>
                    <motion.span
                        initial={{opacity: 0, y: -50}}
                        whileInView={{opacity: 1, y: 0}}
                        exit={{opacity: 0, y: -50}}
                        key="modal">F</motion.span>
                    <motion.span
                        initial={{opacity: 0, y: 50}}
                        whileInView={{opacity: 1, y: 0}}
                        exit={{opacity: 0, y: 50}}
                        key="modal">I</motion.span>
                    <motion.span
                        initial={{opacity: 0, y: -50}}
                        whileInView={{opacity: 1, y: 0}}
                        exit={{opacity: 0, y: -50}}
                        key="modal">M</motion.span>
                    <motion.span
                        initial={{opacity: 0, y: 50}}
                        whileInView={{opacity: 1, y: 0}}
                        exit={{opacity: 0, y: 50}}
                        key="modal">E</motion.span>
                </>
                )}
            </AnimatePresence>
        </h1>
    </div>
  )
}

export default Intro