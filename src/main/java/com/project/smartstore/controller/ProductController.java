package com.project.smartstore.controller;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.dto.SearchConditionDto;
import com.project.smartstore.dto.PagingOffsetDto;
import com.project.smartstore.paging.PaginationListDto;
import com.project.smartstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public PaginationListDto getProductList(
      @RequestParam(value = "cateId", required = false) Integer categoryId,
      @RequestParam(value = "keyword", required = false) String searchKeyword,
      @RequestParam(value = "sort", defaultValue = "date") String sort,
      @RequestParam(value = "pagingIndex", defaultValue = "1") Integer pagingIndex,
      @RequestParam(value = "pagingSize", defaultValue = "40") Integer pagingSize) {
    return productService.getProductList(new SearchConditionDto(categoryId, searchKeyword, sort,
        new PagingOffsetDto(pagingIndex, pagingSize)));
  }

  @GetMapping("/{productId}")
  public ProductDto getProduct(@PathVariable("productId") int productId) {
    return productService.getProduct(productId);
  }

  @PutMapping("/{productId}")
  public void modifyProduct(@PathVariable int productId, @RequestBody ProductDto productDto) {
    productService.modifyProduct(productId, productDto);
  }

  @CacheEvict(value = "product", key = "#productId")
  @DeleteMapping("/{storeId}/{productId}")
  public void deleteProduct(@PathVariable int storeId, @PathVariable int productId) {
    productService.deleteProduct(storeId, productId);
  }
}