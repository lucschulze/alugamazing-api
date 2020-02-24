package com.lschulze.alugamazing.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="items")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Item {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double price;
	@ManyToOne
	private Category type;
	@OneToMany(mappedBy = "item")
	private List<Rent> rents = new ArrayList<>();
	
	public Item(String name, double price, Category type) {
		super();
		this.name = name;
		this.price = price;
		this.type = type;
	}
}
