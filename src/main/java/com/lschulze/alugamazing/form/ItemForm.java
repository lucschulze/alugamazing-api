package com.lschulze.alugamazing.form;

import com.lschulze.alugamazing.model.Category;
import com.lschulze.alugamazing.model.Item;
import com.lschulze.alugamazing.model.Profile;
import com.lschulze.alugamazing.repository.CategoryRepository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemForm {

	private String name;
	private double price;
	private Long categoryId;
	
	public Item converter (CategoryRepository categoryRepository) {
		Category category = categoryRepository.getOne(categoryId);
		return new Item(name, price, category);
	}
}
