package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.ApiRequestException;
import com.LearningSpring.payroll.models.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository()
public class BookDaoImpl implements BookDao {
	HashMap<UUID, Book> DB;

	public BookDaoImpl(HashMap<UUID, Book> fakeDB) {
		this.DB = fakeDB;
	}

	@Override
	public Book insertBook(UUID id, Book book) {
		Book newBook = new Book();
		newBook.setId(id);
		newBook.setPublishingYear(book.getPublishingYear());
		newBook.setAuthor(book.getAuthor());
		newBook.setPublished(book.isPublished());
		newBook.setPrice(book.getPrice());
		newBook.setCopiesAvailable(book.getCopiesAvailable());
		newBook.setName(book.getName());
		return DB.put(id, newBook);
	}

	@Override
	public Book deleteBook(UUID id) {
		return DB.remove(id);
	}

	@Override
	public Book selectBookById(UUID id) {
		return DB.get(id);
	}

	public List<Book> selectBooksByListIds(UUID[] uuids) {
		List<Book> list = new ArrayList<>();
		Book book = new Book();
		for (UUID id : uuids) {
			book = DB.get(id);
			System.out.println(book);
			if (book == null) {
				throw new ApiRequestException("Book has invalid id");
			}
			list.add(book);
		}
		return list;
	}


	@Override
	public Book updateBookById(UUID id, Book update) {
		Book book = DB.get(id);
		book.setAuthor(update.getAuthor());
		book.setCopiesAvailable(update.getCopiesAvailable());
		book.setName(update.getName());
		book.setPublished(update.isPublished());
		book.setPrice(update.getPrice());
		return DB.put(id, book);
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> allBooks = new ArrayList<Book>();
		for (Book book : DB.values()) {
			allBooks.add(book);
		}
		return allBooks;
	}

	@Override
	public List<Book> getBooksByAuthor(String authorFirstName) {
		List<Book> books = new ArrayList<Book>();
		for (Book book : DB.values()) {
			if (book.getAuthor().getFirstName().equals(authorFirstName)) {
				books.add(book);
			}
		}
		return books;
	}

	@Override
	public List<Book> getBooksByName(String bookName) {
		List<Book> books = new ArrayList<Book>();
		for (Book book : DB.values()) {
			if (book.getName().equals(bookName)) {
				books.add(book);
			}
		}
		if (books.size() == 0) {
			throw new ApiRequestException("book with that name does not exist");
		}
		return books;
	}
}
