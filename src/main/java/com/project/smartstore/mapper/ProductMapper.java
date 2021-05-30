package com.project.smartstore.mapper;

import com.project.smartstore.dto.ProductDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {

  void insertProduct(ProductDto productDto);

}
