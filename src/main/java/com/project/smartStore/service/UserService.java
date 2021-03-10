package com.project.smartStore.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.project.smartStore.dto.UserDto;

/**
 * UserService : 구매자와 판매자가 공통으로 수행하는 기능을 추상화한 인터페이스
 */
public interface UserService {
	public void joinUser(UserDto user);
	public boolean isUsingId(String userId);
	public boolean loginUser(UserDto user, HttpSession session);
	public Optional<UserDto> findUserByIdAndPassword(UserDto user);
	public void logOutUser(UserDto user);

}