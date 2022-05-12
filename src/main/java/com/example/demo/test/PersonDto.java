package com.example.demo.test;

public class PersonDto {
    private String email;

    public PersonDto() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
