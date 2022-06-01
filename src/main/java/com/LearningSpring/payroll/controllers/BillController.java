package com.LearningSpring.payroll.controllers;

import com.LearningSpring.payroll.models.Bill;
import com.LearningSpring.payroll.models.Person;
import com.LearningSpring.payroll.services.BillService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/bill")
public class BillController {
	@Autowired
	private BillService billService;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public Bill createBill(@RequestParam UUID[] books, @RequestParam String personJSON) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Person person = objectMapper.readValue(personJSON, Person.class);
		return billService.createBill(books, person);
	}


	@GetMapping("/byPerson/{id}")
	public List<Bill> getBillsByPerson(@PathVariable("id") UUID personId) {
		return billService.getBillsByPerson(personId);
	}

	@GetMapping("/byId/{id}")
	public Bill getBillById(@PathVariable("id") UUID billId) {
		return billService.getBillById(billId);
	}

	@GetMapping("/getAll")
	public List<Bill> getAllBills() {
		return billService.getAllBills();
	}

	private static class RequestBill {
		public static UUID[] booksIds;
		public static Person buyer;

		public RequestBill(UUID[] ids, Person buyer) {
			this.booksIds = ids;
			this.buyer = buyer;
		}

		public UUID[] getBooksIds() {
			return booksIds;
		}

		public void setBooksIds(UUID[] booksIds) {
			this.booksIds = booksIds;
		}

		public Person getBuyer() {
			return buyer;
		}

		public void setBuyer(Person buyer) {
			this.buyer = buyer;
		}
	}


}
