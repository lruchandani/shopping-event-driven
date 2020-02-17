package com.lalit.shopping.order.service;

import static org.springframework.messaging.MessageHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.UUID;

import com.lalit.shopping.order.channel.OrderEventChannel;
import com.lalit.shopping.order.dto.OrderDto;
import com.lalit.shopping.order.events.CreateOrderEvent;
import com.lalit.shopping.order.model.Order;
import com.lalit.shopping.order.repository.OrderRepository;

@Service
@EnableBinding(OrderEventChannel.class)
public class OrderServiceImpl implements OrderService {

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  OrderEventChannel orderEventChannel;

  @Override
  public Order createOrder(OrderDto orderDto) {
    Order order= orderRepository.save(new Order(orderDto));
    orderEventChannel.orderCreated()
        .send(MessageBuilder
        .withPayload(new CreateOrderEvent(order))
            .setHeaderIfAbsent(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        .build());
    return order;
  }

  @Override
  public Order getOrderById(UUID orderId) {
    return orderRepository.findByOrderId(orderId);
  }
}
