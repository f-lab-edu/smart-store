package com.project.smartStore.service;

import org.springframework.stereotype.Service;

import com.project.smartStore.dto.UserDto;
import com.project.smartStore.exception.DuplicatedIdException;
import com.project.smartStore.mapper.UserMapper;
import com.project.smartStore.utils.PasswordEncryptor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

	private final UserMapper userMapper;

	@Override
	public void joinUser(UserDto user){

		if(isUsingId(user.getId())) {
			throw new DuplicatedIdException("동일한 아이디가 존재합니다. ");
		}
		String salt = PasswordEncryptor.generateSalt();
		String encryptedPassword = PasswordEncryptor.getEncrypt(user.getPassword(), salt);
		user.setPassword(encryptedPassword);

		userMapper.insertUser(user);
	}

	@Override
	public UserDto findUserByIdAndPassword(UserDto user) {
		return null;
	}

	@Override
	public boolean isUsingId(String userId) {
		return false;
	}

	@Override
	public void loginUser(UserDto user) {

	}

	@Override
	public void logOutUser(UserDto user) {

	}
}
