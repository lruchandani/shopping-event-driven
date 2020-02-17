package com.lalit.shopping.order.service;

import java.util.UUID;

import com.lalit.shopping.order.dto.OrderDto;
import com.lalit.shopping.order.model.Order;

public interface OrderService {
  Order createOrder(OrderDto orderDto);
  
  Order getOrderById(UUID orderId);

}
