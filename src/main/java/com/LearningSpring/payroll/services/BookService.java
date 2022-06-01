package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.models.Book;

import java.util.List;
import java.util.UUID;

public interface BookService {

	Book insertBook(Book book);

	Book deleteBook(UUID id);

	Book selectBookById(UUID id);
	List<Book> selectBooksByListIds(UUID[] uuids);

	Book updateBookById(UUID id, Book update);
	List<Book> getAllBooks();
	List<Book> getBooksByAuthorFirstName(String authorFirstName);

	List<Book> getBooksByName(String bookName);

}
