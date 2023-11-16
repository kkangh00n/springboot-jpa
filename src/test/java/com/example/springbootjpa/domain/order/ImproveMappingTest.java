package com.example.springbootjpa.domain.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ImproveMappingTest {

    @Autowired
    private EntityManagerFactory emf;

    @Test
    void inheritance_test() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Food food = new Food();
        food.setPrice(2000);
        food.setStockQuantity(200);
        food.setChef("백종원");

        em.persist(food);

        transaction.commit();
    }
}
