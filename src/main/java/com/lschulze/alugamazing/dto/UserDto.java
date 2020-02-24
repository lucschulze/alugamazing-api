package com.lschulze.alugamazing.dto;

import java.util.ArrayList;
import java.util.List;

import com.lschulze.alugamazing.model.Profile;
import com.lschulze.alugamazing.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	private Long id;
	private String name;
	private String email;
	private Profile profile;

	public static List<UserDto> converter(List<User> users) {
		List<UserDto> usersDto = new ArrayList<UserDto>();
		for (User user : users) {
			usersDto.add(new UserDto(user.getId(), user.getName(), user.getEmail(), user.getProfile()));
		}
		return usersDto;
	}

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.profile = user.getProfile();
	}

}
