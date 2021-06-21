package com.project.smartstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto {
  private Integer storeId;
  private String storeName;
  private String categoryId;
  private String storeAddress1;
  private String storeAddress2;
  private String storePhone;
  private String ownerId;
  private String bizNumber;
  private String regDt;
  private String modiDt;
}
