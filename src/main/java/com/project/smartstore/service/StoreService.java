package com.project.smartstore.service;

import java.util.List;

import com.project.smartstore.dto.StoreDto;

public interface StoreService {

  void createStore(StoreDto store);

  List<StoreDto> selectStoreList(String ownerId);
}
