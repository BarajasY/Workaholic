import React, {useState, useEffect} from 'react';
import {AiOutlineDelete} from 'react-icons/ai'
import './AddPosting.css';
import { useSelector } from 'react-redux';
import { userType } from '../../types';
import { useNavigate } from 'react-router-dom';

const AddPosting = () => {
  const user = useSelector((state: userType) => state.worker)
  const navigate = useNavigate()

  const [Salary, setSalary] = useState('')
  const [SalaryRate, setSalaryRate] = useState("hr")
  const [SalaryCurrency, setSalaryCurrency] = useState("MXN")
  const [BenefitsArray, setBenefitsArray] = useState([""])
  const [PostingTitle, setPostingTitle] = useState("")
  const [PostingDescription, setPostingDescription] = useState("")
  const [IsRemote, setIsRemote] = useState(false)
  const [IsIndefinite, setIsIndefinite] = useState(false)
  const [JobTypeArray, setJobTypeArray] = useState([""])
  const [Location, setLocation] = useState("")
  const [Duration, setDuration] = useState("")
  const [Benefit, setBenefit] = useState("")
  const [PostingTags, setPostingTags] = useState<String[]>([])
  const [SuccessfullyPosted, setSuccessfullyPosted] = useState(false)
  const JobType = ["Full-Time", "Part-Time", "Freelance"]
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

  useEffect(() => {
    if(user.Role !== "company") {
      navigate('/home')
    }
  }, [])
  

  const EnlistBenefits = () => {
    const benefitChecker = Benefit === "" || Benefit === " "
    if(!benefitChecker) {
      if(BenefitsArray[0] === "") {
        setBenefitsArray([Benefit]);
        setBenefit("");
      } else {
        setBenefitsArray([...BenefitsArray, Benefit])
        setBenefit(""); 
      }
    }
  }

  const AddPostingTag = (tag:string) => {
    const TagAlreadyIn = PostingTags.includes(tag);
    if (TagAlreadyIn) {
      // remove tag from the list.
      setPostingTags(PostingTags.filter((t) => t!== tag));
    } else {
      // adad tag to the list.
      setPostingTags([...PostingTags, tag]);
      }
  }

  const AddJobType = (type:string) => {
    if(JobTypeArray[0] === "" || JobTypeArray[0] === null) {
      setJobTypeArray([type])
    } else {
      const AlreadyIn = JobTypeArray.includes(type)
      if(AlreadyIn) {
        setJobTypeArray(JobTypeArray.filter(t => t!= type))
      } else {
        setJobTypeArray([...JobTypeArray, type])
      }
    }
  }

  const IsRemoteFunction = () => {
    setIsRemote(!IsRemote)
      setLocation("Remote")
  }
  const IsIndefiniteFunction = () => {
    setIsIndefinite(!IsIndefinite)
    setDuration("Indefinite")
  }

  const AddPosting = () => {
    const date = new Date()
    const post = fetch("http://localhost:8080/api/v1/postings/add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        businessName: user.CompanyName,
        title: PostingTitle,
        description: PostingDescription,
        jobType: JobTypeArray,
        salary: Number(Salary),
        salaryCurrency: SalaryCurrency,
        salaryRate: SalaryRate,
        location: Location,
        country: user.Country,
        duration: Duration,
        date: `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`,
        tags: PostingTags,
        benefits: BenefitsArray,
        company_id: user.Id
      })
    }).then(response => {
      if(response.status === 200) {
        setSuccessfullyPosted(true)
        window.scrollTo({top: 0, behavior: "smooth"})
        setTimeout(() => {
          navigate("/home")
        }, 1500)
      }
    })
  }

  if(SuccessfullyPosted) {
    return (
      <div className="SuccessfullyPostedContainer">
        <h1>Se ha subido tu oferta de trabajo!</h1>
      </div>
    )
  }

  return (
    <div className="addPostingContainer">
        <div className="addPostingForm">
          <section>
            <h1>Título del puesto</h1>
            <input type="text" onChange={(e) => setPostingTitle(e.target.value)}/>
          </section>
          <section>
            <h1>Descripción a detalle del puesto</h1>
            <textarea name="" id="" cols={50} rows={20} onChange={(e) => setPostingDescription(e.target.value)}></textarea>
          </section>
          <section>
            <h1>Salario ofrecido</h1>
            <article className="PostingSalary">
              <input
                type="number"
                pattern="[0-9]*"
                value={Salary}
                onChange={(e) =>
                  setSalary((v) => (e.target.validity.valid ? e.target.value : v))
                }
              />
              <select name="SalaryRate" value={SalaryRate} onChange={(e) => setSalaryRate(e.target.value)}>
                <option value="hr">/hr</option>
                <option value="day">/day</option>
                <option value="month">/month</option>
                <option value="year">/year</option>
              </select>
              <select name="SalaryCurrency" value={SalaryCurrency} onChange={(e) => setSalaryCurrency(e.target.value)}>
                <option value="MXN">$MXN</option>
                <option value="USD">$USD</option>
                <option value="CAD">$CAD</option>
                <option value="ARG">$ARG</option>
              </select>
              </article>
          </section>
          <section>
            <h1>Tipo de puesto</h1>
            <article className='jobType'>
              {JobType.map((item, index) => {
                return <p key={index} className={JobTypeArray.includes(item) ? "JobTypeSelected" : ""} onClick={() => AddJobType(item)}>{item}</p>
              })}
            </article>
          </section>
          <section>
            <article className="PostingAddress">
              <h1>Dirección completa</h1>
              <p>Remoto</p>
              <input type="checkbox" className='AddressCheckbox' onClick={() => IsRemoteFunction()}/>
            </article>
            <input type="text" 
            placeholder='Municipio, Estado, Colonia, Calle, Numero'
            className={IsRemote ? "Unchangeable" : ""}
            value={Location}
            readOnly = {IsRemote}
            onChange={(e) => setLocation(e.target.value)}/>
          </section>
          <section>
            <article className='PostingDuration'>
              <h1>Duración</h1>
              <p>Indefinido </p>
              <input type="checkbox" onClick={() => IsIndefiniteFunction()}/>
            </article>
            <article className='PostingDurationMonths'>
              <input type="number"
              value={Duration}
              className={IsIndefinite ? "Unchangeable" : ""}
              readOnly={IsIndefinite}
              onChange={(e) => setDuration(e.target.value)}/>
              <h1>Meses</h1>
            </article>
          </section>
          <section>
            <h1>Beneficios</h1>
            <article className="PostingBenefits">
              {/*On Press "Enter" EnlistBenefits function will trigger.
                EnlistBenefits function adds Benefit to an array. */}
              <input type="text" value={Benefit} onKeyDown={(e) => e.key === "Enter" ? EnlistBenefits() : null} onChange={(e) => setBenefit(e.target.value)}/>
              <button onClick={() => EnlistBenefits()}>Enlistar</button>
            </article>
            <ul>
              {BenefitsArray.map((Benefit, i) => (
                <li key={i}>{Benefit}<AiOutlineDelete className='PostingIcon'
                //Uses .filter to delete the desired benefit from the list.
                onClick={() => setBenefitsArray(BenefitsArray.filter(item => item !== Benefit))}/>
                </li>
              ))}
            </ul>
          </section>
          <section>
            <h1>Seleccione la categoría relacionada con el puesto:</h1>
            <div className="PostingTagsContainer">
            {tags.map((tag, i) => (
              <p key={i} className={PostingTags.includes(tag) ? "PostingTag Selected" : 'PostingTag'} onClick={() => AddPostingTag(tag)}>{tag}</p>
              ))}
            </div>
          </section>
          <section className='PostingSubmitContainer'>
            <button onClick={() => AddPosting()}>Subir</button>
          </section>
        </div>
    </div>
  )
}

export default AddPosting