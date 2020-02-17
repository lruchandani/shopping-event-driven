package com.lalit.shopping.product.web;

import java.time.Duration;

import org.reactivestreams.Publisher;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@Controller
public class NumberController {

  @MessageMapping("numbers.{limit}")
  Publisher<Integer> getNumbers(@DestinationVariable("limit") Integer limit){
    return Flux.range(0,limit)
        .delayElements(Duration.ofSeconds(1), Schedulers.single());
  }

  @GetMapping("/numbers/{limit}")
  Publisher<Integer> getNumbersRest(@PathVariable("limit") Integer limit){
    return Flux.range(0,limit)
        .delayElements(Duration.ofSeconds(1), Schedulers.single());
  }

}
