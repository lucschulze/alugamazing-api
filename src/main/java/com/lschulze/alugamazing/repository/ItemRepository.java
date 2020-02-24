package com.lschulze.alugamazing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lschulze.alugamazing.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

	List<Item> findByName(String name);
}
