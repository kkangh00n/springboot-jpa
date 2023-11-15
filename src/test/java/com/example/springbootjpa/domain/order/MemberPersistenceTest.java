package com.example.springbootjpa.domain.order;

import static org.junit.jupiter.api.Assertions.*;

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
class MemberPersistenceTest {

    @Autowired
    EntityManagerFactory emf;

    @Test
    void 연관관계_테스트() {
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        transaction.begin();

        Member member = new Member();
        member.setName("kimkanghoon");
        member.setNickName("kkangh00n");
        member.setAddress("서울시 구로구");
        member.setAge(26);

        entityManager.persist(member);

        Order order = new Order();
        order.setUuid(UUID.randomUUID().toString());
        order.setOrderStatus(OrderStatus.OPENED);
        order.setOrderDateTime(LocalDateTime.now());
        order.setMemo("부재시 연락");
        order.setMember(member);        //MemberId가 참조키로 잘 등록 됨

        entityManager.persist(order);
        transaction.commit();

        entityManager.clear();
        Order entity = entityManager.find(Order.class, order.getUuid());

        log.info("{}", entity.getMember().getNickName());  //객체 그래프 탐색
        log.info("{}", entity.getMember().getOrders().size());
    }
}