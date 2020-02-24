package com.lschulze.alugamazing.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.lschulze.alugamazing.dto.RentDto;
import com.lschulze.alugamazing.form.RentForm;
import com.lschulze.alugamazing.form.UpdateRentForm;
import com.lschulze.alugamazing.model.Rent;
import com.lschulze.alugamazing.model.Status;
import com.lschulze.alugamazing.repository.ItemRepository;
import com.lschulze.alugamazing.repository.RentRepository;
import com.lschulze.alugamazing.repository.UserRepository;
import com.lschulze.alugamazing.util.DateUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rents")
public class RentController {

	@Autowired
	private RentRepository rentRepository;
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
		
	@GetMapping
	public List<RentDto> lista(HttpServletResponse response) {
		List<Rent> rents = rentRepository.findAll();
		for (Rent rent : rents) {
			if (rent.getStatus() != Status.RETURNED && rent.getStatus() != Status.CANCELED) {
				Status status = DateUtils.checkStatus(rent.getFirstDay(), rent.getLastDay());
				if (rent.getStatus() != status) {
					rent.setStatus(status);
					rentRepository.saveAndFlush(rent);
				}
			}
		}
		response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
		response.addHeader("X-Total-Count", "10");
		return RentDto.converter(rents);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RentDto> detalhar(HttpServletResponse response, @PathVariable Long id) {
		Optional<Rent> rent = rentRepository.findById(id);
		response.addHeader("Access-Control-Expose-Headers", "X-Total-Count");
		response.addHeader("X-Total-Count", "1");
		if(rent.isPresent()) {
			return ResponseEntity.ok(new RentDto(rent.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<RentDto> register(@RequestBody @Valid RentForm form, UriComponentsBuilder uriBuilder) {
		Rent rent = form.converter(itemRepository, userRepository);
		rentRepository.save(rent);
		
		URI uri = uriBuilder.path("/users/{id}").buildAndExpand(rent.getId()).toUri();
		return ResponseEntity.created(uri).body(new RentDto(rent));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<RentDto> atualizar(@PathVariable Long id, @RequestBody @Valid UpdateRentForm form){
		Optional<Rent> optional = rentRepository.findById(id);
		if(optional.isPresent()) {
			Rent rent = form.update(id, rentRepository);
			
			return ResponseEntity.ok(new RentDto(rent));
		}
		return ResponseEntity.notFound().build();	
	}
	
}
