package com.lschulze.alugamazing.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lschulze.alugamazing.model.Rent;
import com.lschulze.alugamazing.model.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {

	private Long id;
	private Long itemId;
	private Long userId;
	private LocalDate firstDay;
	private LocalDate lastDay;
	private double value;
	private boolean paid;
	private Status status;
	
	public static List<RentDto> converter(List<Rent> rents) {
		List<RentDto> rentsDto = new ArrayList<RentDto>();
		for (Rent rent : rents) {
			rentsDto.add(new RentDto(rent.getId(), rent.getItem().getId(), rent.getUser().getId(), rent.getFirstDay(), rent.getLastDay(), rent.getValue(), rent.isPaid(), rent.getStatus()));
		}
		return rentsDto;
	}

	public RentDto(Rent rent) {
		super();
		this.id = rent.getId();
		this.itemId = rent.getItem().getId();
		this.userId = rent.getUser().getId();
		this.firstDay = rent.getFirstDay();
		this.lastDay = rent.getLastDay();
		this.value = rent.getValue();
		this.status = rent.getStatus();
	}
	
	
}
