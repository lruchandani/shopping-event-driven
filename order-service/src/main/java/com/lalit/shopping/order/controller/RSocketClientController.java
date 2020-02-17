package com.lalit.shopping.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;

@RestController
public class RSocketClientController {

  @Autowired
  RSocketRequester rSocketRequester;

  @GetMapping(value = "/numbers/{limit}")
  public Flux<Integer> getThroughWebClient(@PathVariable("limit") Integer limit){
    return WebClient.create()
        .get()
        .uri("http://localhost:8081/numbers/{limit}",limit)
        .retrieve()
        .bodyToFlux(Integer.class)
        .log();
  }

  @GetMapping(value = "/numbers/{limit}"  )
  public Flux<Integer> getThroughRSocket(@PathVariable("limit") Integer limit){
    return rSocketRequester
        .route("numbers.{limit}",limit)
        .retrieveFlux(Integer.class)
        .log();
  }
}
