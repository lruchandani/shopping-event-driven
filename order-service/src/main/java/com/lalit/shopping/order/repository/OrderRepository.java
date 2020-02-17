package com.lalit.shopping.order.repository;

import java.util.UUID;

import com.lalit.shopping.order.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
  Order findByOrderId(UUID orderId);
}
