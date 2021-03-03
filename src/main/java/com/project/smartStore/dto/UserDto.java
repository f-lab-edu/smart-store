package com.project.smartStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 *
 * @Builder :
 *
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private Integer id;
	private String email;
	private String password;
	private String name;
	private String phone;
}