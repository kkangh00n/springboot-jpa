package com.example.springbootjpa.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class PersistenceContextTest {
    
    @Autowired
    CustomerRepository repository;
    
    @Autowired
    EntityManagerFactory emf;
    
    @BeforeEach
    void setUp(){
        repository.deleteAll();
    }

    @Test
    void save() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kkangh00n");
        customer.setLastName("kim");

        em.persist(customer);       //비영속 -> 영속
        transaction.commit();       //em.flush();
    }

    @Test
    void find() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kkangh00n");
        customer.setLastName("kim");

        em.persist(customer);       //비영속 -> 영속
        transaction.commit();       //em.flush();
        log.info("{} {}", customer.getLastName(), customer.getFirstName());    //쿼리 전송 X

        em.detach(customer);        //영속 -> 준영속
        Customer findCustomer = em.find(Customer.class, 1L);        //쿼리 전송
        log.info("{} {}", findCustomer.getLastName(), findCustomer.getFirstName());
    }

    @Test
    void update() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kkangh00n");
        customer.setLastName("kim");

        em.persist(customer);       //비영속 -> 영속
        transaction.commit();       //em.flush();

        transaction.begin();
        customer.setFirstName("guppy");
        customer.setLastName("kang");
        transaction.commit();       //update 쿼리 전송
    }

    @Test
    void delete() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("kkangh00n");
        customer.setLastName("kim");

        em.persist(customer);       //비영속 -> 영속
        transaction.commit();       //em.flush();

        transaction.begin();
        em.remove(customer);
        transaction.commit();       //update 쿼리 전송
    }
}
