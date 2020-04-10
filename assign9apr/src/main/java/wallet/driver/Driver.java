package wallet.driver;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import wallet.bean.Account;
import wallet.bean.Transaction;
import wallet.dao.AccountDao;

public class Driver {

	private static ApplicationContext context;

	private static AccountDao accountDao;
	static Account account = new Account();
	static Transaction transaction = new Transaction();
	private static Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		accountDao = (AccountDao) context.getBean("accountDao");
		while (true) {
			System.out.println("1.Create Account");
			System.out.println("2.Withdraw");
			System.out.println("3.Deposit");
			System.out.println("4.Fund Transfer");
			System.out.println("5.Transaction details");
			System.out.println("6.Exit");
			System.out.println("Choice?");
			int choice = in.nextInt();
			switch (choice) {

			case 1:
				createAccount();
				break;
			case 2:
				withdrawAmount();
				break;
			case 3:
				depositAmount();
				break;
			case 4:
				fundTransfer();
				break;
			case 5:
				transactionDetails();
				break;
			case 6:
				System.exit(0);
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	private static void transactionDetails() {
		System.out.println("Enter Account Id");
		in.nextLine();
		String id = in.nextLine();
		transaction.setAccountId(id);
		List<Transaction> trList;
		trList = accountDao.transaction(id);
		if(trList.isEmpty()){
			System.out.println("No transactions available");
		} else{
		for(Transaction transaction:trList){
			System.out.println(transaction);
		}
		}
	}

	private static void fundTransfer() {
		System.out.println("Enter your Account Id");
		in.nextLine();
		String yourId = in.nextLine();
		System.out.println("Enter receipient Id");
		String theirId = in.nextLine();
		System.out.println("Amount to be deposited");
		int amount = in.nextInt();
		account.setAccountId(yourId);
		accountDao.withdraw(account, amount);
		System.out.println("Amount sent successfully\nCurrent balance = Rs." + account.getAccountBalance());

		System.out.println("----------------------------");
		account.setAccountId(theirId);
		accountDao.deposit(account, amount);
		System.out.println("Amount recieved successfully\nCurrent balance = Rs." + account.getAccountBalance());
	}

	private static void depositAmount() {
		System.out.println("Enter Account Id");
		in.nextLine();
		String accountId = in.nextLine();
		System.out.println("Amount?");
		int amount = in.nextInt();
		account.setAccountId(accountId);
		accountDao.deposit(account, amount);
		System.out.println("Amount deposited successfully\nCurrent balance = Rs. " + account.getAccountBalance());

	}

	private static void createAccount() throws ClassNotFoundException, SQLException {
		System.out.println("Name");
		in.nextLine();
		String name = in.nextLine();
		System.out.println("Opening balance");
		int openingBalance = in.nextInt();

		String pin = accountDao.pin();
		String id = accountDao.id();
		account.setAccountId(id);
		account.setAccountName(name);
		account.setAccountBalance(openingBalance);
		account.setAccountPin(pin);
		accountDao.saveAccount(account);
		System.out.println("Account created with id  " + account.getAccountId() + " with pin: " + pin);

	}

	private static void withdrawAmount() {
		System.out.println("Enter your account id");
		in.nextLine();
		String accountId = in.nextLine();
		System.out.println("Amount to withdraw");
		int amount = in.nextInt();
		account.setAccountId(accountId);
		accountDao.withdraw(account, amount);
		System.out.println("Amount withdrawn successfully\nCurrent balance = Rs. " + account.getAccountBalance());

	}
}
