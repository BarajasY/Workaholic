import { useQuery } from '@tanstack/react-query';
import React from 'react';
import './Browse.css'

const Browse = () => {
    const { isLoading, error , data} = useQuery({
        queryKey: ["postingsData"],
        queryFn: () => fetch("http://localhost:8080/api/v1/postings/all").then(res => res.json())
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
                <h1>{job.title}</h1>
              </div>
            </div>
          ))}
        </div>
    </div>
  )
}

export default Browse