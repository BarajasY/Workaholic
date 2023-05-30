import React, {useState} from 'react';
import { useQuery } from '@tanstack/react-query';
import './Browse.css'
import { useNavigate } from 'react-router-dom';
import { PostingInterface, PostingType, jobTypeType } from '../../types';

const Browse:React.FC = () => {
  const [SearchText, setSearchText] = useState("")
  const [Posts, setPosts] = useState<PostingInterface[]>([])

  const navigate = useNavigate()
    const { isLoading, error , data} = useQuery({
        queryKey: ["postingsData"],
        queryFn: () => fetch("http://localhost:8080/api/v1/postings/all")
          .then(res => res.json())
          .then(info => setPosts(info))
          .then(info => info)
    })
    if(isLoading) return <h1>Loading</h1>
    if(error) console.log(error);

    const findPostings = async () => {
      const get = await fetch(`http://localhost:8080/api/v1/postings/all/${SearchText}`);
      const json = await get.json();
      setPosts(json)
    }

  return (
    <div className="browseContainer">
      <div className="inputContainer">
        <input type="text" onChange={(e) => setSearchText(e.target.value)}/>
        <button onClick={() => findPostings()}>Search</button>
      </div>
      {Posts.length > 0 
      ? 
        <h1>{Posts.length} Jobs Found</h1>
      :
        <h1>No jobs Found</h1>
      }
        <div className="jobsContainer">
          {Posts.map((job:PostingInterface)=> (
            <div key={job.id} className="jobCard" onClick={() => navigate(`./${job.id}`)}>
              <div className="jobCardHeader">
                <h1 className='jobTitle'>{job.title}</h1>
                <p className='jobDate'>{job.date}</p>
              </div>
                <p className="companyName">{job.user.name}</p>
                <h1 className="jobSalary">${job.salary} <span>{job.currency.code}/{job.rate.rateName}</span></h1>
                <div className="jobTypeContainer">
                  {job.jobTypes.map((type:jobTypeType) => (
                    <h1 className='type' key={type.id}>{type.type}</h1>
                    ))}
                </div>
            </div>
          ))}
        </div>
    </div>
  )
}

export default Browse