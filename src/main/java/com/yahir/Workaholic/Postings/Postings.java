package com.yahir.Workaholic.Postings;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Postings {
    @Id
    @SequenceGenerator (
        name="id_sequence_generator",
        sequenceName = "id_sequence_generator",
        allocationSize = 1
    )
    @GeneratedValue (
        strategy = GenerationType.SEQUENCE,
        generator = "id_sequence_generator"
    )
    private Integer id;
    private String title;
    private String businessName;
    private String description;
    private String jobType;
    private String tags;
    private Number salary;
    private String location;
    private String country;

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Postings)) {
            return false;
        }
        Postings postings = (Postings) o;
        return Objects.equals(id, postings.id) && Objects.equals(title, postings.title) && Objects.equals(businessName, postings.businessName) && Objects.equals(description, postings.description) && Objects.equals(jobType, postings.jobType) && Objects.equals(tags, postings.tags) && Objects.equals(salary, postings.salary) && Objects.equals(location, postings.location) && Objects.equals(country, postings.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, businessName, description, jobType, tags, salary, location, country);
    }
    
    public Postings(Integer id, String title, String businessName, String description, String jobType, String tags, Number salary, String location, String country) {
        this.id = id;
        this.title = title;
        this.businessName = businessName;
        this.description = description;
        this.jobType = jobType;
        this.tags = tags;
        this.salary = salary;
        this.location = location;
        this.country = country;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobType() {
        return this.jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Number getSalary() {
        return this.salary;
    }

    public void setSalary(Number salary) {
        this.salary = salary;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
