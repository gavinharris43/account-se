package com.qa.persistence.repository;
import java.util.HashMap;
import java.util.Map;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.Map;


import javax.enterprise.inject.Alternative;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;

import javax.transaction.Transactional;


import com.google.gson.Gson;
import com.qa.domain.Account;
import com.qa.persistence.domain.TempAccount;
// import com.qa.business.service.AccountService;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Alternative
@ApplicationScoped
public class AccountMapRepository implements AccountRepository {
	
//	@PersistenceContext(unitName = "primary")
// 	@Inject
	private Map<Integer, Account> accountMap = new HashMap<>();
	private int count = 0;
	
	@Inject
	private JSONUtil util;


	public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
		return (int) accountMap.values().stream()
				.filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
		
	}

	@Override
	public String getAllAccounts() {
		return util.getJSONForObject(accountMap);
	}

	@Override
	public String createAccount(String account) {
		accountMap.put(count,util.getObjectForJSON(account, Account.class));
		count++;
		return "{\"message\": \"Account sucessfully updated to Map\"}" ;
	}

	@Override
	public String deleteAccount(int accountID, String lName) {
		boolean accountExists = accountMap.containsKey(accountID);
		if (accountExists) {
			accountMap.remove(accountID);
		}
		return  "{\"message\": \"Account sucessfully deleted from Map\"}";
	}

	@Override
	public String updateAccount(int accountID, String account) {
		boolean countExists = accountMap.containsKey(accountID);
		if (countExists) {
			Account editAcc =accountMap.get(accountID);
			TempAccount accountN = util.getObjectForJSON(account, TempAccount.class); 
			String fName=accountN.getfName()  ;
			String lName= accountN.getlName();
			editAcc.setFirstName(fName);
			editAcc.setSecondName(lName);
			accountMap.replace(accountID, editAcc);
		}
		return  "{\"message\": \"Account sucessfully deleted from Map\"}";
	
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}
