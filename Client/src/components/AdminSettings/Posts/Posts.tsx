import React from "react";
import "./Posts.css";
import { useQuery } from "@tanstack/react-query";
import { PostingInterface } from "../../../types";
import { AiFillDelete } from "react-icons/ai";
import { useNavigate } from "react-router-dom";

const Posts = () => {
  const navigate = useNavigate();

  const { isLoading, error, data } = useQuery({
    queryKey: ["queryAllPosts"],
    queryFn: async () => {
      const get = await fetch("http://localhost:8080/api/v1/postings/all");
      return await get.json();
    },
  });
  if(isLoading) return <h1>Loading</h1>
  if(error) console.log(error)

  const deletePost = async (id:number) => {
    const post = await fetch(`http://localhost:8080/api/v1/postings/delete/`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        post_id: id
      })
    })
    if(post.ok) {
      navigate(0)
    }
  }

  return (
    <div className="postsContainer">
      <h1>List of Posts</h1>
      <div className="postsTableColumns">
        <h1>Title</h1>
        <h1>Date</h1>
        <h1>Company</h1>
        <h1>Country</h1>
      </div>
      {
        data.length > 0 
        ? 
        <section>
        {data.map((post: PostingInterface) => (
          <div className="postWrapper">
          <div className="postRecord">
            <h1>{post.title}</h1>
            <h1>{post.date}</h1>
            <h1>{post.user.name}</h1>
            <h1>{post.user.country.name}</h1>
          </div>
          <AiFillDelete className="deleteIcon" onClick={() => deletePost(post.id)}/>
        </div>
      ))}
      </section>
      : <h1>No posts found</h1>
    }
    </div>
  );
};

export default Posts;
