import React, {useState} from 'react';
import {AiOutlineDelete} from 'react-icons/ai'
import './AddPosting.css';

const AddPosting = () => {
  const [val, setVal] = useState('')
  const [BenefitsArray, setBenefitsArray] = useState([""])
  const [Benefit, setBenefit] = useState("")
  const JobTypeArray = ["Full-Time", "Part-Time", "Freelance"]

  return (
    <div className="addPostingContainer">
        <div className="addPostingForm">
          <section>
            <h1>Título del puesto</h1>
            <input type="text" />
          </section>
          <section>
            <h1>Descripción a detalle del puesto</h1>
            <textarea name="" id="" cols={50} rows={20}></textarea>
          </section>
          <section>
            <h1>Salario ofrecido</h1>
            <article className="PostingSalary">
              <input
                type="number"
                pattern="[0-9]*"
                value={val}
                onChange={(e) =>
                  setVal((v) => (e.target.validity.valid ? e.target.value : v))
                }
              />
              <select name="" id="">
                <option value="hr">/hr</option>
                <option value="day">/day</option>
                <option value="month">/month</option>
                <option value="year">/year</option>
              </select>
              <select name="" id="">
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
              {JobTypeArray.map((item, index) => {
                return <p key={index}>{item}</p>
              })}
            </article>
          </section>
          <section>
            <article className="PostingAddress">
              <h1>Dirección completa</h1>
              <p>Remoto</p>
              <input type="checkbox" className='AddressCheckbox'/>
            </article>
            <input type="text" placeholder='Municipio, Estado, Colonia, Calle, Numero'/>
          </section>
          <section>
            <article className='PostingDuration'>
              <h1>Duración</h1>
              <p>Indefinido </p>
              <input type="checkbox" />
            </article>
            <article className='PostingDurationMonths'>
              <input type="number"/>
              <h1>Meses</h1>
            </article>
          </section>
          <section>
            <h1>Beneficios</h1>
            <article className="PostingBenefits">
              <input type="text" onChange={(e) => setBenefit(e.target.value)}/>
              <button onClick={() => BenefitsArray[0] === "" ? setBenefitsArray([Benefit]) : setBenefitsArray([...BenefitsArray, Benefit])}>Enlistar</button>
            </article>
            <ul>
              {BenefitsArray.map((Benefit, i) => (
                <li key={i}>{Benefit}<AiOutlineDelete className='PostingIcon'
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