import React, {useState} from 'react';
import {AiOutlineDelete} from 'react-icons/ai'
import './AddPosting.css';

const AddPosting = () => {
  const [Salary, setSalary] = useState('')
  const [SalaryRate, setSalaryRate] = useState("/hr")
  const [SalaryCurrency, setSalaryCurrency] = useState("")
  const [BenefitsArray, setBenefitsArray] = useState([""])
  const [PostingTitle, setPostingTitle] = useState("")
  const [PostingDescription, setPostingDescription] = useState("")
  const [IsRemote, setIsRemote] = useState(false)
  const [IsIndefinite, setIsIndefinite] = useState(false)
  const [JobTypeArray, setJobTypeArray] = useState([""])
  const [Location, setLocation] = useState("")
  const [Duration, setDuration] = useState("")
  const [Benefit, setBenefit] = useState("")
  const [PostingDate, setPostingDate] = useState("")
  const JobType = ["Full-Time", "Part-Time", "Freelance"]

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
            <button>Subir</button>
          </section>
        </div>
    </div>
  )
}

export default AddPosting