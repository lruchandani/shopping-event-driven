package com.lalit.shopping.order.controller;

import java.util.UUID;

import com.lalit.shopping.order.dto.OrderDto;
import com.lalit.shopping.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.rsocket.RSocketRequesterAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class OrderController {

  @Autowired
  OrderService orderService;

  @PostMapping(path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  Mono<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
    return Mono.fromSupplier(() -> orderService.createOrder(orderDto)).map(order -> new OrderDto(order));
  }

  @GetMapping(value = "/orders/{orderId}")
  public Mono<OrderDto> getMethodName(@PathVariable("orderId") String orderId) {
    return Mono.fromSupplier(() -> orderService.getOrderById(UUID.fromString(orderId)))
        .map(order -> new OrderDto(order));
  }

}
