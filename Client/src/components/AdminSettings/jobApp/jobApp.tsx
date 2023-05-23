import React from 'react'
import './jobApp.css';
import { useQuery } from '@tanstack/react-query';
import { jobApplicationInterface } from '../../../types';

const jobApp = () => {

  const {isLoading, error, data} = useQuery({
    queryKey: ["QueryAllApplications"],
    queryFn: async() => {
      const get = await fetch("http://localhost:8080/api/v1/jobapplication/all");
      return await get.json()
    }
  })
  if(isLoading) return <h1>Loading</h1>
  if(error) console.log(error)

  return (
    <div className="jobappContainer">
      <h1>List of Job Applications</h1>
      <div className="jobappTableColumns">
        <h1>UserId</h1>
        <h1>PostId</h1>
        <h1>User Name</h1>
        <h1>Company Name</h1>
      </div>
      {data.length > 0 
        ?
        <>
        {data.map((application:jobApplicationInterface) => (
          <div className="jobApplicationRecord">
            <h1>{application.user.id.toString()}</h1>
            <h1>{application.posting.id}</h1>
            <h1>{application.user.name}</h1>
            <h1>{application.posting.user.name}</h1>
          </div>
        ))}
        </>
       :
          <h1>No applications found</h1>
       }
    </div>
  )
}

export default jobApp