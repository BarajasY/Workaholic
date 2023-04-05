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

  return (
    <div className="browseContainer">
        <h1>Here supposedly lies the jobs.</h1>
    </div>
  )
}

export default Browse