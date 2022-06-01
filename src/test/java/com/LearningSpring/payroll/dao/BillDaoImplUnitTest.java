package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.models.Bill;
import com.LearningSpring.payroll.models.Book;
import com.LearningSpring.payroll.models.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BillDaoImplUnitTest {
	private HashMap<UUID, Bill> fakeDb;

	private BillDaoImpl underTest;

	@BeforeEach
	public void setup() {
		fakeDb = new HashMap<>();
		this.underTest = new BillDaoImpl(fakeDb);
	}

	@AfterEach
	public void afterEach() {
		fakeDb.clear();
	}

	@Test
	void createBill_createNewBill_databaseSizeIncrease() {
		Bill newBill = new Bill();
		newBill.setPrice(20);
		UUID id = UUID.randomUUID();
		underTest.createBill(id, newBill);
		assertEquals(fakeDb.size(), 1);
		assertEquals(fakeDb.get(id).getPrice(), 20);
	}

	@Test
	void getBillsByPerson() {
		Bill newBill = createFakeBill();
		Bill newBill2 = createFakeBill();
		UUID id = newBill2.getBuyer().getId();
		fakeDb.put(newBill.getId(), newBill);
		fakeDb.put(newBill.getId(), newBill2);
		List<Bill> billList = underTest.getBillsByPerson(id);
		assertEquals(billList.size(), 1);
	}

	@Test
	void getBillById() {
		Bill newBill = createFakeBill();
		Bill newBill2 = createFakeBill();
		UUID id = newBill2.getId();
		fakeDb.put(newBill.getId(), newBill);
		fakeDb.put(newBill2.getId(), newBill2);
		Bill bill = underTest.getBillById(id);
		assertEquals(bill.getId(), id);
		assertEquals(bill.getBuyer().getFirstName(), "yazeed");
	}

	@Test
	void getAllBills() {
		Bill newBill = createFakeBill();
		Bill newBill2 = createFakeBill();
		Bill newBill3 = createFakeBill();
		UUID id = newBill2.getId();
		fakeDb.put(newBill.getId(), newBill);
		fakeDb.put(newBill2.getId(), newBill2);
		fakeDb.put(newBill3.getId(), newBill3);
		List<Bill> list = underTest.getAllBills();
		assertEquals(list.size(), 3);
	}

	private Bill createFakeBill() {
		Bill newBill = new Bill();
		Person person = createFakePerson();
		List<Book> listOfBooks = createFakeBooks();
		UUID id = UUID.randomUUID();
		newBill.setPrice(20);
		newBill.setBuyer(person);
		newBill.setListOfBooks(listOfBooks);
		newBill.setId(id);
		return newBill;

	}

	private Person createFakePerson() {
		Person person = new Person();
		person.setId(UUID.randomUUID());
		person.setFirstName("yazeed");
		person.setSecondName("Hasan");
		person.setLastName("hasan");
		person.setPhoneNumber("0791102951");
		return person;
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