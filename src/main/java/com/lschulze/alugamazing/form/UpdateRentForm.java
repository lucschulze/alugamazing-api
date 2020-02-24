package com.lschulze.alugamazing.form;

import java.time.LocalDate;

import com.lschulze.alugamazing.model.Rent;
import com.lschulze.alugamazing.model.Status;
import com.lschulze.alugamazing.repository.RentRepository;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRentForm {
	private LocalDate firstDay;
	private LocalDate lastDay;
	private boolean paid;
	private Status status;
	
	public Rent update (Long id, RentRepository rentRepository) {
		Rent rent = rentRepository.getOne(id);
		rent.setFirstDay(firstDay);
		rent.setLastDay(lastDay);
		rent.setPaid(paid);
		rent.setStatus(status);
		rentRepository.saveAndFlush(rent);
		return rent;
	}
}
