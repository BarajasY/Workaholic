import React, {useState} from 'react';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import Cookies from 'universal-cookie';
import './Login.css'
import { storeWorker, storeCompany } from '../../redux/workerSlice';
import { companyInterface, userStateInterface, workerInterface } from '../../types';
import { storeUser } from '../../redux/userSlice';

const Login = () => {
  const [Email, setEmail] = useState("")
  const [Password, setPassword] = useState("")
  const [ErrorMessage, setErrorMessage] = useState("")
  const dispatch = useDispatch();
  const navigate = useNavigate()

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

/*   const storeWorkerLogin = (data: workerInterface) => {
    const cookies = new Cookies();
    cookies.set('Logged', true, {path: "/"})
    cookies.set('Email', data.email, {path: "/"});
    cookies.set('FName', data.fname, {path: "/"});
    cookies.set('LName', data.lname, {path: "/"});
    cookies.set('Country', data.country, {path: "/"});
    cookies.set("Role", data.role, {path: "/"});
    cookies.set('Tags', data.tags, {path: "/"});
    cookies.set('Id', data.id, {path: "/"});
    const cookieData = cookies.getAll();
    dispatch(storeWorker(cookieData))
    navigate("/browse")
  } */

  const storeUserLogin = (data:userStateInterface) => {
    const cookies = new Cookies();
    cookies.set('logged', true)
    cookies.set('id', data.id)
    cookies.set('name', data.name)
    cookies.set('email', data.email)
    cookies.set('password', data.password)
    cookies.set('country', data.country)
    cookies.set('role', data.role)
    const cookieData = cookies.getAll();
    dispatch(storeUser(cookieData))
    navigate("/browse")
  }
  
/*   const storeCompanyLogin = (data: companyInterface) => {
    const cookies = new Cookies();
    cookies.set('Logged', true, {path: "/"})
    cookies.set('CompanyName', data.name, {path: "/"})
    cookies.set('Password', data.password, {path: "/"})
    cookies.set('Email', data.email, {path: "/"})
    cookies.set('CompanyOwner', data.owner, {path: "/"})
    cookies.set('Country', data.country, {path: "/"})
    cookies.set('CompanyLocation', data.location, {path: "/"})
    cookies.set("Role", data.role, {path: "/"})
    cookies.set("Tags", data.tags, {path: "/"})
    cookies.set("Id", data.id, {path: "/"})
    const cookieData = cookies.getAll();
    dispatch(storeCompany(cookieData))
    navigate("/browse")
  } */

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