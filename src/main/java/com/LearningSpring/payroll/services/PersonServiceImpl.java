package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.dao.PersonDao;
import com.LearningSpring.payroll.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component("defaultPersonService")
@Service
public class PersonServiceImpl implements PersonService {
	private final PersonDao personDao;

	@Autowired
	public PersonServiceImpl(@Qualifier("fakeDao") PersonDao personDao) {
		this.personDao = personDao;
	}

	public Person addPerson(Person person) {
		UUID id = UUID.randomUUID();
		Person personDB = person;
		personDB.setId(id);
		return personDao.insertPerson(id, personDB);
	}

	public Person[] getAllPeople() {
		return personDao.selectAllPeople();
	}

	public Person getPersonById(UUID id) {
		return personDao.selectPersonById(id);
	}

	public Person deletePerson(UUID id) {
		return personDao.deletePersonById(id);
	}

	public Person updatePerson(UUID id, Person newPerson) {
		newPerson.setId(id);
		return personDao.updatePersonById(id, newPerson);
	}
}
