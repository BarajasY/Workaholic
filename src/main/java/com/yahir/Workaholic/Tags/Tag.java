package com.yahir.Workaholic.Tags;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.yahir.Workaholic.Users.User;

@Entity
public class Tag {
    
    @Id
    @SequenceGenerator(
        name="tag_id_generator",
        sequenceName = "tag_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "tag_id_generator"
    )
    private Integer id;
    private String tagName;

    public Tag() {
    }

    public Tag(Integer id, String tagName) {
        this.id = id;
        this.tagName = tagName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Tag id(Integer id) {
        setId(id);
        return this;
    }

    public Tag tagName(String tagName) {
        setTagName(tagName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tagName);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", tagName='" + getTagName() + "'" +
            "}";
    }
/*     @ManyToMany(mappedBy = "tags")
    private Set<User> users = new HashSet<>(); */
}
