package com.qa.service;

import java.util.Map;

public interface AccountService  {

	String getAllAccounts();

	String deleteAccount(int accountNo, String lName);

	String updateAccount(int id, String account);

	String addAccount(String account);
	

	
	
}
