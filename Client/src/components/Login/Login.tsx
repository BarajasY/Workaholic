import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import Cookies from 'universal-cookie';
import './Login.css'
import { userStateInterface} from '../../types';
import { storeUser } from '../../redux/userSlice';

const Login = () => {
  const [Email, setEmail] = useState("")
  const [Password, setPassword] = useState("")
  const [ErrorMessage, setErrorMessage] = useState("")
  const dispatch = useDispatch();
  const navigate = useNavigate()
  const cookies = new Cookies();
  
  const Login = () => {
    if(Email === "" && Password === "") {
      setErrorMessage("Rellene todos los espacios por favor")
    } else {
      SendLogin();
    }
  }

  const SendLogin = () => {
    const post = fetch("http://localhost:8080/api/v1/user/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: Email,
        password: Password
      })
    })
    .then(response => {
      if(response.status === 500) {
        console.clear()
        setErrorMessage("El correo no existe.")
      } else if(response.status === 409) {
        console.clear()
        setErrorMessage("La contraseña es incorrecta.")
      } else if(response.status === 200) {
        response.json().then(data => {
          storeUserLogin(data)
        })
      }
    }).catch(error => {
      console.log(error)
    })
  }

  const storeUserLogin = (data:userStateInterface) => {
    cookies.set('logged', true)
    cookies.set('id', data.id)
    cookies.set('name', data.name)
    cookies.set('email', data.email)
    cookies.set('country', data.country)
    cookies.set('role', data.role)
    const cookieData = cookies.getAll();
/*     dispatch(storeUser(cookieData)) */
    navigate("/browse")
    navigate(0)
  }

  return (
    <div className="loginContainer">
      <div className="loginContent">
        <h1>Entrar a <span>Workaholic</span></h1>
        <p style={{color: "var(--error)"}}>{ErrorMessage}</p>
        <p>Correo</p>
        <input type="text" onChange={(e) => setEmail(e.target.value)}/>
        <p>Contraseña</p>
        <input type="password" onKeyDown={(e) => e.key === "Enter" ? Login() : null} onChange={(e) => setPassword(e.target.value)}/>
      </div>
      <button className='loginSubmit' onClick={() => Login()}>Submit</button>
    </div>
  )
}

export default Login