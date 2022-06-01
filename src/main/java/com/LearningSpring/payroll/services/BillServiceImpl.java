package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.dao.BillDao;
import com.LearningSpring.payroll.models.Bill;
import com.LearningSpring.payroll.models.Book;
import com.LearningSpring.payroll.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;
	@Autowired
	private BookService bookService;


	@Override
	public Bill createBill(UUID[] booksIds, Person buyer) {
		Bill newBill = new Bill();
		UUID newBillId = UUID.randomUUID();
		HashMap<String, UUID> bookListHash = new HashMap<>();
		List<Book> bookList = bookService.selectBooksByListIds(booksIds);
		List<Book> listOfBooks = new ArrayList<>();
		int totalPrice = 0;

		for (Book book : bookList) {
			bookListHash.put(book.getName(), book.getId());
			totalPrice += book.getPrice();
		}

		for (Book book : bookList) {
			Book newBook = new Book();
			newBook.setName(book.getName());
			newBook.setPrice(book.getPrice());
			newBook.setId(book.getId());
			listOfBooks.add(newBook);

		}
		newBill.setPrice(totalPrice);
		newBill.setBookList(bookListHash);
		newBill.setBuyer(buyer);
		newBill.setId(newBillId);
		newBill.setIssuingDate(new Date());
		newBill.setListOfBooks(listOfBooks);
		return billDao.createBill(newBillId, newBill);
	}

	@Override
	public List<Bill> getBillsByPerson(UUID personId) {
		return billDao.getBillsByPerson(personId);
	}

	@Override
	public Bill getBillById(UUID billId) {
		return billDao.getBillById(billId);
	}

	@Override
	public List<Bill> getAllBills() {
		return billDao.getAllBills();
	}
}
