package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.Bill;

import java.util.List;
import java.util.UUID;

public interface BillDao {
	public Bill createBill(UUID id, Bill bill);

	public List<Bill> getBillsByPerson(UUID personId);

	public Bill getBillById(UUID billId);

	List<Bill> getAllBills();
}
