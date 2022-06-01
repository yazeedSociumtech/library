package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.dao.BookDao;
import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.models.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookServiceImplUnitTest {

	@Mock
	private BookDao bookDao;

	@InjectMocks
	private BookServiceImpl underTest;

	@Test
	void insertBook_insertValidBook_insertMethodIsCalled() {
		Book book = createTestBook();
		ArgumentCaptor<Book> bookArgumentCaptor =
				ArgumentCaptor.forClass(Book.class);
		ArgumentCaptor<UUID> uuidArgumentCaptor =
				ArgumentCaptor.forClass(UUID.class);

		underTest.insertBook(book);
		verify(bookDao)
				.insertBook(uuidArgumentCaptor.capture(), bookArgumentCaptor.capture());
	}

	@Test
	void deleteBook_validId_deleteMethodIsCalled() {
		UUID id = UUID.randomUUID();
		underTest.deleteBook(id);
		verify(bookDao).deleteBook(id);
	}

	@Test
	void selectBookById() {
		UUID id = UUID.randomUUID();
		underTest.selectBookById(id);
		verify(bookDao).selectBookById(id);
	}

	@Test
	void updateBookById() {
		Book book = createTestBook();
		UUID id = UUID.randomUUID();
		underTest.updateBookById(id, book);
		verify(bookDao).updateBookById(id, book);
	}

	@Test
	void getAllBooks() {
		underTest.getAllBooks();
		verify(bookDao).getAllBooks();
	}

	@Test
	void getBooksByAuthorFirstName() {
		String authorName = "Yazeed";
		underTest.getBooksByAuthorFirstName(authorName);
		verify(bookDao).getBooksByAuthor(authorName);
	}

	@Test
	void getBooksByName() {
		String bookName = "The One";
		underTest.getBooksByName(bookName);
		verify(bookDao).getBooksByName(bookName);
	}

	private Book createTestBook() {
		Author author = new Author();
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");
		UUID id = UUID.randomUUID();
		author.setId(id);

		Book book = new Book();
		book.setName("The One");
		book.setPrice(15);
		book.setPublished(true);
		book.setAuthor(author);
		book.setCopiesAvailable(123);
		book.setPublishingYear(1992);
		return book;
	}
}