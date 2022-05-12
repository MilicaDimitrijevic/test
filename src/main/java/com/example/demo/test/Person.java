package com.example.demo.test;


import javax.persistence.*;

@Entity
@Table(name = "person")
public class Person extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    public Person() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email,String name) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id,String name) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name,Integer id) {
        this.name = name;
    }
}
