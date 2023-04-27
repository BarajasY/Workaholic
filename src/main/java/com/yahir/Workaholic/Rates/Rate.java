package com.yahir.Workaholic.Rates;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.util.Objects;

@Entity
public class Rate {
    
    @Id
    @SequenceGenerator(
        name="rate_id_generator",
        sequenceName = "rate_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "rate_id_generator"
    )
    private Integer id;
    @Column(nullable = false)
    private String rateName;

    public Rate() {
    }

    public Rate(Integer id, String rateName) {
        this.id = id;
        this.rateName = rateName;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRateName() {
        return this.rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    public Rate id(Integer id) {
        setId(id);
        return this;
    }

    public Rate rateName(String rateName) {
        setRateName(rateName);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rate)) {
            return false;
        }
        Rate rate = (Rate) o;
        return Objects.equals(id, rate.id) && Objects.equals(rateName, rate.rateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rateName);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", rateName='" + getRateName() + "'" +
            "}";
    }
}
