package com.example.demo;

import com.example.demo.test.GeneralDaoJPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;


@SpringBootApplication(exclude = JpaRepositoriesAutoConfiguration.class)
public class Demo5Application {
    @PersistenceUnit
    private EntityManagerFactory emf;
    @PostConstruct
    private void setUpEmf(){
        GeneralDaoJPA.setEmf(emf);
    }
    public static void main(String[] args) {
        SpringApplication.run(Demo5Application.class, args);
    }

}
