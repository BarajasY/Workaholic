package com.yahir.Workaholic.Users;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.yahir.Workaholic.Countries.Country;
import com.yahir.Workaholic.Roles.Role;
import com.yahir.Workaholic.Tags.Tag;

@Entity
public class User {
    
    @Id
    @SequenceGenerator(
        name="user_id_generator",
        sequenceName = "user_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_id_generator"
    )
    private Integer id;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToOne
    private Country country;
    private String cvPath;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }

    public User(Integer id, String name, String email, String password, Country country, String cvPath, Role role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.country = country;
        this.cvPath = cvPath;
        this.role = role;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Country getCountry() {
        return this.country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCvPath() {
        return this.cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User id(Integer id) {
        setId(id);
        return this;
    }

    public User name(String name) {
        setName(name);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User country(Country country) {
        setCountry(country);
        return this;
    }

    public User cvPath(String cvPath) {
        setCvPath(cvPath);
        return this;
    }

    public User role(Role role) {
        setRole(role);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(country, user.country) && Objects.equals(cvPath, user.cvPath) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, country, cvPath, role);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", country='" + getCountry() + "'" +
            ", cvPath='" + getCvPath() + "'" +
            ", role='" + getRole() + "'" +
            "}";
    }
}
