package com.example.demo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
public class Contact {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 2, message = "Name must have at least two characters")
    private String name;
    private String email;
    private String phone;
    @Past
    private LocalDate birthday;


    public Contact() {
    }

    public Contact(String name, String email, String phone, LocalDate birthday) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
