package com.lalit.shopping.order.events;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class Item  implements Serializable {
    String productId;
    Integer quantity;
    BigDecimal price;
}
