package com.LearningSpring.payroll.models;

import java.lang.reflect.Array;
import java.util.*;

public class Bill {

	private UUID id;
	private Person buyer;
	private HashMap<String, UUID> bookList;
	private List<Book> listOfBooks;
	private Date issuingDate;

	private float price;

	public Person getBuyer() {
		return buyer;
	}

	public void setBuyer(Person buyer) {
		this.buyer = buyer;
	}

	public HashMap<String, UUID> getBookList() {
		return bookList;
	}

	public void setBookList(HashMap<String, UUID> bookList) {
		this.bookList = bookList;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getIssuingDate() {
		return issuingDate;
	}

	public void setIssuingDate(Date issuingDate) {
		this.issuingDate = issuingDate;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public List<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(List<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}
}
