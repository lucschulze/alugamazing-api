package com.lschulze.alugamazing.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.lschulze.alugamazing.model.Category;
import com.lschulze.alugamazing.model.Item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DetalharCategoryDto {

	private Long id;
	private String name;
	private List<ItemDto> items = new ArrayList<>();
	
	public DetalharCategoryDto(Category category) {
		super();
		this.id = category.getId();
		this.name = category.getName();
		this.items.addAll(category.getItems().parallelStream().map(ItemDto::new).collect(Collectors.toList()));
	}
	
	
}
