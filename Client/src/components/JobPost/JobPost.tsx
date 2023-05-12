import React, {useState} from 'react';
import './JobPost.css';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { PostingType, jobTypeType, userType } from '../../types';
import { useDispatch, useSelector } from 'react-redux';
import { storePosting } from '../../redux/postingSlice';

const JobPost = () => {
  const user = useSelector((state:userType) => state.user)
  const dispatch = useDispatch()
    const {id} = useParams();
    const navigate = useNavigate()
    
    const { isLoading, error, data} = useQuery({
      queryKey: [`jobPost`, id],
      queryFn: async ({queryKey}) => {
        const data = await fetch(`http://localhost:8080/api/v1/postings/${queryKey[1]}`)
        if(data.status === 404) {
          navigate("/browse")
        }
        return data.json()
      },
    })
    if(isLoading) return <h1>Loading...</h1>
    if(error) console.log(error);

    const storePostingData = () => {
      dispatch(storePosting(data))
    }

  return (
    <div className="jobPostContainer">
      <div className="jobPostHeader">
        <h1 className='jobPostTitle'>{data.title}</h1>
        <p className='jobPostCompany'>{data.user.name}</p>
        <div className="jobPostDateAndCountry">
          <p className="jobPostCountry">{data.country}</p>
          <p className="jobPostDate">{data.date}</p>
        </div>
        <div className="jobPostTypeContainer">
          {data.jobTypes.map((type: jobTypeType) => (
            <p className='jobPostType' key={type.id}>{type.type}</p>
          ))}
        </div>
        <p className='jobPostSalary'>$ {data.salary} {data.currency.code} per {data.rate.rateName}</p>
      </div>
      <div className="jobPostAbout">
        <section>
          <p>Descripción</p>
          <div className="jobPostDescription">
            <p>{data.description}</p>
          </div>
        </section>
        <p className='jobPostDuration'>Duración: {data.duration === 0 ? <span>Remoto</span> : <span>{data.duration} Meses</span> }</p>
        <ul className='jobPostBenefitsContainer'>
          <h1 className='jobPostBenefitsHeader'>Beneficios</h1>
          {data.benefits.split(',').map((benefit:string, i:number) => (
            <li className='jobPostBenefit' key={i}>{benefit}</li>
          ))}
        </ul>
      </div>
      {user.role.name === "worker" 
       ?
            <div className="jobPostApplyButton">
              <Link to="./apply" onClick={() => storePostingData()}>
                <button onClick={() => storePostingData()}>Apply</button>
              </Link>
            </div>
       :
            null
       }
    </div>
  )
}

export default JobPost