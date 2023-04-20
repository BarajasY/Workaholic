package com.yahir.Workaholic.JobApplications;

import java.util.Objects;

import com.yahir.Workaholic.Company.Company;
import com.yahir.Workaholic.Postings.Postings;
import com.yahir.Workaholic.Workers.Worker;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class JobApplications {

    @Id
    @SequenceGenerator(
        name="jobApplication_id_generator",
        sequenceName = "jobApplication_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "jobApplication_id_generator"
    )
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="worker_id")
    private Worker worker;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="company_id")
    private Company company;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="posting_id")
    private Postings posting;
    private String workerln;
    private String wemail;
    private String posting_title;

    public JobApplications(Integer id, Worker worker, Company company, Postings posting, String workerln, String wemail, String posting_title) {
        this.id = id;
        this.worker = worker;
        this.company = company;
        this.posting = posting;
        this.workerln = workerln;
        this.wemail = wemail;
        this.posting_title = posting_title;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Worker getWorker() {
        return this.worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Postings getPosting() {
        return this.posting;
    }

    public void setPosting(Postings posting) {
        this.posting = posting;
    }

    public String getWorkerln() {
        return this.workerln;
    }

    public void setWorkerln(String workerln) {
        this.workerln = workerln;
    }

    public String getWemail() {
        return this.wemail;
    }

    public void setWemail(String wemail) {
        this.wemail = wemail;
    }

    public String getPosting_title() {
        return this.posting_title;
    }

    public void setPosting_title(String posting_title) {
        this.posting_title = posting_title;
    }

    public JobApplications id(Integer id) {
        setId(id);
        return this;
    }

    public JobApplications worker(Worker worker) {
        setWorker(worker);
        return this;
    }

    public JobApplications company(Company company) {
        setCompany(company);
        return this;
    }

    public JobApplications posting(Postings posting) {
        setPosting(posting);
        return this;
    }

    public JobApplications workerln(String workerln) {
        setWorkerln(workerln);
        return this;
    }

    public JobApplications wemail(String wemail) {
        setWemail(wemail);
        return this;
    }

    public JobApplications posting_title(String posting_title) {
        setPosting_title(posting_title);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof JobApplications)) {
            return false;
        }
        JobApplications jobApplications = (JobApplications) o;
        return Objects.equals(id, jobApplications.id) && Objects.equals(worker, jobApplications.worker) && Objects.equals(company, jobApplications.company) && Objects.equals(posting, jobApplications.posting) && Objects.equals(workerln, jobApplications.workerln) && Objects.equals(wemail, jobApplications.wemail) && Objects.equals(posting_title, jobApplications.posting_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, worker, company, posting, workerln, wemail, posting_title);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", worker='" + getWorker() + "'" +
            ", company='" + getCompany() + "'" +
            ", posting='" + getPosting() + "'" +
            ", workerln='" + getWorkerln() + "'" +
            ", wemail='" + getWemail() + "'" +
            ", posting_title='" + getPosting_title() + "'" +
            "}";
    }

    public JobApplications() {
    }

}
