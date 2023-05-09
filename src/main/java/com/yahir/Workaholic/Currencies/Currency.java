package com.yahir.Workaholic.Currencies;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
public class Currency {
    
    @Id
    @SequenceGenerator(
        name="currency_id_generator",
        sequenceName = "currency_id_generator",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "currency_id_generator"
    )
    private Integer id;
    @Column(nullable = false)
    private String code;

    public Currency() {
    }

    public Currency(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Currency id(Integer id) {
        setId(id);
        return this;
    }

    public Currency code(String code) {
        setCode(code);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Currency)) {
            return false;
        }
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) && Objects.equals(code, currency.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
