package com.lalit.shopping.order.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.lalit.shopping.order.dto.OrderDto;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "TBL_ORDER")
@Getter
@Setter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable = false,updatable = false,unique = true,columnDefinition = "BINARY(16)")
  private UUID orderId;

  @Column(nullable = false,updatable = false,columnDefinition = "BINARY(16)")
  private UUID customerId;
  private BigDecimal price;
  private Date orderDate;

  @OneToMany(mappedBy = "order",cascade =  CascadeType.ALL,orphanRemoval = true)
  private List<LineItem> lineItems;

  @Column(nullable = false)
  private OrderStatus orderStatus;

  public Order(){

  }

  public Order(final OrderDto orderDto) {
      this.customerId = orderDto.getCustomerId();
      this.price = orderDto.getPrice();
      this.orderId = UUID.randomUUID();
      this.orderStatus = OrderStatus.CREATED;
      this.orderDate = new Date();
      Order orderItem = this;
      this.lineItems = orderDto.getProducts().stream().map(item -> {
        LineItem lineItem = new LineItem();
        lineItem.setOrder(orderItem);
        lineItem.setName(item.getName());
        lineItem.setPrice(item.getPrice());
        lineItem.setProductId(item.getProductId());
        lineItem.setQuantity(item.getQuantity());
        return lineItem;
      }).collect(Collectors.toList());
  }
}
