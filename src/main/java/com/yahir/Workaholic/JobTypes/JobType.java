package com.yahir.Workaholic.JobTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import java.util.Objects;
import java.util.Set;

import com.yahir.Workaholic.Postings.Postings;

@Entity
public class JobType {
    
    @Id
    @SequenceGenerator (
        name="jobtype_sequence_generator",
        sequenceName = "jobtype_sequence_generator",
        allocationSize = 1
    )
    @GeneratedValue (
        strategy = GenerationType.SEQUENCE,
        generator = "jobtype_sequence_generator"
    )
    private Integer id;
    @Column(nullable = false)
    private String type;
    @ManyToMany(mappedBy = "jobTypes")
    private Set<Postings> postings;

    public JobType(Integer id, String type, Set<Postings> postings) {
        this.id = id;
        this.type = type;
        this.postings = postings;
    }

    public Set<Postings> getPostings() {
        return this.postings;
    }

    public void setPostings(Set<Postings> postings) {
        this.postings = postings;
    }

    public JobType postings(Set<Postings> postings) {
        setPostings(postings);
        return this;
    }

    public JobType() {
    }

    public JobType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public JobType id(Integer id) {
        setId(id);
        return this;
    }

    public JobType type(String type) {
        setType(type);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JobType)) {
            return false;
        }
        JobType jobType = (JobType) o;
        return Objects.equals(id, jobType.id) && Objects.equals(type, jobType.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
