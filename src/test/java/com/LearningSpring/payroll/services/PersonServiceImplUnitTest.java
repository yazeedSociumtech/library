package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.dao.PersonDao;
import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplUnitTest {
	@Mock
	private PersonDao personDao;

	@InjectMocks
	private PersonServiceImpl underTest;

	@Test
	void addPerson() {
		Person person = new Person();
		person.setFirstName("yazeed");
		person.setLastName("Aloufee");
		underTest.addPerson(person);
		ArgumentCaptor<Person> personArgumentCaptor =
				ArgumentCaptor.forClass(Person.class);

		ArgumentCaptor<UUID> uuidArgumentCaptor =
				ArgumentCaptor.forClass(UUID.class);
		verify(personDao).insertPerson(uuidArgumentCaptor.capture(), personArgumentCaptor.capture());
	}

	@Test
	void getAllPeople() {
		underTest.getAllPeople();
		verify(personDao).selectAllPeople();
	}

	@Test
	void getPersonById() {
		UUID id = UUID.randomUUID();
		underTest.getPersonById(id);
		verify(personDao).selectPersonById(id);
	}

	@Test
	void deletePerson() {
		UUID id = UUID.randomUUID();
		underTest.deletePerson(id);
		verify(personDao).deletePersonById(id);
	}

	@Test
	void updatePerson() {
		Person person = new Person();
		UUID id = UUID.randomUUID();
		person.setId(id);
		person.setFirstName("yazeed");
		underTest.updatePerson(id, person);
		verify(personDao).updatePersonById(id, person);
	}
}