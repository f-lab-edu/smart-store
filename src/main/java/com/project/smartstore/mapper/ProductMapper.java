package com.project.smartstore.mapper;

import com.project.smartstore.dto.ProductDto;
import com.project.smartstore.dto.ProductListDto;
import com.project.smartstore.dto.SearchConditionDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

  void insertProduct(ProductDto productDto);

  List<ProductListDto> selectProductList(SearchConditionDto searchConditionDto);

  ProductDto selectProduct(int productId);
}
