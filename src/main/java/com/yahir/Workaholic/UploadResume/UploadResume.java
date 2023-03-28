package com.yahir.Workaholic.UploadResume;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
    @Lob
    @Column(name = "file", columnDefinition="BLOB")
    private Byte[] file;
    private String url;

    public UploadResume(Integer id, String email, Byte[] file, String url) {
        this.id = id;
        this.email = email;
        this.file = file;
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UploadResume url(String url) {
        setUrl(url);
        return this;
    }

    public UploadResume() {
    }

    public UploadResume(Integer id, String email, Byte[] file) {
        this.id = id;
        this.email = email;
        this.file = file;
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

    public Byte[] getFile() {
        return this.file;
    }

    public void setFile(Byte[] file) {
        this.file = file;
    }

    public UploadResume id(Integer id) {
        setId(id);
        return this;
    }

    public UploadResume email(String email) {
        setEmail(email);
        return this;
    }

    public UploadResume file(Byte[] file) {
        setFile(file);
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
        return Objects.equals(id, uploadResume.id) && Objects.equals(email, uploadResume.email) && Objects.equals(file, uploadResume.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, file);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", email='" + getEmail() + "'" +
            ", file='" + getFile() + "'" +
            "}";
    }
}
