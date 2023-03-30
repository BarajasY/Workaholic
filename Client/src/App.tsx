import * as Index from './components/Index'
import './App.css'
import { BrowserRouter } from 'react-router-dom'
import { Route, Routes } from 'react-router'

function App() {

  return (
    <div className="App">
      <BrowserRouter>
          <Index.Navbar/>
          <Routes>
            <Route path="/" element={<Index.Intro/>} />
            <Route path="/home" element={<Index.Home/>} />
            <Route path="/signup" element={<Index.Signup/>} />
            <Route path="/login" element={<Index.Login/>} />
          </Routes>
          <Index.Footer/>
      </BrowserRouter>
    </div>
  )
}

export default App
