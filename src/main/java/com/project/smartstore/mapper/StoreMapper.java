package com.project.smartstore.mapper;

import com.project.smartstore.dto.StoreDto;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {

  void createStore(StoreDto store);

  List<StoreDto> selectStoreList(String ownerId);

  int countStores(String ownerId);

  StoreDto selectStore(String ownerId, String storeId);

  void updateStore(StoreDto store);

  void deleteStore(String ownerId, String storeId);
}
