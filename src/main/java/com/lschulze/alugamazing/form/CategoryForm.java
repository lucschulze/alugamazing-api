package com.lschulze.alugamazing.form;

import com.lschulze.alugamazing.model.Category;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoryForm {
	
	private String name;

	public Category converter() {
		return new Category(name);
	}
}
