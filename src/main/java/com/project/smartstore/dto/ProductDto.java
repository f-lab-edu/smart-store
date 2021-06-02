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

  String name;
  String category;
  int price;
  int amount;
  String mainImage;
  String detailImage;
  String description;
  String storeId;

}