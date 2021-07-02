package com.project.smartstore.service;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.dto.ProductListDto;
import com.project.smartstore.dto.SearchConditionDto;
import com.project.smartstore.paging.PaginationListDto;
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

  public PaginationListDto getProductList(SearchConditionDto searchConditionDto) {
    List<ProductListDto> productListDto = productMapper.selectProductList(searchConditionDto);
    int totalRecordCount = getProductListCount(searchConditionDto);
    return new PaginationListDto(totalRecordCount, productListDto);
  }

  public ProductDto getProduct(int productId) {
    return productMapper.selectProduct(productId);
  }

  private int getProductListCount(SearchConditionDto searchConditionDto) {
    return productMapper.selectProductListCount(searchConditionDto);
  }

  public ProductDto modifyProduct(int productId, ProductDto productDto) {
    productMapper.updateProduct(productId, productDto);
    return productMapper.selectProduct(productId);
  }

  public void deleteProduct(int storeId, int productId) {
    productMapper.deleteProduct(storeId, productId);
  }
}