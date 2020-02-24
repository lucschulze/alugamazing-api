package com.lschulze.alugamazing.dto;

import java.util.ArrayList;
import java.util.List;

import com.lschulze.alugamazing.model.Category;
import com.lschulze.alugamazing.model.Item;
import com.lschulze.alugamazing.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
	
	private Long id;
	private String name;
	private double price;
	private Long categoryId;
	
	public static List<ItemDto> converter(List<Item> items) {
		List<ItemDto> itemsDto = new ArrayList<ItemDto>();
		for (Item item : items) {
			itemsDto.add(new ItemDto(item.getId(), item.getName(), item.getPrice(), item.getType().getId()));
		}
		return itemsDto;
	}

	public ItemDto(Item item) {
		super();
		this.id = item.getId();
		this.name = item.getName();
		this.price = item.getPrice();
		this.categoryId = item.getType().getId();
	}
	
	

}
