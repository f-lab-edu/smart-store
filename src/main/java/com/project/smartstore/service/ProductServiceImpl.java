package com.project.smartstore.service;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.dto.SearchConditionDto;
import com.project.smartstore.mapper.ProductMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductMapper productMapper;

  @Override
  public void addProductInStore(ProductDto productDto) {
    productMapper.insertProduct(productDto);
  }

  @Override
  public List<ProductDto> getProductList(SearchConditionDto searchConditionDto) {
    return productMapper.selectProduct(searchConditionDto);
  }
}