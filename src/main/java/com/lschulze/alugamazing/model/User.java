package com.lschulze.alugamazing.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@ToString
public class User {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	@Enumerated(EnumType.STRING)
	private Profile profile;
	@OneToMany(mappedBy = "user")
	private List<Rent> rents = new ArrayList<>();
	
	public User(String email, String password, Profile profile) {
		super();
		this.email = email;
		this.password = password;
		this.profile = profile;
	}
	
	public User(String name, String email, String password, Profile profile) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.profile = profile;
	}
	
}	
