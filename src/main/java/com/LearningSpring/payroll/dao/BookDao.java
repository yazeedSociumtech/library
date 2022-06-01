package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.Book;

import java.util.List;
import java.util.UUID;

public interface BookDao {
	Book insertBook(UUID id, Book book);

	Book deleteBook(UUID id);

	Book selectBookById(UUID id);

	List<Book> selectBooksByListIds(UUID[] uuids);

	Book updateBookById(UUID id, Book update);

	List<Book> getAllBooks();

	List<Book> getBooksByAuthor(String authorName);

	List<Book> getBooksByName(String bookName);

}
