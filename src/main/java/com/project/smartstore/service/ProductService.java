package com.project.smartstore.service;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.dto.ProductListDto;
import com.project.smartstore.dto.SearchConditionDto;
import java.util.List;


public interface ProductService {

  void addProductInStore(ProductDto productDto);

  List<ProductListDto> getProductList(SearchConditionDto searchConditionDto);
}