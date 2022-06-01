package com.LearningSpring.payroll.controllers;

import com.LearningSpring.payroll.models.Book;
import com.LearningSpring.payroll.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
	private final BookService bookService;

	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@PostMapping("/create")
	public void createBook(@RequestBody Book book) {
		bookService.insertBook(book);
	}

	@DeleteMapping("/delete/{bookId}")
	public void deleteBook(@PathVariable("bookId") UUID id) {
		bookService.deleteBook(id);
	}

	@PutMapping("/update/{bookId}")
	public void updateBook(@PathVariable("bookId") UUID bookId, @RequestBody Book update) {
		bookService.updateBookById(bookId, update);
	}

	@GetMapping("/get/{bookId}")
	public Book getBook(@PathVariable("bookId") UUID bookId) {
		return bookService.selectBookById(bookId);
	}

	@GetMapping("/getAll")
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@GetMapping("/get/byAuthorName")
	public List<Book> getBooksByAuthorFirstName(@RequestParam("name") String authorFirstName) {
		return bookService.getBooksByAuthorFirstName(authorFirstName);
	}

	@GetMapping("get/byBookName")
	public List<Book> getBooksByName(@RequestParam("name") String bookName) {
		return bookService.getBooksByName(bookName);
	}
}
