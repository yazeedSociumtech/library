package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.ApiRequestException;
import com.LearningSpring.payroll.models.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository()
public class AuthorDaoImpl implements AuthorDao {

	private HashMap<UUID, Author> DB;

	@Autowired
	public AuthorDaoImpl(HashMap<UUID, Author> DB) {
		this.DB = DB;
	}

	@Override
	public void insertAuthor(UUID id, Author author) {
		Author newAuthor = new Author();
		newAuthor.setId(id);
		newAuthor.setFirstName(author.getFirstName());
		newAuthor.setSecondName(author.getSecondName());
		DB.put(id, newAuthor);
	}

	@Override
	public Author selectAuthorById(UUID id) {
		Author author = DB.get(id);
		if (author == null) {
			throw new ApiRequestException("Author was not found");
		}
		return author;
	}

	@Override
	public Author deleteAuthorById(UUID id) {
		return DB.remove(id);
	}

	@Override
	public Author updateAuthorById(UUID id, Author update) {
		Author author = DB.get(id);
		if (author == null) {
			throw new ApiRequestException("Author was not found");
		}
		author.setFirstName(update.getFirstName());
		author.setSecondName(update.getSecondName());
		return DB.put(id, author);
	}

	@Override
	public List<Author> getAllAuthors() {
		List<Author> authorsList = new ArrayList<>();
		authorsList.addAll(DB.values());
		return authorsList;
	}
}
