package com.lschulze.alugamazing.dto;

import java.util.ArrayList;
import java.util.List;

import com.lschulze.alugamazing.model.Category;
import com.lschulze.alugamazing.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

	private Long id;
	private String name;
	
	public static List<CategoryDto> converter (List<Category> categories){
		List<CategoryDto> categoriesDto = new ArrayList<CategoryDto>();
		for (Category category : categories) {
			categoriesDto.add(new CategoryDto(category.getId(), category.getName()));
		}
		return categoriesDto;
	}
	
	public CategoryDto(Category category) {
		super();
		this.id = category.getId();
		this.name = category.getName();
	}
	
	

}
