import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import './Login.css'

const Login = () => {
  const [Email, setEmail] = useState("")
  const [Password, setPassword] = useState("")
  const [ErrorMessage, setErrorMessage] = useState("")
  const navigate = useNavigate()

  const Login = () => {
    if(Email === "" && Password === "") {
      setErrorMessage("Rellene todos los espacios por favor")
    } else {
      SendLogin();
    }
  }

  const SendLogin = () => {
    const post = fetch("http://localhost:8080/api/v1/worker/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        Email,
        Password
      })
    })
    .then(response => {
      if(response.status === 404) {
        console.clear()
        setErrorMessage("El correo no existe.")
      } else if(response.status === 409) {
        console.clear()
        setErrorMessage("La contraseña es incorrecta.")
      } else if(response.status === 200) {
        navigate("/home")
      }
    })
  }


  return (
    <div className="loginContainer">
      <div className="loginContent">
        <h1>Entrar a <span>Workaholic</span></h1>
        <p style={{color: "var(--error)"}}>{ErrorMessage}</p>
        <p>Correo</p>
        <input type="text" onChange={(e) => setEmail(e.target.value)}/>
        <p>Contraseña</p>
        <input type="password" onChange={(e) => setPassword(e.target.value)}/>
      </div>
      <button className='loginSubmit' onClick={() => Login()}>Submit</button>
    </div>
  )
}

export default Login