package com.lschulze.alugamazing.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lschulze.alugamazing.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Long>{

}
