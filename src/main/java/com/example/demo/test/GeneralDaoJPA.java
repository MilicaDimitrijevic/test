package com.example.demo.test;


import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GeneralDaoJPA<T extends BaseEntity> {

    private Class<T> clazz;


    private static EntityManagerFactory emf;
    private EntityManager em;

    public GeneralDaoJPA() {
    }

    public static void setEmf(EntityManagerFactory emf) {
        GeneralDaoJPA.emf = emf;
    }

    public void setEm() {
        em = emf.createEntityManager();
    }

    public void closeEm() {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public void setClazz(Class<T> clazzToSet) {
        this.clazz = clazzToSet;
    }

    public T findOne(int id) {
        setEm();
        T entity = em.find(clazz, id);
        closeEm();
        return entity;
    }


    public Stream<Person> findAllAsStream() {
        setEm();
        Stream<Person> personStream = em.createQuery("Select p from Person p", Person.class).getResultStream().onClose(this::closeEm);
        return  personStream;

    }

    public List<Person> findAllAsList() {
        setEm();
        List<Person> personList = em.createQuery("Select p from Person p", Person.class).getResultList();
        closeEm();
        return  personList;

    }

    public void create(final T entity) {
        setEm();
        executeInsideTransaction(em -> em.persist(entity));
        closeEm();
    }

    public void update(final T entity) {
        setEm();
        executeInsideTransaction(em -> em.merge(entity));
        closeEm();
    }

    public void deleteById(int entityId) {
        setEm();
        final T entity = findOne(entityId);
        executeInsideTransaction(em -> em.remove(entity));
        closeEm();
    }

    public void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            action.accept(em);
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
}
