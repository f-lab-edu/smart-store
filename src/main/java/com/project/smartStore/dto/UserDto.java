package com.project.smartStore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @Builder :
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String id;
	private String password;
	private String name;
	private String phone;
	private String type; // customer , seller
}