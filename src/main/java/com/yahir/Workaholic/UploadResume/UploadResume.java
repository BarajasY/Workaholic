package com.yahir.Workaholic.UploadResume;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class UploadResume {
    
    @Id
    @SequenceGenerator(
        name="uploadresume_id_generator",
        sequenceName = "uploadresume_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "uploadresume_id_generator"
    )
    private Integer id;
    private String email;
    private String filename;

    public UploadResume() {
    }

    public UploadResume(Integer id, String email, String filename) {
        this.id = id;
        this.email = email;
        this.filename = filename;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFilename() {
        return this.filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public UploadResume id(Integer id) {
        setId(id);
        return this;
    }

    public UploadResume email(String email) {
        setEmail(email);
        return this;
    }

    public UploadResume filename(String filename) {
        setFilename(filename);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UploadResume)) {
            return false;
        }
        UploadResume uploadResume = (UploadResume) o;
        return Objects.equals(id, uploadResume.id) && Objects.equals(email, uploadResume.email) && Objects.equals(filename, uploadResume.filename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, filename);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", filename='" + getFilename() + "'" +
            "}";
    }

   
}
