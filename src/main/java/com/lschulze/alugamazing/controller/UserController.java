package com.lschulze.alugamazing.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lschulze.alugamazing.dto.UserDto;
import com.lschulze.alugamazing.form.UserForm;
import com.lschulze.alugamazing.model.User;
import com.lschulze.alugamazing.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<UserDto> lista(HttpServletResponse response) {
		List<User> users = userRepository.findAll();
		response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
		response.addHeader("X-Total-Count", "10");
		return UserDto.converter(users);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<UserDto> register(@RequestBody @Valid UserForm form, UriComponentsBuilder uriBuilder) {
		User user = form.converter();
		userRepository.save(user);
		
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));
	}

}
