package com.lschulze.alugamazing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lschulze.alugamazing.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	List<Category> findByName(String name);
}
