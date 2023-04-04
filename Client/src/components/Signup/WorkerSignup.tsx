import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Signup.css";

const Signup = () => {
  const [FName, setFName] = useState("");
  const [LName, setLName] = useState("");
  const [Email, setEmail] = useState("");
  const [Password, setPassword] = useState("");
  const [Country, setCountry] = useState("");
  const [userTags, setUserTags] = useState<String[]>([]);
  const [CompanySignupForm, setCompanySignupForm] = useState(true)
  const [CV, setCV] = useState<File>();
  const formData = new FormData();
  const navigate = useNavigate();
  const [ErrorMessage, setErrorMessage] = useState("");
  const [CompleteSignup, setCompleteSignup] = useState(false);
  const tags = [
    "Software",
    "Medicina",
    "Limpieza",
    "Ciberseguridad",
    "Investigación",
    "Construcción",
    "Atención al cliente",
    "Docente",
  ];

  const AddTag = (tag: string) => {
    const TagAlreadyIn = userTags.includes(tag);
    if (TagAlreadyIn) {
      // remove tag from the list.
      setUserTags(userTags.filter((t) => t !== tag));
    } else {
      // adad tag to the list.
      setUserTags([...userTags, tag]);
    }
  };

  const SubmitSignup = () => {
    // if any input doesn't have a value, show error message.
    if (
      FName === "" ||
      LName === "" ||
      Email === "" ||
      Password === "" ||
      Country === "" ||
      userTags[1] === undefined ||
      CV === undefined
    ) {
      window.scrollTo({ top: 0, behavior: "smooth" });
      setErrorMessage("Porfavor rellene todos los espacios");
    } else {
      //Remove error message
      //Make calls to the API with user data.
      setErrorMessage("");
      SendUserData();
    }
  };

  const SendUserData = async () => {
    //Sends all data except the PDF file to the endpoint.
    const Post = await fetch("http://localhost:8080/api/v1/worker/data", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        "FName": FName,
        "LName": LName,
        "Email": Email,
        "Password": Password,
        "Country": Country,
        "Tags": userTags.toString(),
      })
    })
    .then(response => {
      console.clear()
      // If response is 401, send an error message.
      if(response.status === 401) {
        window.scrollTo({top: 0, behavior: 'smooth'})
        setErrorMessage("An account with that email already exists.")
      } else {
        SendUserCV()
      }
    })
  }

  //Sends the user pdf file to a different api endpoint.
  const SendUserCV = async () => {
    formData.append("file", CV!);
    formData.append("email", Email);
    const Post = await fetch("http://localhost:8080/api/v1/upload", {
      method: "POST",
             headers: {
        "Content-Type": "multipart/form-data",
      },
     body: formData,
    }).then((response) => {
      console.clear();
      // If response is 200 OK, complete signup.
      if (response.status === 200) {
        setErrorMessage("");
        window.scrollTo({ top: 0, behavior: "smooth" });
        setCompleteSignup(true);
      }
    })
    .catch(error => {
      console.log(error);
    });
  };

  // If CompleteSignup variable is true, the next UI will be shown.
  if (CompleteSignup) {
    return (
      <div className="completeSignup">
        <section>
          <h1>Signup has been completed!</h1>
          <h1>
            You may now head to <Link to="./login">Login</Link>
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
          <button id="yes" onClick={() => navigate("./company")}>Sí</button>
          <button id="no" onClick={() => setCompanySignupForm(false)}>No</button>
        </section>
        :
        null
        }
        <h1 id="signupError">{ErrorMessage}</h1>
        <section>
          <h1>Nombre</h1>
          <input type="text" onChange={(e) => setFName(e.target.value)} />
        </section>
        <section>
          <h1>Apellidos</h1>
          <input type="text" onChange={(e) => setLName(e.target.value)} />
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
        <section>
          <h1>País</h1>
          <input
            type="text"
            onChange={(e) => setCountry(e.target.value.toLowerCase())}
          />
        </section>
        <section>
          <h1>Inserte su más reciente currículum</h1>
          <input type="file" onChange={(e) => setCV(e.target.files![0])} />
        </section>
        <h1>Seleccione sus intereses</h1>
        <section id="tagsSection">
          {tags.map((text, i) => (
            <h1
              key={i}
              className={
                userTags.includes(text) ? "signupTags selected" : "signupTags"}
              onClick={() => AddTag(text)}
            >
              {text}
            </h1>
          ))}
        </section>
        <section className="signupSubmit">
          <button onClick={() => SubmitSignup()}>Enviar</button>
        </section>
      </div>
    </div>
  );
};

export default Signup;
