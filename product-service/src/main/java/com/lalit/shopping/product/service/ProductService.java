package com.lalit.shopping.product.service;

import java.util.List;
import java.util.UUID;

import com.lalit.shopping.product.dto.ProductDto;
import com.lalit.shopping.product.model.Product;

public interface ProductService {

  Product create(ProductDto productDto);
  Product get(UUID productId);
  List<Product> findAll();

}
