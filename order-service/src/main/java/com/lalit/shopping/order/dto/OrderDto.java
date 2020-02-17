package com.lalit.shopping.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.lalit.shopping.order.model.Order;

@Getter
@Setter
public class OrderDto {

  UUID orderId;
  UUID customerId;
  BigDecimal price;
  List<ProductDto> products;

  public OrderDto() {
  }

  public OrderDto(final Order order) {
    this.orderId = order.getOrderId();
    this.customerId = order.getCustomerId();
    this.price = order.getPrice();
    this.products = order.getLineItems().stream().map(lineItem -> {
      final ProductDto productDto = new ProductDto();
                            productDto.setName(lineItem.getName());
                            productDto.setPrice(lineItem.getPrice());
                            productDto.setProductId(lineItem.getProductId());
                            productDto.setQuantity(lineItem.getQuantity());
                            return productDto;
        }).collect(Collectors.toList());

  }

  @Getter
  @Setter
  public static class ProductDto {
    UUID productId;
    String name;
    Integer quantity;
    BigDecimal price;
  }
}
