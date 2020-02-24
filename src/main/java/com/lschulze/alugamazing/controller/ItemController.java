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

import com.lschulze.alugamazing.dto.ItemDto;
import com.lschulze.alugamazing.dto.UserDto;
import com.lschulze.alugamazing.form.ItemForm;
import com.lschulze.alugamazing.model.Item;
import com.lschulze.alugamazing.model.User;
import com.lschulze.alugamazing.repository.CategoryRepository;
import com.lschulze.alugamazing.repository.ItemRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/items")
public class ItemController {

	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public List<ItemDto> lista(HttpServletResponse response) {
		List<Item> items = itemRepository.findAll();
		response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
		response.addHeader("X-Total-Count", "10");
		return ItemDto.converter(items);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ItemDto> register(@RequestBody @Valid ItemForm form, UriComponentsBuilder uriBuilder) {
		Item item = form.converter(categoryRepository);
		itemRepository.save(item);
		
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(uri).body(new ItemDto(item));
	}
}
