package wallet.bean;

public class Account {

	private String accountId;
	private String accountName;
	private int accountBalance;
	private String accountPin;
	public Account() {} 
	public Account(String accountId,String accountName, int accountBalance, String accountPin) {
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
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", accountBalance=" + accountBalance
				+ ", accountPin=" + accountPin + "]";
	}
	
	
}
