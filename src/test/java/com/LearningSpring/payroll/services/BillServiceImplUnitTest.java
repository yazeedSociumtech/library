package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.dao.BillDao;
import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.models.Bill;
import com.LearningSpring.payroll.models.Book;
import com.LearningSpring.payroll.models.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.StrictStubs.class)
class BillServiceImplUnitTest {

	@Mock
	private BillDao billDao;
	@Mock
	private BookService bookService;
	@InjectMocks
	private BillServiceImpl billServiceImpl;

	@Test
	void createBill() {
		Person person = new Person();
		person.setId(UUID.randomUUID());
		person.setFirstName("yazeed");
		person.setSecondName("Hasan");
		person.setLastName("hasan");
		person.setPhoneNumber("0791102951");
		UUID[] uuids = new UUID[]{UUID.randomUUID(), UUID.randomUUID()};
		List<Book> listOfBooks = createFakeBooks();
		when(bookService.selectBooksByListIds(uuids)).thenReturn(listOfBooks);

		ArgumentCaptor<Bill> billArgumentCaptor =
				ArgumentCaptor.forClass(Bill.class);
		ArgumentCaptor<UUID> uuidArgumentCaptor =
				ArgumentCaptor.forClass(UUID.class);
		billServiceImpl.createBill(uuids, person);

		verify(billDao).createBill(uuidArgumentCaptor.capture(), billArgumentCaptor.capture());
	}

	@Test
	void getBillsByPerson() {
		UUID id = UUID.randomUUID();
		billServiceImpl.getBillsByPerson(id);
		verify(billDao).getBillsByPerson(id);
	}

	@Test
	void getBillById() {
		UUID id = UUID.randomUUID();
		billServiceImpl.getBillById(id);
		verify(billDao).getBillById(id);
	}

	@Test
	void getAllBills() {
		billServiceImpl.getAllBills();
		verify(billDao).getAllBills();
	}

	private List<Book> createFakeBooks() {
		List<Book> listOfBooks = new ArrayList<>();
		listOfBooks.add(createTestBook());
		listOfBooks.add(createTestBook2());
		return listOfBooks;
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