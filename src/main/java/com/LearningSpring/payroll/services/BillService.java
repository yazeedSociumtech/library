package com.LearningSpring.payroll.services;

import com.LearningSpring.payroll.models.Bill;
import com.LearningSpring.payroll.models.Book;
import com.LearningSpring.payroll.models.Person;

import java.util.List;
import java.util.UUID;

public interface BillService {
	 public Bill createBill (UUID[] booksIds, Person buyer);
	public List<Bill> getBillsByPerson (UUID personId);
	public Bill getBillById (UUID billId);
	List <Bill> getAllBills();
}
