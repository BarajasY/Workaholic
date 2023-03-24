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
            <Route path="/" element={<Index.Home/>} />
          </Routes>
          <Index.Footer/>
      </BrowserRouter>
    </div>
  )
}

export default App
