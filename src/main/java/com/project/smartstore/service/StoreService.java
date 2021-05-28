package com.project.smartstore.service;

import com.project.smartstore.dto.StoreDto;
import java.util.List;


public interface StoreService {

  void createStore(StoreDto store);

  List<StoreDto> selectStoreList();

  StoreDto selectStore(String storeId);

  void updateStore(StoreDto store);

  void deleteStore(String storeId);
}
