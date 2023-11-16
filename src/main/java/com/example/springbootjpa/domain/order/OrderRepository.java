package com.example.springbootjpa.domain.order;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
    List<Order> findAllByOrderStatusOrderByOrderDateTime(OrderStatus orderStatus);

    @Query("select o from Order as o where o.memo LIKE %?1%")
    Optional<Order> findByMemo(String memo);
}
