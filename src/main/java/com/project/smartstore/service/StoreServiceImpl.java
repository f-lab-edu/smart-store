package com.project.smartstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.smartstore.dto.StoreDto;
import com.project.smartstore.mapper.StoreMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

  private final StoreMapper storeMapper;

  @Override
  public void createStore(StoreDto store) {
    storeMapper.createStore(store);
  }

  @Override
  public List<StoreDto> selectStoreList(String ownerId) {
    return storeMapper.selectStoreList(ownerId);
  }
}
