package com.LearningSpring.payroll.controllers;

import com.LearningSpring.payroll.models.ApiRequestException;
import com.LearningSpring.payroll.models.Author;
import com.LearningSpring.payroll.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
	private final AuthorService authorService;

	@Autowired
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@PostMapping("/create")
	public void insertAuthor(@RequestBody Author author) {
		authorService.insertAuthor(author);
	}

	@PostMapping(value = "/create/urlencoded", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void insertAuthorUrlencoded(@Valid Author author, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new ApiRequestException("Author Object is not initiated correctly");
		}
		authorService.insertAuthor(author);
	}

	@GetMapping("/get/{id}")
	public Author getAuthor(@PathVariable("id") UUID id) {
		return authorService.selectAuthorById(id);
	}

	@GetMapping("/get")
	public List<Author> getAllAuthors() {
		return authorService.getAllAuthors();
	}

	@PutMapping("/update/{id}")
	public void updateAuthor(@PathVariable("id") UUID id, @RequestBody Author update) {
		authorService.updateAuthor(id, update);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteAuthor(@PathVariable("id") UUID id) {
		authorService.deleteAuthor(id);
	}

}
