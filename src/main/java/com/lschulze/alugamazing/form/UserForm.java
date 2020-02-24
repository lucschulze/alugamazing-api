package com.lschulze.alugamazing.form;


import com.lschulze.alugamazing.model.Profile;
import com.lschulze.alugamazing.model.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserForm {

	private String name;
	private String email;
	private String password;
	private Profile profile;
	
	public User converter() {
		return new User(name, email, password, profile);
	}
}
