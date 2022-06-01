package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorDao {

	void insertAuthor(UUID id, Author author);

	Author selectAuthorById(UUID id);

	Author deleteAuthorById(UUID id);

	Author updateAuthorById(UUID id, Author update);

	List<Author> getAllAuthors();
}
