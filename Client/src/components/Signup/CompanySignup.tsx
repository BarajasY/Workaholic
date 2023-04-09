import React, {useState} from 'react';
import './CompanySignup.css';

const CompanySignup = () => {
  const [CompanyName, setCompanyName] = useState("")
  const [CompanyLocation, setCompanyLocation] = useState("")
  const [CompanyOwner, setCompanyOwner] = useState("")
  const [CompanyEmail, setCompanyEmail] = useState("")
  const [CompanyCountry, setCompanyCountry] = useState("")
  const [CompanyPassword, setCompanyPassword] = useState("")
  const [CompanyTags, setCompanyTags] = useState<String[]>([])
  const [ErrorMessage, setErrorMessage] = useState("")
  const [SuccessfullyRegistered, setSuccessfullyRegistered] = useState(false)
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

    const sendData = () => {
      if(CompanyName !== "" && CompanyLocation !== "" && CompanyCountry !== "" && CompanyPassword !== "" && CompanyTags[1] !== undefined && CompanyOwner !== "") {
        registerCompany();
      } else {
        setErrorMessage("Todos los campos son obligatorios");
      }
    }

    const registerCompany = () => {
      fetch("http://localhost:8080/api/v1/company/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          name: CompanyName,
          location: CompanyLocation,
          owner: CompanyOwner,
          email: CompanyEmail,
          country: CompanyCountry,
          password: CompanyPassword,
          role: "company",
          tags: CompanyTags.toString(),
        }),
      })
      .then((response) =>  {
        if(response.status === 409) {
          setErrorMessage("Ya existe una cuenta registrada con el correo colocado.")
          window.scrollTo({top: 0, behavior:"smooth"})
        } else if (response.status === 200) {
          setErrorMessage("")
          setSuccessfullyRegistered(true);
        }
      })
      .catch((err) => console.log(err))
    }

  const AddTag = (tag: string) => {
    const TagAlreadyIn = CompanyTags.includes(tag);
    if (TagAlreadyIn) {
      // remove tag from the list.
      setCompanyTags(CompanyTags.filter((t) => t !== tag));
    } else {
      // adad tag to the list.
      setCompanyTags([...CompanyTags, tag]);
    }
  };

  if(SuccessfullyRegistered) {
    return (
      <h1>Registrado!</h1>
      )
  }

  return (
    <div className="companySignupContainer">
      <h1>Registre un negocio a Workaholic</h1>
      <h1 style={{color: "var(--error)"}}>{ErrorMessage}</h1>
      <section>
        <h1>Nombre del negocio</h1>
        <input type="text" onChange={(e) => setCompanyName(e.target.value)}/>
      </section>
      <section>
        <h1>Nombre del dueño</h1>
        <input type="text" onChange={(e) => setCompanyOwner(e.target.value)}/>
      </section>
      <section>
        <h1>Correo</h1>
        <input type="text" onChange={(e) => setCompanyEmail(e.target.value)}/>
      </section>
      <section>
        <h1>Contraseña</h1>
        <input type="text" onChange={(e) => setCompanyPassword(e.target.value)}/>
      </section>
      <section>
        <h1>País</h1>
        <input type="text" onChange={(e) => setCompanyCountry(e.target.value)}/>
      </section>
      <section>
        <h1>Ubicación</h1>
        <input type="text" onChange={(e) => setCompanyLocation(e.target.value)}/>
      </section>
        <h1>Seleccione la(s) rama(s) de su negocio</h1>
      <section id="tags">
        {tags.map((tag, i) => (
          <h1 
            key={i}
            className={
              CompanyTags.includes(tag) ? "companyTags selected" : "companyTags"
            }
            onClick={() => AddTag(tag)}>{tag}</h1>
        ))}
      </section>
      <section className='companySignupSubmit'>
        <button onClick={() => sendData()}>Enviar</button>
      </section>
    </div>
  )
}

export default CompanySignup