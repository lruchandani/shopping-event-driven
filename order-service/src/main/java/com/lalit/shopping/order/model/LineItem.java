package com.lalit.shopping.order.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "TBL_LINEITEM")
@Getter
@Setter
public class LineItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false,updatable = false,columnDefinition = "BINARY(16)")
  private UUID productId;
  private String name;
  @Column(nullable = false,updatable = false)
  private Integer quantity;
  @Column(nullable = false,updatable = false)
  private BigDecimal price;
  @ManyToOne
  private Order order;

}
