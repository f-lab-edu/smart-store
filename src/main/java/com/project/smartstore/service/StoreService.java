package com.project.smartstore.service;

import com.project.smartstore.dto.StoreDto;
import java.util.List;


public interface StoreService {

  void createStore(StoreDto store);

  List<StoreDto> selectStoreList(String ownerId);

  StoreDto selectStore(String ownerId, String storeId);

  void updateStore(StoreDto store);

  void deleteStore(String ownerId, String storeId);
}
