package com.lalit.shopping.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProductDto {
  private UUID productId;
  private String  name;
  private String  description;
  private BigDecimal price;
  private Integer  totalQuantity;
}
