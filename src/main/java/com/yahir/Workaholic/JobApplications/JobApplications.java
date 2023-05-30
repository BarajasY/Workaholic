package com.yahir.Workaholic.JobApplications;

import java.util.Objects;

import com.yahir.Workaholic.Postings.Postings;
import com.yahir.Workaholic.Users.User;

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
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @ManyToOne
    @JoinColumn(name="posting_id", nullable = false)
    private Postings posting;
    private String coverLetter;

    public JobApplications() {
    }

    public JobApplications(Integer id, User user, Postings posting, String coverLetter) {
        this.id = id;
        this.user = user;
        this.posting = posting;
        this.coverLetter = coverLetter;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Postings getPosting() {
        return this.posting;
    }

    public void setPosting(Postings posting) {
        this.posting = posting;
    }

    public String getCoverLetter() {
        return this.coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    public JobApplications id(Integer id) {
        setId(id);
        return this;
    }

    public JobApplications user(User user) {
        setUser(user);
        return this;
    }

    public JobApplications posting(Postings posting) {
        setPosting(posting);
        return this;
    }

    public JobApplications coverLetter(String coverLetter) {
        setCoverLetter(coverLetter);
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
        return Objects.equals(id, jobApplications.id) && Objects.equals(user, jobApplications.user) && Objects.equals(posting, jobApplications.posting) && Objects.equals(coverLetter, jobApplications.coverLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, posting, coverLetter);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", user='" + getUser() + "'" +
            ", posting='" + getPosting() + "'" +
            ", coverLetter='" + getCoverLetter() + "'" +
            "}";
    }
}
