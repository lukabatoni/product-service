package com.techie.microservices.product.service;

import com.techie.microservices.product.dto.ProductRequest;
import com.techie.microservices.product.dto.ProductResponse;
import com.techie.microservices.product.model.Product;
import com.techie.microservices.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  private final ProductRepository productRepository;

  public ProductResponse createProduct(ProductRequest productRequest) {
    Product product = Product.builder()
        .id(productRequest.id())
        .name(productRequest.name())
        .description(productRequest.description())
        .price(productRequest.price())
        .build();
    productRepository.save(product);
    log.info("Product {} is saved", product.getId());
    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getDescription(),
        product.getPrice());
  }

  public List<ProductResponse> getAllProducts() {
    List<ProductResponse> products = productRepository.findAll()
        .stream()
        .map(product -> new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription(),
            product.getPrice()))
        .toList();
    log.info("Products are fetched successfully");
    return products;
  }
}
