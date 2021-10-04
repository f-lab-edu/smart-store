package com.project.smartstore.service;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import com.project.smartstore.dto.UserDto;
import com.project.smartstore.exception.DuplicatedIdException;
import com.project.smartstore.mapper.UserMapper;
import com.project.smartstore.utils.PasswordEncryptor;

//@RunWith(MockitoJUnitRunner.class) : JUnit4에서 사용한다. 
@ExtendWith(MockitoExtension.class) // Mock 테스트가 필요한 경우 써야하는 어노테이션이다. JUnit5에서 사용한다. 
class UserServiceImplTest {
  
  @InjectMocks // @InjectMocks는 @Mock 이 붙은 객체를 주입시키는 어노테이션이다.
  UserServiceImpl service;
  
  @Mock  //@Mock은 해당 클래스에 대한 Mock Object를 생성한다.
  UserMapper userMapper;
  
  @BeforeEach
  public void setSalt() {
    ReflectionTestUtils.setField(service, "salt", "flabsmartstore");
  }
  
  UserDto userDto;
  
  @Autowired
  PasswordEncryptor encryptor;

  @BeforeEach
  public void generateUser() {
    userDto = userDto.builder()
        .id("admin")
        .password("1234")
        .name("테스터")
        .address1("address1")
        .address2("address2")
        .phone("01012345678")
        .regDt("20210101")
        .build();
  }
  
  @Test
  @DisplayName("고객 회원가입 성공")
  public void test_joinUser_success() throws DuplicatedIdException {
    when(userMapper.isUsingId(any())).thenReturn(false);
    service.joinUser(userDto);
    assertThatNoException();
  }
  
  @Test
  @DisplayName("고객 회원가입 실패")
  public void test_joinUser_fail() {
    when(userMapper.isUsingId(any())).thenReturn(true);
    Exception exception = assertThrows(DuplicatedIdException.class, ()->{
      service.joinUser(userDto);
    }); 
    assertEquals("동일한 아이디가 존재합니다. ", exception.getMessage());
  }

  @Test
  @DisplayName("중복된 아이디가 존재하지 않을 경우에 FALSE를 반환한다.")
  void test_isNotUsingId_return_false() {
    //given
    when(userMapper.isUsingId(any())).thenReturn(true);
    
    //then
    assertTrue(service.isUsingId(userDto.getId()));
  }
  
  @Test
  @DisplayName("중복된 아이디가 존재하지 않을 경우에 TRUE를 반환한다.")
  void test_isUsingId_return_true() {
    //given
    when(userMapper.isUsingId(any())).thenReturn(false);
    
    //then
    assertFalse(service.isUsingId(userDto.getId()));
  }

  @Test
  @DisplayName("아이디와 비밀번호로 계정을 찾고 값을 반환한다.")
  void test_findUserByIdAdPassword_notnull() {
    //given
    when(userMapper.findUserByIdAndPassword(any())).thenReturn(userDto);
    //then
    Optional<UserDto> result = service.findUserByIdAndPassword(userDto);
    assertTrue(result.isPresent());
  }
  
  @Test
  @DisplayName("아이디와 비밀번호로 계정을 찾고 빈 값을 반환한다.")
  void test_findUserByIdAdPassword_null() {
    //given
    when(userMapper.findUserByIdAndPassword(any())).thenReturn(null);
    
    //then
    Optional<UserDto> result = service.findUserByIdAndPassword(userDto);
    assertFalse(result.isPresent());
  }
  
  @Test
  @DisplayName("아이디로 계정을 찾고 계정정보를 반환한다.")
  void test_findUserById_success() {
    //given
    when(userMapper.findUserById(any())).thenReturn(userDto);
    
    //when
    UserDto result = service.findUserById(userDto.getId());
    assertNotNull(result);
  }
  
  @Test
  @DisplayName("아이디로 계정을 찾았으나 등록된 계정정보가 없다.")
  void test_findUserById() {
    //given
    when(userMapper.findUserById(any())).thenReturn(userDto);
    
    //when
    UserDto result = service.findUserById(userDto.getId());
    assertNotNull(result);
  }
}
