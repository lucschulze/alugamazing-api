package com.lschulze.alugamazing.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="rents")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Rent {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Item item;
	@ManyToOne
	private User user;
	private LocalDate firstDay;
	private LocalDate lastDay;
	private double value;
	private boolean paid;
	private Status status;
	
	public Rent(Item item, User user, LocalDate firstDay, LocalDate lastDay, double value, boolean paid, Status status) {
		super();
		this.item = item;
		this.user = user;
		this.firstDay = firstDay;
		this.lastDay = lastDay;
		this.value = value;
		this.paid = paid;
		this.status = status;
	}	
	
}
