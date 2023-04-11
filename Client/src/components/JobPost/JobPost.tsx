import React, {useState} from 'react';
import './JobPost.css';
import { useNavigate, useParams } from 'react-router-dom';
import { useQuery } from '@tanstack/react-query';
import { PostingType } from '../../types';

const JobPost = () => {
    const {id} = useParams();
    const navigate = useNavigate()
    const [Job, setJob] = useState<PostingType>()

    const { isLoading, error, data} = useQuery({
        queryKey: [`jobPost${id}`],
        queryFn: () => fetch(`http://localhost:8080/api/v1/postings/${id}`)
            .then(response => {
                if(response.status === 404) {
                    navigate("/browse")
                }
                setJob(response.json())})
    })
    if(isLoading) return <h1>Loading...</h1>
    if(error) console.log(error);

    const getJob = async() => {
        const data = await fetch(`http://localhost:8080/api/v1/postings/${id}`)
        const json = data.json()
        setJob(json)
    }

    console.log(data)
  return (
    <div className="jobPostContainer">
    </div>
  )
}

export default JobPost