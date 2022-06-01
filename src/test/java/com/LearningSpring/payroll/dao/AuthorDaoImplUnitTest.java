package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.ApiRequestException;
import com.LearningSpring.payroll.models.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuthorDaoImplUnitTest {
	private HashMap<UUID, Author> fakeDb;
	private AuthorDaoImpl underTest;

	@BeforeEach
	public void setup() {
		fakeDb = new HashMap<>();
		this.underTest = new AuthorDaoImpl(fakeDb);
	}

	@AfterEach
	public void afterEach() {
		fakeDb.clear();
	}

	@Test
	public void insertAuthor_savingAuthor_AuthorExistsInDB() {
		Author author = new Author();
		UUID id = UUID.randomUUID();
		author.setId(id);
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");
		underTest.insertAuthor(id, author);
		Assertions.assertEquals(fakeDb.size(), 1);
	}

	@Test
	public void selectAuthorById_idExists_returnsAuthor() {
		Author author = new Author();
		UUID id = UUID.randomUUID();
		author.setId(id);
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");
		underTest.insertAuthor(id, author);
		Author savedAuthor = underTest.selectAuthorById(id);
		Assertions.assertEquals(fakeDb.size(), 1);
		Author AuthorDB = fakeDb.get(id);
		Assertions.assertEquals(savedAuthor.getId(), author.getId());
		Assertions.assertEquals(AuthorDB.getId(), author.getId());
	}

	@Test
	public void selectAuthorById_idDoesNotExist_throwsException() {
		Author author = new Author();
		UUID id = UUID.randomUUID();
		UUID idDoesNotExist = UUID.randomUUID();
		author.setId(id);
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");
		underTest.insertAuthor(id, author);
		assertThrows(ApiRequestException.class, () -> {
			Author savedAuthor = underTest.selectAuthorById(idDoesNotExist);
		});

	}

	@Test
	void deleteAuthorById() {
		Author author = new Author();
		UUID id = UUID.randomUUID();
		author.setId(id);
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");
		underTest.insertAuthor(id, author);
		underTest.deleteAuthorById(id);
		Assertions.assertEquals(fakeDb.size(), 0);
	}

	@Test
	void updateAuthorById_validId_updateAuthor() {
		Author author = new Author();
		UUID id = UUID.randomUUID();
		author.setId(id);
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");

		Author author2 = new Author();
		author2.setFirstName("updated");
		author2.setSecondName("updatedSecond");

		underTest.insertAuthor(id, author);
		underTest.updateAuthorById(id, author2);
		Assertions.assertEquals(fakeDb.size(), 1);
		Author updatedAuthor = fakeDb.get(id);
		Assertions.assertEquals(updatedAuthor.getId(), id);
		Assertions.assertEquals(updatedAuthor.getFirstName(), author2.getFirstName());
	}

	@Test
	public void updateAuthorById_invalidId_throwsException() {

		Author author = new Author();
		UUID id = UUID.randomUUID();
		author.setId(id);
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");

		UUID invalidID = UUID.randomUUID();

		assertThrows(ApiRequestException.class, () -> {
			underTest.updateAuthorById(invalidID, author);
		});
	}

	@Test
	void getAllAuthors_twoAuthorsAreAdded_listOfAuthors() {
		Author author = new Author();
		UUID id = UUID.randomUUID();
		author.setId(id);
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");

		Author author2 = new Author();
		UUID id2 = UUID.randomUUID();
		author2.setFirstName("updatedFirst");
		author2.setSecondName("updatedSecond");

		underTest.insertAuthor(id, author);
		underTest.insertAuthor(id2, author2);

		Assertions.assertEquals(fakeDb.size(), 2);
		List<Author> list = underTest.getAllAuthors();
		Assertions.assertEquals(list.get(1).getFirstName(), "updatedFirst");
		Assertions.assertEquals(list.get(0).getId(), id);
	}
}