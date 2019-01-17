package com.qa.persistence.repository;

import java.util.Map;


public interface AccountRepository {

	String getAllAccounts();
	// String createAccount(String fName,String lName);
	String deleteAccount(int accountID, String lName);
	String updateAccount(int accountID, String account);
	String createAccount(String account);
}