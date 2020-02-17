package com.lalit.shopping.product.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderEventSink {
  String EVENT_NAME = "order-created";

  @Input(OrderEventSink.EVENT_NAME)
  SubscribableChannel orderCreated();

}
