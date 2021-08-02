package com.project.smartstore.service;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.dto.ProductListDto;
import com.project.smartstore.dto.SearchConditionDto;
import com.project.smartstore.paging.PaginationListDto;
import java.util.List;
import com.project.smartstore.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;

  public void addProductInStore(ProductDto productDto) {
    productMapper.insertProduct(productDto);
  }

  public PaginationListDto getProductList(SearchConditionDto searchConditionDto) {
    List<ProductListDto> productListDto = productMapper.selectProductList(searchConditionDto);
    int totalRecordCount = getProductListCount(searchConditionDto);
    return new PaginationListDto(totalRecordCount, productListDto);
  }

  @Cacheable(value = "product", key = "#productId")
  public ProductDto getProduct(int productId) {
    return productMapper.selectProduct(productId);
  }

  private int getProductListCount(SearchConditionDto searchConditionDto) {
    return productMapper.selectProductListCount(searchConditionDto);
  }

  @CachePut(value = "product", key = "#productId")
  public void modifyProduct(int productId, ProductDto productDto) {
    productMapper.updateProduct(productId, productDto);
  }
}