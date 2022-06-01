package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.ApiRequestException;
import com.LearningSpring.payroll.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository("fakeDao")
public class PersonDaoImpl implements PersonDao {
	private static HashMap<UUID, Person> DB;

	@Autowired
	public PersonDaoImpl(HashMap<UUID, Person> DB) {
		this.DB = DB;

	}

	@Override
	public Person selectPersonById(UUID id) {
		Person person = DB.getOrDefault(id, null);
		if ( person == null) {
			throw new ApiRequestException("person was not found");
		}
		return person;
	}

	@Override
	public Person deletePersonById(UUID id) {
		if(!DB.containsKey(id)) {
			throw new ApiRequestException(" person already does not exist");
		}
		return DB.remove(id);
	}

	@Override
	public Person updatePersonById(UUID id, Person update) {
		if(!DB.containsKey(id)) {
			throw new ApiRequestException(" person does not exist");
		}
		DB.put(id,update);
		System.out.println(DB.get(id));
		return DB.get(id);
	}

	@Override
	public Person insertPerson(UUID id, Person person) {
		DB.put(id, person);
		return DB.get(id);
	}

	@Override
	public Person[] selectAllPeople() {

		Person[] peopleCollection = new Person[]{};
		Person[] people = DB.values().toArray(peopleCollection);
		return people;
	}


}
