package com.project.smartstore.controller;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.dto.ProductListDto;
import com.project.smartstore.dto.SearchConditionDto;
import com.project.smartstore.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping
  public List<ProductListDto> getProductList(@RequestParam(value = "cateId", required = false) Integer categoryId,
      @RequestParam(value = "keyword", required = false) String searchKeyword) {
    return productService.getProductList(new SearchConditionDto(categoryId, searchKeyword));
  }
}