package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.models.Book;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AuthorService {
	void insertAuthor(Author author);

	Author selectAuthorById(UUID id);

	Author deleteAuthor(UUID id);

	Author updateAuthor(UUID id, Author update);


	List<Author> getAllAuthors();
}
