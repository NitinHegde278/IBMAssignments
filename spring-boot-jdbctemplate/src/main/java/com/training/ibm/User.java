package com.training.ibm;

public class User {
	String accountId;
	String accountName;
	int accountBalance;
	String accountPin;
	
	public User() { };
	
	public User(String accountId, String accountName, int accountBalance, String accountPin) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountBalance = accountBalance;
		this.accountPin = accountPin;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountPin() {
		return accountPin;
	}

	public void setAccountPin(String accountPin) {
		this.accountPin = accountPin;
	}

	@Override
	public String toString() {
		return "User [accountId=" + accountId + ", accountName=" + accountName + ", accountBalance=" + accountBalance
				+ ", accountPin=" + accountPin + "]";
	}
	
	
	
}
