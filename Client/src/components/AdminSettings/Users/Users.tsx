import React from 'react'
import './Users.css'
import { useQuery } from '@tanstack/react-query'
import { userStateInterface } from '../../../types'

const Users = () => {

  const { isLoading, error, data} = useQuery({
    queryKey: ["userQuery"],
    queryFn: async() => {
      const get = await fetch("http://localhost:8080/api/v1/user/all")
      const json = await get.json()
      console.log(json.map((t:any) => t.name))
      return json
    }
  })
  if(isLoading) return <h1>Loading</h1>
  if(error) console.log(error)

  return (
    <div className="usersContainer">
      <h1>List of Users</h1>
      <div className="usersTable">
        {data.map((user:userStateInterface) => (
          <h1>{user.name}</h1>
          ))}
      </div>
    </div>
  )
}

export default Users