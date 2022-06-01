package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.dao.AuthorDao;
import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service()
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDao authorDao;

	@Override
	public void insertAuthor(Author author) {
		UUID id = UUID.randomUUID();
		authorDao.insertAuthor(id, author);
	}

	@Override
	public Author selectAuthorById(UUID id) {
		return authorDao.selectAuthorById(id);
	}

	@Override
	public Author deleteAuthor(UUID id) {
		return authorDao.deleteAuthorById(id);
	}

	@Override
	public Author updateAuthor(UUID id, Author update) {
		return authorDao.updateAuthorById(id, update);
	}

	@Override
	public List<Author> getAllAuthors() {
		return authorDao.getAllAuthors();
	}
}
