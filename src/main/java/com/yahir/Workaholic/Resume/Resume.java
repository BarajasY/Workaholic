package com.yahir.Workaholic.Resume;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.util.Objects;

@Entity
public class Resume {
    
    @Id
    @SequenceGenerator(
        name="resume_id_generator",
        sequenceName = "resume_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "resume_id_generator"
    )
    private Integer id;
    private String path;

    public Resume() {
    }

    public Resume(Integer id, String path) {
        this.id = id;
        this.path = path;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Resume id(Integer id) {
        setId(id);
        return this;
    }

    public Resume path(String path) {
        setPath(path);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Resume)) {
            return false;
        }
        Resume resume = (Resume) o;
        return Objects.equals(id, resume.id) && Objects.equals(path, resume.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", path='" + getPath() + "'" +
            "}";
    }
}
