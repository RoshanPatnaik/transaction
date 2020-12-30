package com.org.Transaction.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.Transaction.dao.TransactionDaoImpl;
import com.org.Transaction.model.Transaction;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionDaoImpl dao;
	
	
	public List<Transaction> getTransactionsWithFilters(LocalDate date, String type, LocalDate startDate, LocalDate endDate){
		List<Transaction> list = dao.getAllTransactions();
		if(date != null) {
			list = list.stream().filter(transaction -> transaction.getTransactionDate().equals(date)).collect(Collectors.toList());
		}
		if(!type.equals("")) {
			list = list.stream().filter(transaction -> transaction.getTransactionType().equals(type)).collect(Collectors.toList());
		}
		if(startDate != null && endDate != null) {
			list = list.stream().filter(transaction -> transaction.getTransactionDate().isAfter(startDate) && transaction.getTransactionDate().isBefore(endDate)).collect(Collectors.toList());
		}
		return list;
	}
	public List<Transaction> getAllTransactions(){
		return dao.getAllTransactions();
	}
	public void saveTransactions(List<Transaction> list) {
		dao.saveTransactions(list);
	}
	public void saveTransaction(Transaction transaction) {
		dao.saveTransaction(transaction);
	}
}
