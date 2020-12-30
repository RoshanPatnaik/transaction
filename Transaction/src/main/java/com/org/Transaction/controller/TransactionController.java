package com.org.Transaction.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonValue;
import com.org.Transaction.model.Transaction;
import com.org.Transaction.service.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
	@RequestMapping("/index")
	public ModelAndView indexPage() {
		ModelAndView mav = new ModelAndView("index");
		return mav;
	}
	
	@RequestMapping(value = "/filters", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getTransactionsWithFilters(@RequestParam("date") @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date, @RequestParam("type") String type,@RequestParam("startDate") @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate, @RequestParam("endDate") @Nullable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
		List<Transaction> list = service.getTransactionsWithFilters(date, type, startDate, endDate);
		return list;
	}
	
//	@RequestMapping("/save")
//	public void saveTransactions() {
//		LocalDate date = LocalDate.now();
//		Transaction t1 = new Transaction();
//		Transaction t2 = new Transaction();
//		t2.setUserId(2);
//		t2.setSenderAccountNumber(192837465);
//		t2.setTransactionAmount(20000);
//		t2.setTransactionDate(date);
//		t2.setTransactionType("RTGS");
//		t2.setBeneficiaryAccountNumber(546789012);
//		service.saveTransaction(t2);
//	}
	
}
