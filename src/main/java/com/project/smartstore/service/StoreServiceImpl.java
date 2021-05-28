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
  private final LoginService sessionLoginService;

  @Override
  public void createStore(StoreDto store) {
    storeMapper.createStore(store);
  }

  @Override
  public List<StoreDto> selectStoreList() {
    return storeMapper.selectStoreList(sessionLoginService.getLoginId());
  }

  @Override
  public StoreDto selectStore(String storeId) {
    return storeMapper.selectStore(sessionLoginService.getLoginId(), storeId);
  }

  @Override
  public void updateStore(StoreDto store) {
    storeMapper.updateStore(store);
  }

  @Override
  public void deleteStore(String storeId) {
    storeMapper.deleteStore(sessionLoginService.getLoginId(), storeId);
  }
}
