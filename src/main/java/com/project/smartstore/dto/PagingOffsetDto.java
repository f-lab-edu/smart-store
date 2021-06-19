package com.project.smartstore.dto;

import lombok.Getter;

@Getter
public class PagingOffsetDto {

  private final int currentPageNum;

  private final int recordCountPerPage;

  private final int offsetNum;

  public PagingOffsetDto(int currentPageNum, int recordCountPerPage) {
    if (currentPageNum < 1) {
      currentPageNum = 1;
    }

    if (recordCountPerPage != 20 && recordCountPerPage != 40 && recordCountPerPage != 60
        && recordCountPerPage != 80) {
      recordCountPerPage = 40;
    }
    this.currentPageNum = currentPageNum;
    this.recordCountPerPage = recordCountPerPage;
    offsetNum = (currentPageNum - 1) * recordCountPerPage;
  }

}