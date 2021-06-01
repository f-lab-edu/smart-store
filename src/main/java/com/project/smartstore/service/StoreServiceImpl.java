package com.project.smartstore.service;

import com.project.smartstore.dto.StoreDto;
import com.project.smartstore.mapper.StoreMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private final StoreMapper storeMapper;

  @Override
  public void createStore(StoreDto store) {
    storeMapper.createStore(store);
  }

  @Override
  public List<StoreDto> selectStoreList(String sessionLoginId) {
    return storeMapper.selectStoreList(sessionLoginId);
  }

  @Override
  public StoreDto selectStore(String sessionLoginId, String storeId) {
    return storeMapper.selectStore(sessionLoginId, storeId);
  }

  @Override
  public void updateStore(StoreDto store) {
    storeMapper.updateStore(store);
  }

  @Override
  public void deleteStore(String sessionLoginId, String storeId) {
    storeMapper.deleteStore(sessionLoginId,  storeId);
  }
}
