package com.project.smartstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchConditionDto {

  private Integer categoryId;

  private String searchKeyword;

  private String sort;

  private PagingOffsetDto pagingOffsetDto;
}