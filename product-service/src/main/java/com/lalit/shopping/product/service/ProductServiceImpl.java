package com.lalit.shopping.product.service;

import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.lalit.shopping.product.ProductRepository;
import com.lalit.shopping.product.channel.OrderEventSink;
import com.lalit.shopping.product.dto.ProductDto;
import com.lalit.shopping.product.events.OrderCreatedEvent;
import com.lalit.shopping.product.events.OrderCreatedEvent.Item;
import com.lalit.shopping.product.model.Product;

import lombok.extern.slf4j.Slf4j;

@Service
@EnableBinding(OrderEventSink.class)
@Slf4j
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;

  @Override
  public Product create(ProductDto productDto) {
    Product product = new Product();
    product.setProductId(UUID.randomUUID());
    product.setDescription(productDto.getDescription());
    product.setName(productDto.getName());
    product.setPrice(productDto.getPrice());
    product.setTotalQuantity(productDto.getTotalQuantity());
    return productRepository.save(product);
  }

  @Override
  public Product get(UUID productId) {
    return productRepository.findProductByProductId(productId);
  }

  @Override
  public List<Product> findAll() {
    return productRepository.findAll();
  }

  @StreamListener(OrderEventSink.EVENT_NAME)
  public void handleOrderCreated(OrderCreatedEvent event){
    log.info("Order Event received : {}",event.getOrderId());
    //get product
    Map<String, Item> products= event.getLineItems().stream().collect(toMap(Item::getProductId, Function.identity()));
    List<Product> updatedProducts=productRepository.findAllByProductIdIn(products.keySet().stream().map(UUID::fromString).collect(toList()))
                     .stream()
                     .map(product-> ofNullable(products.get(product.getProductId().toString()))
                                       .map(item -> {
                                         product.setTotalQuantity(product.getTotalQuantity()>item.getQuantity()?
                                             product.getTotalQuantity()-item.getQuantity():
                                             product.getTotalQuantity());
                                         return product;
                                       })
                                      .orElse(product))
                    .collect(Collectors.toList());
    productRepository.saveAll(updatedProducts);
  }

}
