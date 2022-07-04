package com.example.demo.model;

import org.springframework.lang.NonNull;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Integer id;
    @NotBlank
    String name;
    @NotBlank
    String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String email) {
        this.city = email;
    }

    @Override
    public String toString() {
        return String.format("User id: %d, name: %s, city: %s", this.getId(), this.getName(), this.getCity());
    }
}

