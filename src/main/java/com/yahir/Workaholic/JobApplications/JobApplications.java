package com.yahir.Workaholic.JobApplications;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name="worker_id")
    private Integer worker_id;
    @JoinColumn(name="company_id")
    private Integer company_id;
    @JoinColumn(name="posting_id")
    private Integer postings_id;
    private String workerln;
    private String wemail;
    private String posting_title;

    public JobApplications() {
    }

    public JobApplications(Integer id, Integer worker_id, Integer company_id, Integer postings_id, String workerln, String wemail, String posting_title) {
        this.id = id;
        this.worker_id = worker_id;
        this.company_id = company_id;
        this.postings_id = postings_id;
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

    public Integer getWorker_id() {
        return this.worker_id;
    }

    public void setWorker_id(Integer worker_id) {
        this.worker_id = worker_id;
    }

    public Integer getCompany_id() {
        return this.company_id;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }

    public Integer getPostings_id() {
        return this.postings_id;
    }

    public void setPostings_id(Integer postings_id) {
        this.postings_id = postings_id;
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

    public JobApplications worker_id(Integer worker_id) {
        setWorker_id(worker_id);
        return this;
    }

    public JobApplications company_id(Integer company_id) {
        setCompany_id(company_id);
        return this;
    }

    public JobApplications postings_id(Integer postings_id) {
        setPostings_id(postings_id);
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
        return Objects.equals(id, jobApplications.id) && Objects.equals(worker_id, jobApplications.worker_id) && Objects.equals(company_id, jobApplications.company_id) && Objects.equals(postings_id, jobApplications.postings_id) && Objects.equals(workerln, jobApplications.workerln) && Objects.equals(wemail, jobApplications.wemail) && Objects.equals(posting_title, jobApplications.posting_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, worker_id, company_id, postings_id, workerln, wemail, posting_title);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", worker_id='" + getWorker_id() + "'" +
            ", company_id='" + getCompany_id() + "'" +
            ", postings_id='" + getPostings_id() + "'" +
            ", workerln='" + getWorkerln() + "'" +
            ", wemail='" + getWemail() + "'" +
            ", posting_title='" + getPosting_title() + "'" +
            "}";
    }


}
