package com.project.smartstore.service;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.dto.ProductListDto;
import com.project.smartstore.dto.SearchConditionDto;
import java.util.List;
import com.project.smartstore.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductMapper productMapper;

  public void addProductInStore(ProductDto productDto) {
    productMapper.insertProduct(productDto);
  }

  public List<ProductListDto> getProductList(SearchConditionDto searchConditionDto) {
    return productMapper.selectProductList(searchConditionDto);
  }

  public ProductDto getProduct(int productId) {
    return productMapper.selectProduct(productId);
  }
}