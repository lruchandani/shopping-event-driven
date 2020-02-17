package com.lalit.shopping.product;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lalit.shopping.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
  Product findProductByProductId(UUID productId);
  List<Product> findAllByProductIdIn(Collection<UUID> productIds);

}
