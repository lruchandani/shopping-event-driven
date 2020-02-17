package com.lalit.shopping.product.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity(name = "TBL_PRODUCT")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(nullable = false,updatable = false,unique = true,columnDefinition = "BINARY(16)")
  private UUID productId;
  @Column(nullable = false)
  private String  name;
  @Column(nullable = false)
  private String  description;
  @Column(nullable = false)
  @ColumnDefault(value = "0")
  private BigDecimal price;
  @Column(nullable = false)
  @ColumnDefault(value = "0")
  private Integer  totalQuantity;
}
