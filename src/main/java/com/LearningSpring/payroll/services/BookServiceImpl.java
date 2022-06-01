package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.dao.BookDao;
import com.LearningSpring.payroll.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service()
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;


	@Override
	public Book insertBook(Book book) {
		UUID id = UUID.randomUUID();
		return bookDao.insertBook(id, book);
	}

	@Override
	public Book deleteBook(UUID id) {
		return bookDao.deleteBook(id);
	}

	@Override
	public Book selectBookById(UUID id) {
		return bookDao.selectBookById(id);
	}

	@Override
	public Book updateBookById(UUID id, Book update) {
		return bookDao.updateBookById(id, update);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	}

	@Override
	public List<Book> getBooksByAuthorFirstName(String AuthorFirstName) {
		return bookDao.getBooksByAuthor(AuthorFirstName);
	}

	@Override
	public List<Book> selectBooksByListIds(UUID[] uuids) {
		return bookDao.selectBooksByListIds(uuids);
	}

	@Override
	public List<Book> getBooksByName(String bookName) {
		return bookDao.getBooksByName(bookName);
	}
}
