package com.project.smartstore.service;

import com.project.smartstore.dto.StoreDto;
import java.util.List;


public interface StoreService {

  void createStore(StoreDto store);

  List<StoreDto> selectStoreList(String sessionLoginId);

  int countStores(String ownerId);

  StoreDto selectStore(String sessionLoginId, String storeId);

  void updateStore(StoreDto store);

  void deleteStore(String sessionLoginId, String storeId);
}
