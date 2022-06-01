package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDao {
	Person insertPerson(UUID id, Person person);

	Person[] selectAllPeople();

	Person selectPersonById(UUID id);

	Person deletePersonById(UUID id);

	Person updatePersonById(UUID id, Person person);
}
