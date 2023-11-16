package com.example.springbootjpa.domain.order;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository repository;

    @Test
    void test() {
        String uuid = UUID.randomUUID().toString();

        Order order = new Order();
        order.setUuid(uuid);
        order.setOrderStatus(OrderStatus.OPENED);
        order.setOrderDateTime(LocalDateTime.now());
        order.setMemo("---");
        order.setCreatedBy("kkangh00n");
        order.setCreatedAt(LocalDateTime.now());

        repository.save(order);

        Order findOrder = repository.findById(uuid).get();
        List<Order> allOrder = repository.findAll();

        repository.findAllByOrderStatus(OrderStatus.OPENED);
        repository.findAllByOrderStatusOrderByOrderDateTime(OrderStatus.OPENED);

        repository.findByMemo("---");
    }
}