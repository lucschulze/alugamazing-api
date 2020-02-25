package com.lschulze.alugamazing;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.lschulze.alugamazing.model.Category;
import com.lschulze.alugamazing.model.Item;
import com.lschulze.alugamazing.model.Profile;
import com.lschulze.alugamazing.model.Rent;
import com.lschulze.alugamazing.model.Status;
import com.lschulze.alugamazing.model.User;
import com.lschulze.alugamazing.repository.CategoryRepository;
import com.lschulze.alugamazing.repository.ItemRepository;
import com.lschulze.alugamazing.repository.RentRepository;
import com.lschulze.alugamazing.repository.UserRepository;

@SpringBootTest
class AlugamazingApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private RentRepository rentRepository;
	
	
	
	@Test
	void contextLoads() {
		
		LocalDate firstDay = LocalDate.now();
		LocalDate lastDay = LocalDate.now();
		
		User user1 = new User("Lucas Schulze", "contato@lschulze.com", "123", Profile.ADMIN);
		User user2 = new User("Davi Schulze", "davi@gmail.com", "123", Profile.CLIENT);
		User user3 = new User("Vivi Morais", "vivi@gmail.com", "123", Profile.CLIENT);
		User user4 = new User("André Alves", "andre@gmail.com", "123", Profile.CLIENT);
		
		Category cat1 = new Category("HQ");
		Category cat2 = new Category("Mangá");
		Category cat3 = new Category("Livro");
		
		Item item1 = new Item("Naruto 1", new Double("5.0"), cat2);
		Item item2 = new Item("Naruto 2", new Double("5.5"), cat2);
		Item item3 = new Item("Bleach 1", new Double("8.0"), cat2);
		Item item4 = new Item("Spider-man: Multiverse 1", new Double("4.0"), cat1);
		Item item5 = new Item("Spider-man: Multiverse 2", new Double("4.0"), cat1);
		Item item6 = new Item("Avengers: Infinite war", new Double("10.0"), cat1);
		Item item7 = new Item("Harry Potter: The philosopher's stone", new Double("14.0"), cat3);
		
		Rent rent1 = new Rent(item1, user1, firstDay, lastDay, new Double("50.0"), true, Status.RESERVED);
		Rent rent2 = new Rent(item2, user2, firstDay, lastDay, new Double("20.0"), true, Status.PENDENT);
		Rent rent3 = new Rent(item3, user3, firstDay, lastDay, new Double("30.0"), true, Status.RETURNED);
		Rent rent4 = new Rent(item4, user4, firstDay, lastDay, new Double("110.0"), false, Status.RENTED);
		Rent rent5 = new Rent(item7, user1, firstDay, lastDay, new Double("200.0"), false, Status.RESERVED);		
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		categoryRepository.save(cat1);
		categoryRepository.save(cat2);
		categoryRepository.save(cat3);
		itemRepository.save(item1);
		itemRepository.save(item2);
		itemRepository.save(item3);
		itemRepository.save(item4);
		itemRepository.save(item5);
		itemRepository.save(item6);
		itemRepository.save(item7);
		rentRepository.save(rent1);
		rentRepository.save(rent2);
		rentRepository.save(rent3);
		rentRepository.save(rent4);
		rentRepository.save(rent5);

		
	}

}
