package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.models.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonService{


	public Person addPerson(Person person);

	public Person[] getAllPeople();

	public Person getPersonById(UUID id);

	public Person deletePerson(UUID id);

	public Person updatePerson(UUID id, Person newPerson);
}
