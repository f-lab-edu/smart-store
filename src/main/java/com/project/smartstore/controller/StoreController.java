package com.project.smartstore.controller;

import com.project.smartstore.annotation.LoginCheck;
import com.project.smartstore.annotation.LoginUserId;
import com.project.smartstore.dto.StoreDto;
import com.project.smartstore.service.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;

  @LoginCheck
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void createStore(@RequestBody StoreDto store) {
    storeService.createStore(store);
  }

  /**
   * ResponseEntity:
   *  ResponseEntity는 HttpHeader와 HttpBody를 포함하는 HttpEntity클래스를 상속받았습니다.
   *  ResponseEntity는 사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스이며,
   *  HttpStatus와 HttpHeader, HttpBody를 포함할 수 있습니다.
   */
  @LoginCheck
  @GetMapping
  public List<StoreDto> selectStoreList(@LoginUserId String loginUserId) {
    return storeService.selectStoreList(loginUserId);
  }

  @LoginCheck
  @GetMapping("/{storeId}")
  public StoreDto selectStore(@LoginUserId String loginUserId, @PathVariable String storeId) {
    return storeService.selectStore(loginUserId, storeId);
  }

  @LoginCheck
  @PutMapping
  public void updateStore(@RequestBody StoreDto store) {
    storeService.updateStore(store);
  }

  @LoginCheck
  @DeleteMapping("/{storeId}")
  public void deleteMapping(@LoginUserId String loginUserId, @PathVariable String storeId) {
    storeService.deleteStore(loginUserId, storeId);
  }
}
