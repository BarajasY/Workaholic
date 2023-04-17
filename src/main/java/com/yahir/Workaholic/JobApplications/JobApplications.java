package com.yahir.Workaholic.JobApplications;

import java.util.Objects;

import com.yahir.Workaholic.Company.Company;
import com.yahir.Workaholic.Postings.Postings;
import com.yahir.Workaholic.Workers.Worker;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="worker_id")
    private Worker worker;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="posting_id")
    private Postings posting;
    private String workerln;
    private String wemail;
    private String posting_title;

    public JobApplications() {
    }

    public JobApplications(Integer id, Worker worker, String workerln, String wemail, String posting_title) {
        this.id = id;
        this.worker = worker;
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
        return Objects.equals(id, jobApplications.id) && Objects.equals(worker, jobApplications.worker) && Objects.equals(workerln, jobApplications.workerln) && Objects.equals(wemail, jobApplications.wemail) && Objects.equals(posting_title, jobApplications.posting_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, worker, workerln, wemail, posting_title);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", worker='" + getWorker() + "'" +
            ", workerln='" + getWorkerln() + "'" +
            ", wemail='" + getWemail() + "'" +
            ", posting_title='" + getPosting_title() + "'" +
            "}";
    }
}
