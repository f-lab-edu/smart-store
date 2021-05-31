package com.project.smartstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

  private String name;
  private String category;
  private int price;
  private int amount;
  private String mainImage;
  private String detailImage;
  private String description;
  private String storeId;

}