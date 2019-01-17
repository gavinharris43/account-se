package com.qa.persistence.repository;
import java.util.HashMap;
import java.util.Map;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;
import java.util.Map;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.domain.Account;
import com.qa.persistence.domain.TempAccount;
// import com.qa.business.service.AccountService;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	
	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllAccounts() {
		
	Query query = manager.createQuery("Select a FROM Account a");
	Collection<Account> accounts = (Collection<Account>) query.getResultList();
	return util.getJSONForObject(accounts);
	}
	
	@Override
	@Transactional(REQUIRED)
	public String createAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"Account has been sucessfully added\"}";
	}
	
	@Override
	@Transactional(REQUIRED)
	public String deleteAccount(int accountID, String lName) {
		Account accountInDB = findAccount(accountID);
	if (accountInDB != null && accountInDB.getSecondName()==lName ) {
			manager.remove(accountInDB);
		return "{\"message\": \"Account sucessfully deleted\"}";
		}
		return "{\"message\": \"Account not found\"}";
		
	}
	@Override
	@Transactional(REQUIRED)
	public String updateAccount(int accountID, String account) {
		Account accountInDB = findAccount(accountID);
	if (accountInDB != null) {
		TempAccount accountN = util.getObjectForJSON(account, TempAccount.class); 
		accountInDB.setFirstName(accountN.getfName());
		accountInDB.setSecondName(accountN.getlName());
		manager.refresh(accountInDB);
		return "{\"message\": \"Account sucessfully updated\"}";
		}
		return "{\"message\": \"Account not found\"}";
		
	}

	private Account findAccount(int accountId) {
		return manager.find(Account.class, accountId);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}




}
