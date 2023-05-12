import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Signup.css";
import { CountryType, tagType } from "../../types";

const Signup = () => {
  const [Name, setName] = useState("");
  const [Email, setEmail] = useState("");
  const [Password, setPassword] = useState("");
  const [ListOfCountries, setListOfCountries] = useState<CountryType[]>([])
  const [Country, setCountry] = useState("");
  const [CompanySignupForm, setCompanySignupForm] = useState(true)
  const [CV, setCV] = useState<File>();
  const [Role, setRole] = useState("worker")
  const formData = new FormData();
  const navigate = useNavigate();
  const [ErrorMessage, setErrorMessage] = useState("");
  const [CompleteSignup, setCompleteSignup] = useState(false);

  const SubmitSignup = () => {
    // if any input doesn't have a value, show error message.
    if (
      Name === "" ||
      Email === "" ||
      Password === "" ||
      Country === ""
    ) {
      window.scrollTo({ top: 0, behavior: "smooth" });
      setErrorMessage("Porfavor rellene todos los espacios");
    } else {
      //Remove error message
      //Make calls to the API with user data.
      setErrorMessage("");
      Role === "company" ? SendUserData() : SendUserCV()
    }
  };

  const SendUserData = async () => {
    //Sends all data except the PDF file to the endpoint.
    const Post = await fetch("http://localhost:8080/api/v1/user/register", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        "name": Name,
        "email": Email,
        "password": Password,
        "country": Country,
        "role": Role,
/*         "tags": userTags */
      })
    })
    .then(response => {
      console.clear()
      // If response is 401, send an error message.
      if(response.status === 401) {
        window.scrollTo({top: 0, behavior: 'smooth'})
        setErrorMessage("An account with that email already exists.")
      } else {
        window.scrollTo({ top: 0, behavior: "smooth" });
        setCompleteSignup(true);
        setTimeout(() => {
          navigate("/login")
        }, 1500);
      }
    })
  }

  //Sends the user pdf file to a different api endpoint.
  const SendUserCV = async () => {
    formData.append("file", CV!);
    formData.append("email", Email);
    const Post = await fetch("http://localhost:8080/api/v1/upload", {
      method: "POST",
/*              headers: {
        "Content-Type": "multipart/form-data",
      }, */
     body: formData,
    }).then((response) => {
      console.clear();
      // If response is 200 OK, complete signup.
      if (response.status === 200) {
        setErrorMessage("");
          SendUserData()
      }
    })
    .catch(error => {
      console.log(error);
    });
  };

  const getCountries = async() => {
    const data = await fetch("http://localhost:8080/api/v1/country")
    const json = await data.json()
    setListOfCountries(json);
  }

  // If CompleteSignup variable is true, the next UI will be shown.
  if (CompleteSignup) {
    return (
      <div className="completeSignup">
        <section>
          <h1>Signup has been completed!</h1>
          <h1>
            You may now head to <Link to="/login">Login</Link>
          </h1>
        </section>
      </div>
    );
  }

  // Normal signup UI.
  return (
    <div className="signupContainer">
      <h1 id="signupHeader">Signup to Workaholic</h1>
      <div className="signupContent">
        {CompanySignupForm 
        ? 
        <section className="isCompanySignup">
          <h1>¿Buscas registrar un negocio?</h1>
          <button id="yes" className={Role==="company" ? "CompanyActive" : ""} onClick={() => setRole("company")}>Sí</button>
          <button id="no" onClick={() => {setCompanySignupForm(false), setRole("worker")}}>No</button>
        </section>
        :
        null
        }
        <h1 id="signupError">{ErrorMessage}</h1>
        <section>
          <h1>Nombre</h1>
          <input type="text" onChange={(e) => setName(e.target.value)} />
        </section>
        <section>
          <h1>Correo</h1>
          <input type="text" onChange={(e) => setEmail(e.target.value)} />
        </section>
        <section>
          <h1>Contraseña</h1>
          <input
            type="password"
            onChange={(e) => setPassword(e.target.value)}
          />
        </section>
        <section className="signupCountry">
          <h1>País</h1>
          <input
            type="text"
            value={Country}
            readOnly
            onClick={() => getCountries()}
            onChange={(e) => setCountry(e.target.value.toLowerCase())}
            />
            {ListOfCountries.length !== 0 && 
          <article>
            {ListOfCountries.map((country) => (
              <h1 
              key={country.id}
              onClick={() => setCountry(country.name)}>{country.name}</h1>
              ))}
          </article>
            }
        </section>
        {Role === "worker" 
        ? 
        <section>
          <h1>Inserte su más reciente currículum</h1>
          <input type="file" accept=".pdf" onChange={(e) => setCV(e.target.files![0])} />
        </section>
        :
        null
        }
        <section className="signupSubmit">
          <button onClick={() => SubmitSignup()}>Enviar</button>
        </section>
      </div>
    </div>
  );
};

export default Signup;
