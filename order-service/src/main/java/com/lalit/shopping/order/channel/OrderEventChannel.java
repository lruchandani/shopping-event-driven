package com.lalit.shopping.order.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderEventChannel {
  String CREATE_ORDER = "order-created";

  @Output(CREATE_ORDER)
  MessageChannel orderCreated();
}
