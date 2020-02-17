package com.lalit.shopping.product.web;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.UUID;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import com.lalit.shopping.product.dto.ProductDto;
import com.lalit.shopping.product.model.Product;
import com.lalit.shopping.product.service.ProductService;

@RestController
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  ProductDtoMapper productDtoMapper;

  @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public Publisher<ProductDto> createOrder(@RequestBody ProductDto orderDto) {
    return Mono.fromSupplier(() -> productService.create(orderDto))
        .map(productDtoMapper::map);
  }

  @GetMapping(value = "/products/{productId}")
  public Publisher<ProductDto> getProductId(@PathVariable("productId") String productId) {
    return Mono.fromSupplier(() -> productService.get(UUID.fromString(productId)))
        .map(productDtoMapper::map);
  }

  @GetMapping(value = "/products")
  public Publisher<List<ProductDto>> getProducts() {
    return Mono.fromSupplier(() ->
        productService.findAll()
            .stream()
            .map(productDtoMapper::map)
            .collect(toList()));
  }

  @Component
  public static class ProductDtoMapper {

    ProductDto map(Product product) {
      ProductDto productDto = new ProductDto();
      productDto.setProductId(product.getProductId());
      productDto.setName(product.getName());
      productDto.setDescription(product.getDescription());
      productDto.setPrice(product.getPrice());
      productDto.setTotalQuantity(product.getTotalQuantity());
      return productDto;
    }
  }
}