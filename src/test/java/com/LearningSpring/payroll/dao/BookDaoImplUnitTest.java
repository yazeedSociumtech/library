package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.ApiRequestException;
import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.models.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookDaoImplUnitTest {
	private HashMap<UUID, Book> fakeDb;
	private BookDaoImpl underTest;

	@BeforeEach
	public void setup() {
		fakeDb = new HashMap<>();
		this.underTest = new BookDaoImpl(fakeDb);
	}

	@AfterEach
	public void afterEach() {
		fakeDb.clear();
	}

	@Test
	void insertBook_validBook_savesBookToDb() {
		Book newBook = createTestBook();
		underTest.insertBook(newBook.getId(), newBook);
		assertEquals(fakeDb.size(), 1);
		Book bookInDB = fakeDb.get(newBook.getId());
		assertEquals(bookInDB.getAuthor(), newBook.getAuthor());
	}

	@Test
	void deleteBook() {
		Book newBook = createTestBook();
		underTest.insertBook(newBook.getId(), newBook);
		Book deletedBook = underTest.deleteBook(newBook.getId());
		assertEquals(fakeDb.size(), 0);
		assertEquals(deletedBook.getAuthor(), newBook.getAuthor());
	}

	@Test
	void selectBookById() {
		Book newBook = createTestBook();
		underTest.insertBook(newBook.getId(), newBook);
		Book selectedBook = underTest.selectBookById(newBook.getId());
		Book bookInDB = fakeDb.get(newBook.getId());
		assertEquals(bookInDB.getAuthor(), newBook.getAuthor());
		assertEquals(bookInDB.getId(), selectedBook.getId());
	}

	@Test
	void updateBookById() {
		Book book = createTestBook();
		Book book2 = createTestBook2();
		underTest.insertBook(book.getId(), book);

		Book updatedBook = underTest.updateBookById(book.getId(), book2);
		Book bookInDB = fakeDb.get(book.getId());

		assertEquals(updatedBook.getId(), book.getId());
		assertEquals(bookInDB.getAuthor(), book2.getAuthor());
	}

	@Test
	void getAllBooks() {
		Book book = createTestBook();
		Book book2 = createTestBook2();
		underTest.insertBook(book.getId(), book);
		underTest.insertBook(book2.getId(), book2);
		List<Book> list = underTest.getAllBooks();
		assertEquals(list.size(), fakeDb.size());
		assertEquals(book2.getId(), fakeDb.get(book2.getId()).getId());
	}

	@Test
	void getBooksByAuthor() {
		Book book = createTestBook();
		Book book2 = createTestBook2();
		underTest.insertBook(book.getId(), book);
		underTest.insertBook(book2.getId(), book2);

		List<Book> bookList = underTest.getBooksByAuthor("yazeed2");

		assertEquals(bookList.get(0).getId(), fakeDb.get(book2.getId()).getId());
	}

	@Test
	void getBooksByName() {
		Book book = createTestBook();
		Book book2 = createTestBook2();
		underTest.insertBook(book.getId(), book);
		underTest.insertBook(book2.getId(), book2);

		List<Book> bookList = underTest.getBooksByName("The One");

		assertEquals(bookList.get(0).getId(), fakeDb.get(book.getId()).getId());
	}

	@Test
	void getBooksByName_nameDoesNotExit_throwsError() {
		Book book = createTestBook();
		Book book2 = createTestBook2();
		underTest.insertBook(book.getId(), book);
		underTest.insertBook(book2.getId(), book2);

		assertThrows(ApiRequestException.class, () -> {
			List<Book> bookList = underTest.getBooksByName("Does Not Exist");
		});
	}

	private Book createTestBook() {
		Author author = new Author();
		author.setFirstName("yazeed");
		author.setSecondName("Aloufee");
		UUID id = UUID.randomUUID();
		author.setId(id);

		UUID bookId = UUID.randomUUID();
		author.setId(id);

		Book book = new Book();
		book.setId(bookId);
		book.setName("The One");
		book.setPrice(15);
		book.setPublished(true);
		book.setAuthor(author);
		book.setCopiesAvailable(123);
		book.setPublishingYear(1990);
		return book;
	}

	private Book createTestBook2() {
		Author author = new Author();
		author.setFirstName("yazeed2");
		author.setSecondName("Aloufee2");
		UUID id = UUID.randomUUID();
		author.setId(id);
		UUID bookId = UUID.randomUUID();
		author.setId(id);

		Book book = new Book();
		book.setId(bookId);
		book.setName("The One2");
		book.setPrice(152);
		book.setPublished(true);
		book.setAuthor(author);
		book.setCopiesAvailable(1232);
		book.setPublishingYear(1994);
		return book;
	}
}