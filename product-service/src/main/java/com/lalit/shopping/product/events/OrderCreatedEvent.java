package com.lalit.shopping.product.events;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedEvent implements Serializable {
  String orderId;
  String orderDate;
  String customerId;
  BigDecimal price;
  String orderStatus;
  List<Item> lineItems;

  @Setter
  @Getter
  public static class Item  implements Serializable {
    String productId;
    Integer quantity;
    BigDecimal price;
  }
}