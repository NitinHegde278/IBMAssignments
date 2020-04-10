package wallet.bean;

public class Transaction {
	private String accountId;
	private int accountAmount;
	private int accountBalance;
	private String timeStamp;
	
	public Transaction(){};
	public Transaction(String accountId, int accountAmount, int accountBalance, String timeStamp) {
		this.accountId = accountId;
		this.accountAmount = accountAmount;
		this.accountBalance = accountBalance;
		this.timeStamp = timeStamp;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public int getAccountAmount() {
		return accountAmount;
	}
	public void setAccountAmount(int accountAmount) {
		this.accountAmount = accountAmount;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Transaction [accountId=" + accountId + ", accountAmount=" + accountAmount + ", accountBalance="
				+ accountBalance + ", timeStamp=" + timeStamp + "]";
	}
	
	
	
}
