import { useQuery } from '@tanstack/react-query';
import React from 'react';
import './Browse.css'
import { useNavigate } from 'react-router-dom';
import { jobTypeType } from '../../types';

const Browse:React.FC = () => {
  const navigate = useNavigate()
    const { isLoading, error , data} = useQuery({
        queryKey: ["postingsData"],
        queryFn: () => fetch("http://localhost:8080/api/v1/postings/all")
          .then(res => res.json())
    })
    if(isLoading) return <h1>Loading</h1>
    if(error) console.log(error);

  return (
    <div className="browseContainer">
      {data.length > 0 
      ? 
        <h1>{data.length} Jobs Found</h1>
      :
        <h1>No jobs Found</h1>
      }
        <div className="jobsContainer">
          {data.map((job:any)=> (
            <div key={job.id} className="jobCard" onClick={() => navigate(`./${job.id}`)}>
              <div className="jobCardHeader">
                <h1 className='jobTitle'>{job.title}</h1>
                <p className='jobDate'>{job.date}</p>
              </div>
                <p className="companyName">{job.user.name}</p>
                <h1 className="jobSalary">${job.salary} <span>{job.currency.code} a {job.rate.ratename}</span></h1>
                <div className="jobTypeContainer">
                  {job.jobTypes.map((type:jobTypeType) => (
                    <h1 className='type' key={type.id}>{type.type}</h1>
                    ))}
                </div>
                <h1 className="jobLocation">{job.location}</h1>
            </div>
          ))}
        </div>
    </div>
  )
}

export default Browse