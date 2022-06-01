package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonDaoImplUnitTest {

	private HashMap<UUID, Person> fakeDb;
	private PersonDaoImpl underTest;

	@BeforeEach
	public void setup() {
		fakeDb = new HashMap<>();
		this.underTest = new PersonDaoImpl(fakeDb);
	}

	@AfterEach
	public void afterEach() {
		fakeDb.clear();
	}

	@Test
	void selectPersonById() {
		Person person = createTestPerson1();
		UUID id = person.getId();
		underTest.insertPerson(id, person);
		Person savedPerson = underTest.selectPersonById(id);
		assertEquals(savedPerson.getFirstName(), "Yazeed");
		assertEquals(savedPerson.getLastName(), "Aloufee");
	}

	@Test
	void deletePersonById() {
		Person person = createTestPerson1();
		UUID id = person.getId();
		underTest.insertPerson(id, person);
		assertEquals(fakeDb.size(), 1);
		underTest.deletePersonById(id);
		assertEquals(fakeDb.size(), 0);
	}

	@Test
	void updatePersonById() {
		Person person = createTestPerson1();
		Person updatedPerson = createTestPerson2();
		UUID id = person.getId();
		underTest.insertPerson(id, person);
		underTest.updatePersonById(id, updatedPerson);

		Person savedPerson = fakeDb.get(id);
		assertEquals(savedPerson.getPhoneNumber(), "2222222222");
	}

	@Test
	void insertPerson_validPerson_DBSizeIncreases() {
		Person person = createTestPerson1();
		UUID id = person.getId();
		underTest.insertPerson(id, person);
		assertEquals(fakeDb.size(), 1);
	}

	@Test
	void selectAllPeople() {
		Person person1 = createTestPerson1();
		UUID id1 = person1.getId();
		Person person2 = createTestPerson2();
		UUID id2 = person2.getId();
		underTest.insertPerson(id1, person1);
		underTest.insertPerson(id2, person2);

		Person[] listOfPeople = underTest.selectAllPeople();
		assertEquals(listOfPeople.length, 2);
	}

	private Person createTestPerson1() {
		Person person = new Person();
		person.setFirstName("Yazeed");
		person.setSecondName("Hasan");
		person.setLastName("Aloufee");
		person.setPhoneNumber("0795246547");
		UUID id = UUID.randomUUID();
		person.setId(id);
		return person;
	}

	private Person createTestPerson2() {
		Person person = new Person();
		person.setFirstName("Yazeed2");
		person.setSecondName("Hasan2");
		person.setLastName("Aloufee2");
		person.setPhoneNumber("2222222222");
		UUID id = UUID.randomUUID();
		person.setId(id);
		return person;
	}
}