package com.lschulze.alugamazing.form;

import java.time.LocalDate;
import java.time.Period;

import com.lschulze.alugamazing.model.Item;
import com.lschulze.alugamazing.model.Rent;
import com.lschulze.alugamazing.model.User;
import com.lschulze.alugamazing.repository.ItemRepository;
import com.lschulze.alugamazing.repository.UserRepository;
import com.lschulze.alugamazing.util.DateUtils;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RentForm {

	private Long itemId;
	private Long userId;
	private LocalDate firstDay;
	private LocalDate lastDay;
	private boolean paid;
	
	public Rent converter (ItemRepository itemRepository, UserRepository userRepository) {
		Item item = itemRepository.getOne(itemId);
		User user = userRepository.getOne(userId);
		Period period = Period.between(firstDay, lastDay);
		int diff = period.getDays() + 1;
		return new Rent(item, user, firstDay, lastDay, new Double(item.getPrice()*diff), paid, DateUtils.reservedType(firstDay));
	}
	

}
