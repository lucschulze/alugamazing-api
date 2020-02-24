package com.lschulze.alugamazing.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lschulze.alugamazing.dto.CategoryDto;
import com.lschulze.alugamazing.dto.DetalharCategoryDto;
import com.lschulze.alugamazing.form.CategoryForm;
import com.lschulze.alugamazing.model.Category;
import com.lschulze.alugamazing.repository.CategoryRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/categories")
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@GetMapping
	public List<CategoryDto> lista(HttpServletResponse response) {
		List<Category> categories = categoryRepository.findAll();
		response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
		response.addHeader("X-Total-Count", "10");
		return CategoryDto.converter(categories);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<CategoryDto> register(@RequestBody @Valid CategoryForm form, UriComponentsBuilder uriBuilder) {
		Category category = form.converter();
		categoryRepository.save(category);
		
		URI uri = uriBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(new CategoryDto(category));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalharCategoryDto> detalhar(@PathVariable Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isPresent()) {
			return ResponseEntity.ok(new DetalharCategoryDto(category.get()));
		}
		return ResponseEntity.notFound().build();
	}

}
