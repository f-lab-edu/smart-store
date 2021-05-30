package com.project.smartstore.controller;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public void addProductInStore(@RequestBody ProductDto productDto) {
    productService.addProductInStore(productDto);
  }
}