package com.yahir.Workaholic.Postings;

import java.sql.Date;
import java.util.Objects;
import java.util.Set;

import com.yahir.Workaholic.Currencies.Currency;
import com.yahir.Workaholic.JobTypes.JobType;
import com.yahir.Workaholic.Rates.Rate;
import com.yahir.Workaholic.Resume.Resume;
import com.yahir.Workaholic.Users.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @ManyToMany
    @JoinTable(name = "posting_types", 
                joinColumns = {@JoinColumn(name = "posting_id")},
                inverseJoinColumns = {@JoinColumn(name = "jobtype_id")})
    Set<JobType> jobTypes;
    @Column(nullable = false)
    private Integer salary;
    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;
    @ManyToOne
    @JoinColumn(name = "rate_id", nullable = false)
    private Rate rate;
    @Column(nullable = false)
    private Integer duration;
    private Date date;
    private String benefits;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name =  "company_id", nullable = false)
    private User user;

    public Postings(Integer id, String title, String description, Set<JobType> jobTypes, Integer salary, Currency currency, Rate rate, Integer duration, Date date, String benefits, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.jobTypes = jobTypes;
        this.salary = salary;
        this.currency = currency;
        this.rate = rate;
        this.duration = duration;
        this.date = date;
        this.benefits = benefits;
        this.user = user;
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<JobType> getJobTypes() {
        return this.jobTypes;
    }

    public void setJobTypes(Set<JobType> jobTypes) {
        this.jobTypes = jobTypes;
    }

    public Integer getSalary() {
        return this.salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Rate getRate() {
        return this.rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBenefits() {
        return this.benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Postings id(Integer id) {
        setId(id);
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

    public Postings jobTypes(Set<JobType> jobTypes) {
        setJobTypes(jobTypes);
        return this;
    }

    public Postings salary(Integer salary) {
        setSalary(salary);
        return this;
    }

    public Postings currency(Currency currency) {
        setCurrency(currency);
        return this;
    }

    public Postings rate(Rate rate) {
        setRate(rate);
        return this;
    }

    public Postings duration(Integer duration) {
        setDuration(duration);
        return this;
    }

    public Postings date(Date date) {
        setDate(date);
        return this;
    }

    public Postings benefits(String benefits) {
        setBenefits(benefits);
        return this;
    }

    public Postings user(User user) {
        setUser(user);
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
        return Objects.equals(id, postings.id) && Objects.equals(title, postings.title) && Objects.equals(description, postings.description) && Objects.equals(jobTypes, postings.jobTypes) && Objects.equals(salary, postings.salary) && Objects.equals(currency, postings.currency) && Objects.equals(rate, postings.rate) && Objects.equals(duration, postings.duration) && Objects.equals(date, postings.date) && Objects.equals(benefits, postings.benefits) && Objects.equals(user, postings.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, jobTypes, salary, currency, rate, duration, date, benefits, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", jobTypes='" + getJobTypes() + "'" +
            ", salary='" + getSalary() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", rate='" + getRate() + "'" +
            ", duration='" + getDuration() + "'" +
            ", date='" + getDate() + "'" +
            ", benefits='" + getBenefits() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
}
