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
      <div className="usersTableColumns">
        <h1>Id</h1>
        <h1>Name</h1>
        <h1>Email</h1>
        <h1>Country</h1>
        <h1>Role</h1>
      </div>
      <div className="usersTable">
        {data.map((user:userStateInterface) => (
          <div className="userRecord">
            <h1>{user.id.toString()}</h1>
            <h1>{user.name}</h1>
            <h1>{user.email}</h1>
            <h1>{user.country.name}</h1>
            <h1>{user.role.name}</h1>
          </div>
          ))}
      </div>
    </div>
  )
}

export default Users