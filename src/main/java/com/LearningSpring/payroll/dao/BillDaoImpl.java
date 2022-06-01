package com.LearningSpring.payroll.dao;

import com.LearningSpring.payroll.models.ApiRequestException;
import com.LearningSpring.payroll.models.Bill;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Repository
public class BillDaoImpl implements BillDao {
	private HashMap<UUID, Bill> DB;

	public BillDaoImpl(HashMap<UUID, Bill> fakeDb) {
		this.DB = fakeDb;
	}

	@Override
	public Bill createBill(UUID id, Bill bill) {
		return DB.put(id, bill);
	}

	@Override
	public List<Bill> getBillsByPerson(UUID personId) {
		List<Bill> billsList = new ArrayList<>();
		for (Bill bill : DB.values()) {
			if (bill.getBuyer().getId().equals(personId)) {
				billsList.add(bill);
			}
		}
		if (billsList.size() == 0) {
			throw new ApiRequestException("no bills associated with that person");
		}
		return billsList;
	}

	public Bill getBillById(UUID billId) {
		Bill bill = DB.getOrDefault(billId, null);
		if (bill == null) {
			throw new ApiRequestException("no bill is found");
		}
		return bill;
	}

	public List<Bill> getAllBills() {
		List<Bill> billsList = new ArrayList<>();
		for (Bill bill : DB.values()) {
			billsList.add(bill);
		}
		if (billsList.size() == 0) {
			throw new ApiRequestException("no bills are available");
		}
		return billsList;
	}
}
