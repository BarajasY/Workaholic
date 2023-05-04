package com.yahir.Workaholic.postType;

import com.yahir.Workaholic.JobTypes.JobType;
import com.yahir.Workaholic.Postings.Postings;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

import java.io.Serializable;
import java.util.Objects;

public class PostType implements Serializable{
    
    @EmbeddedId
    private Postings postings;

    @EmbeddedId
    private JobType jobType;

    public PostType() {
    }

    public PostType(Postings postings, JobType jobType) {
        this.postings = postings;
        this.jobType = jobType;
    }

    public Postings getPostings() {
        return this.postings;
    }

    public void setPostings(Postings postings) {
        this.postings = postings;
    }

    public JobType getJobType() {
        return this.jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public PostType postings(Postings postings) {
        setPostings(postings);
        return this;
    }

    public PostType jobType(JobType jobType) {
        setJobType(jobType);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PostType)) {
            return false;
        }
        PostType postType = (PostType) o;
        return Objects.equals(postings, postType.postings) && Objects.equals(jobType, postType.jobType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postings, jobType);
    }

    @Override
    public String toString() {
        return "{" +
            " postings='" + getPostings() + "'" +
            ", jobType='" + getJobType() + "'" +
            "}";
    }
}
