import { useQuery } from '@tanstack/react-query';
import React from 'react';
import './Browse.css'

const Browse = () => {
    const { isLoading, error , data} = useQuery({
        queryKey: ["postingsData"],
        queryFn: () => fetch("http://localhost:8080/api/v1/postings/all")
          .then(res => res.json())
    })
    if(isLoading) return <h1>Loading</h1>
    if(error) console.log(error);

    console.log(data)

  return (
    <div className="browseContainer">
        <h1>Jobs Found</h1>
        <div className="jobsContainer">
          {data.map((job:any)=> (
            <div key={job.id} className="jobCard">
              <div className="jobCardHeader">
                <h1 className='jobTitle'>{job.title}</h1>
                <p className='jobDate'>{job.date}</p>
              </div>
                <p className="companyName">{job.businessName}</p>
                <h1 className="jobSalary">${job.salary} <span>{job.salaryCurrency} a {job.salaryRate}</span></h1>
                <div className="jobTypeContainer">
                  {job.jobType.map((type:string, i:number) => (
                    <h1 className='type' key={i}>{type}</h1>
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