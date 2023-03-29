package com.yahir.Workaholic.Workers;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Worker {

    public Worker(){}
    
    @Id
    @SequenceGenerator(
        name="worker_id_generator",
        sequenceName = "worker_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "worker_id_generator"
    )
    private Integer id;
    private String fname;
    private String lname;
    private String country;
    private String email;
    private String password;
    private String tags;
    
    public Worker(Integer id, String fname, String lname, String country, String email, String password, String tags) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.email = email;
        this.password = password;
        this.tags = tags;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this)
        return true;
        if (!(o instanceof Worker)) {
            return false;
        }
        Worker worker = (Worker) o;
        return Objects.equals(id, worker.id) && Objects.equals(fname, worker.fname) && Objects.equals(lname, worker.lname) && Objects.equals(country, worker.country) && Objects.equals(password, worker.password) && Objects.equals(tags, worker.tags) && Objects.equals(email, worker.email);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname, country, password, tags, email);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTags() {
        return this.tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
