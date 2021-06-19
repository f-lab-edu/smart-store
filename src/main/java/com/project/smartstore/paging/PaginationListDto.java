package com.project.smartstore.paging;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaginationListDto {

  private int totalRecordCount;

  private List<?> paginationListData;

}
