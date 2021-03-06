package com.project.smartstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductListDto {

  private int productId;
  private String productName;
  private String categoryId;
  private int price;
  private String registerDate;
  private String mainImg;

}
