package com.LearningSpring.payroll.controllers;

import com.LearningSpring.payroll.models.Person;
import com.LearningSpring.payroll.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RequestMapping("/api/v1/person")
@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping("/create")
	public Person addPerson(@RequestBody Person person) {
		return personService.addPerson(person);
	}

	@GetMapping("getAll")
	public Person[] getAllPeople() {
		return personService.getAllPeople();
	}

	@GetMapping("/byId/{id}")
	public Person getPersonById(@PathVariable("id") UUID id) {
		return personService.getPersonById(id);
	}

	@GetMapping(path = "/query")
	public Person getPersonByIdQuery(@RequestParam("id") UUID id) {
		return personService.getPersonById(id);
	}

	@DeleteMapping("/delete/{id}")
	public Person deletePerson(@PathVariable("id") UUID id) {
		return personService.deletePerson(id);
	}

	@PutMapping("/update/{id}")
	public Person updatePerson(@PathVariable("id") UUID id, @RequestBody Person newPerson) {
		return personService.updatePerson(id, newPerson);
	}
}
