package com.example.springbootjpa.domain.order;

import com.example.springbootjpa.domain.parent.Parent;
import com.example.springbootjpa.domain.parent.ParentId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.UUID;
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

    @Test
    void mapped_super_class_test() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Order order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderStatus(OrderStatus.OPENED);
        order.setMemo("---");
        order.setOrderDateTime(LocalDateTime.now());

        //
        order.setCreatedBy("kkangh00n");
        order.setCreatedAt(LocalDateTime.now());

        em.persist(order);
        transaction.commit();
    }

    @Test
    void id_test() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        Parent parent = new Parent();
        parent.setId(new ParentId("id1", "id2"));

        em.persist(parent);
        transaction.commit();

        em.clear();
        //조회
        Parent findParent = em.find(Parent.class, new ParentId("id1", "id2"));
        log.info("{} {}", parent.getId().getId1(), parent.getId().getId2());
    }
}
