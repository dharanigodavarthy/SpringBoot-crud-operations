package com.springdemo.userdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springdemo.userdemo.model.Person;
import com.springdemo.userdemo.repository.PersonRepository;

@RestController
@RequestMapping("/api/")
public class PersonController {

	@Autowired
	private PersonRepository personRepository;

	@GetMapping("/persons")
	public List<Person> getAll() {
		return personRepository.findAll();
	}

	@GetMapping("/person/{id}")
	public Optional<Person> getById(@PathVariable Integer id) {
		return personRepository.findById(id);
	}

	@PostMapping("/person")
	public Person persist(@RequestBody Person person) {
		System.out.println(person);
		return personRepository.save(person);
	}

	@PutMapping("/person/{id}")
	public Person update(@PathVariable Integer id, @RequestBody String name) {

		Optional<Person> personIsPresent = personRepository.findById(id);
		if (personIsPresent.isPresent()) {
			Person person = personRepository.getOne(id);
			person.setName(name);
			personRepository.save(person);
			return person;
		} else
			return null;
	}

	@DeleteMapping("/person/{id}")
	public String delete(@PathVariable Integer id) {
		Optional<Person> person = personRepository.findById(id);
		if (person.isPresent()) {
			personRepository.deleteById(id);
			return "person deleted succesfully";
		} else
			return "Person not found";
	}

}
