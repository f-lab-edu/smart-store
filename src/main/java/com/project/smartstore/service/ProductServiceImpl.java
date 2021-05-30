package com.project.smartstore.service;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.mapper.ProductMapper;
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
}