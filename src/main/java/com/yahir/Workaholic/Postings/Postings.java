package com.yahir.Workaholic.Postings;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Postings {

    public Postings(){}

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
    private String businessName;
    private String title;
    private String description;
    private String[] jobType;
    private Number salary;
    private String salaryCurrency;
    private String salaryRate;
    private String location;
    private String country;
    private String duration;
    private String date;
    private String[] tags;
    private String[] benefits;

    public Postings(Integer id, String businessName, String title, String description, String[] jobType, Number salary, String salaryCurrency, String salaryRate, String location, String country, String duration, String date, String[] tags, String[] benefits) {
        this.id = id;
        this.businessName = businessName;
        this.title = title;
        this.description = description;
        this.jobType = jobType;
        this.salary = salary;
        this.salaryCurrency = salaryCurrency;
        this.salaryRate = salaryRate;
        this.location = location;
        this.country = country;
        this.duration = duration;
        this.date = date;
        this.tags = tags;
        this.benefits = benefits;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusinessName() {
        return this.businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getJobType() {
        return this.jobType;
    }

    public void setJobType(String[] jobType) {
        this.jobType = jobType;
    }

    public Number getSalary() {
        return this.salary;
    }

    public void setSalary(Number salary) {
        this.salary = salary;
    }

    public String getSalaryCurrency() {
        return this.salaryCurrency;
    }

    public void setSalaryCurrency(String salaryCurrency) {
        this.salaryCurrency = salaryCurrency;
    }

    public String getSalaryRate() {
        return this.salaryRate;
    }

    public void setSalaryRate(String salaryRate) {
        this.salaryRate = salaryRate;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getTags() {
        return this.tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getBenefits() {
        return this.benefits;
    }

    public void setBenefits(String[] benefits) {
        this.benefits = benefits;
    }

    public Postings id(Integer id) {
        setId(id);
        return this;
    }

    public Postings businessName(String businessName) {
        setBusinessName(businessName);
        return this;
    }

    public Postings title(String title) {
        setTitle(title);
        return this;
    }

    public Postings description(String description) {
        setDescription(description);
        return this;
    }

    public Postings jobType(String[] jobType) {
        setJobType(jobType);
        return this;
    }

    public Postings salary(Number salary) {
        setSalary(salary);
        return this;
    }

    public Postings salaryCurrency(String salaryCurrency) {
        setSalaryCurrency(salaryCurrency);
        return this;
    }

    public Postings salaryRate(String salaryRate) {
        setSalaryRate(salaryRate);
        return this;
    }

    public Postings location(String location) {
        setLocation(location);
        return this;
    }

    public Postings country(String country) {
        setCountry(country);
        return this;
    }

    public Postings duration(String duration) {
        setDuration(duration);
        return this;
    }

    public Postings date(String date) {
        setDate(date);
        return this;
    }

    public Postings tags(String[] tags) {
        setTags(tags);
        return this;
    }

    public Postings benefits(String[] benefits) {
        setBenefits(benefits);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Postings)) {
            return false;
        }
        Postings postings = (Postings) o;
        return Objects.equals(id, postings.id) && Objects.equals(businessName, postings.businessName) && Objects.equals(title, postings.title) && Objects.equals(description, postings.description) && Objects.equals(jobType, postings.jobType) && Objects.equals(salary, postings.salary) && Objects.equals(salaryCurrency, postings.salaryCurrency) && Objects.equals(salaryRate, postings.salaryRate) && Objects.equals(location, postings.location) && Objects.equals(country, postings.country) && Objects.equals(duration, postings.duration) && Objects.equals(date, postings.date) && Objects.equals(tags, postings.tags) && Objects.equals(benefits, postings.benefits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, businessName, title, description, jobType, salary, salaryCurrency, salaryRate, location, country, duration, date, tags, benefits);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", businessName='" + getBusinessName() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", jobType='" + getJobType() + "'" +
            ", salary='" + getSalary() + "'" +
            ", salaryCurrency='" + getSalaryCurrency() + "'" +
            ", salaryRate='" + getSalaryRate() + "'" +
            ", location='" + getLocation() + "'" +
            ", country='" + getCountry() + "'" +
            ", duration='" + getDuration() + "'" +
            ", date='" + getDate() + "'" +
            ", tags='" + getTags() + "'" +
            ", benefits='" + getBenefits() + "'" +
            "}";
    }

    
}
