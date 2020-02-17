package com.lalit.shopping.order.events;

import static java.time.ZoneId.of;
import static java.util.Optional.ofNullable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.lalit.shopping.order.model.Order;
import com.lalit.shopping.order.model.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrderEvent  implements Serializable {
  String orderId;
  String orderDate;
  String customerId;
  BigDecimal price;
  String orderStatus;
  List<Item> lineItems;

  public CreateOrderEvent(Order order){
    this.orderId = order.getOrderId().toString();
    this.customerId = order.getCustomerId().toString();
    this.orderDate = DateTimeFormatter.ISO_DATE_TIME
        .withZone(of("UTC"))
        .format(ofNullable(order.getOrderDate().toInstant()).orElse(
        Instant.now()));
    this.price = order.getPrice();
    this.orderStatus = OrderStatus.CREATED.name();
    this.lineItems = order.getLineItems().stream().map(lineItem -> {
      Item items = new Item();
      items.setPrice(lineItem.getPrice());
      items.setProductId(lineItem.getProductId().toString());
      items.setQuantity(lineItem.getQuantity());
      return items;
    }).collect(Collectors.toList());
  }
}
