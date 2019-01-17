package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.apache.log4j.Logger;

import com.qa.service.AccountService;
@Path("/account")
public class AccountEndpoint {

	@Inject
	private AccountService service;
	
	@Path("/getAllAccounts")
	@GET
	@Produces({ "application/json" })
	public String getAllAccounts() {
		return service.getAllAccounts();
	}

	@Path("/createAccount")
	@POST
	@Produces({ "application/json" })
	public String addAccount(String account) {
		return service.addAccount(account);
	}

	@Path("/deleteAccount/{id}")
	@DELETE
	@Produces({ "application/json" })
	public String deleteAccount(@PathParam("id") int id, String lname) {
		return service.deleteAccount(id, lname);
	}
	
	@Path("/updateAccount/{id}")
	@PUT
	@Produces({ "application/json" })
	public String updateAccount(@PathParam("id") int id, String accountName) {
		//account== fname + lname 
		return service.updateAccount(id, accountName);
	}

	public void setService(AccountService service) {
		this.service = service;
	}

}
