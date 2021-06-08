package com.project.smartstore.service;


import com.project.smartstore.dto.StoreDto;
import com.project.smartstore.exception.ExceedMaximumCountException;
import com.project.smartstore.mapper.StoreMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private final StoreMapper storeMapper;
  private static final int MAX_STORE_COUNT = 4;

  @Override
  public void createStore(StoreDto store) {
    int storeCnt = storeMapper.countStores(store.getOwnerId());

    if (storeCnt >= MAX_STORE_COUNT) {
      throw new ExceedMaximumCountException("스토어는 최대 4개까지 생성 가능합니다.");
    }

    storeMapper.createStore(store);
  }

  @Override
  public List<StoreDto> selectStoreList(String ownerId) {
    return storeMapper.selectStoreList(ownerId);
  }

  @Override
  public int countStores(String ownerId) {
    return storeMapper.countStores(ownerId);
  }

  @Override
  public StoreDto selectStore(String ownerId, String storeId) {
    return storeMapper.selectStore(ownerId, storeId);
  }

  @Override
  public void updateStore(StoreDto store) {
    storeMapper.updateStore(store);
  }

  @Override
  public void deleteStore(String ownerId, String storeId) {
    storeMapper.deleteStore(ownerId,  storeId);
  }
}
