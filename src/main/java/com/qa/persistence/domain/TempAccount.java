package com.qa.persistence.domain;

public class TempAccount {

	private String fName;
	private String lName;
	
	public TempAccount(String fName,String lName) {
		this.fName = fName;
		this.lName = lName;
			
		
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

}
