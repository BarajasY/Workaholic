package com.yahir.Workaholic.Company;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Company {

    public Company() {}
    
    @Id
    @SequenceGenerator(
        name="company_id_generator",
        sequenceName = "company_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "company_id_generator"
    )
    private Integer id;
    private String name;
    private String location;
    private String owner;
    private String email;
    private String country;
    private String tags;

    public Company(Integer id, String name, String location, String owner, String email, String country, String tags) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.owner = owner;
        this.email = email;
        this.country = country;
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Company)) {
            return false;
        }
        Company company = (Company) o;
        return Objects.equals(id, company.id) && Objects.equals(name, company.name) && Objects.equals(location, company.location) && Objects.equals(owner, company.owner) && Objects.equals(email, company.email) && Objects.equals(country, company.country) && Objects.equals(tags, company.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location, owner, email, country, tags);
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

}
