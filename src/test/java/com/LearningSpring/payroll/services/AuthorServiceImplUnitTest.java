package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.dao.AuthorDao;
import com.LearningSpring.payroll.models.Author;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(MockitoJUnitRunner.StrictStubs.class)
//@ExtendWith(MockitoExtension.class)
class AuthorServiceImplUnitTest {
	@Mock
	private AuthorDao authorDao;

	@InjectMocks
	private AuthorServiceImpl underTest;

	@Test
	void insertAuthor_verifiedAuthor_daoAuthorInsertAuthorIsCalled() {
		Author author = new Author();
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");
		ArgumentCaptor<Author> authorArgumentCapture =
				ArgumentCaptor.forClass(Author.class);

		ArgumentCaptor<UUID> uuidArgumentCaptor =
				ArgumentCaptor.forClass(UUID.class);

		underTest.insertAuthor(author);
		verify(authorDao)
				.insertAuthor(uuidArgumentCaptor.capture(), authorArgumentCapture.capture());
	}

	@Test
	void selectAuthorById_UUIDIsPassed_callSelectAuthorByIdWithId() {
		UUID id = UUID.randomUUID();
		underTest.selectAuthorById(id);
		verify(authorDao).selectAuthorById(id);
	}

	@Test
	void deleteAuthor() {
		UUID id = UUID.randomUUID();
		underTest.deleteAuthor(id);
		verify(authorDao).deleteAuthorById(id);
	}

	@Test
	void updateAuthor() {
		Author author = new Author();
		UUID id = UUID.randomUUID();
		author.setId(id);
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");
		underTest.updateAuthor(id, author);
		verify(authorDao).updateAuthorById(id, author);
	}

	@Test
	void getAllAuthors_whenAuthorsExist_returnAllAuthors() {
		underTest.getAllAuthors();
		verify(authorDao).getAllAuthors();
	}
}