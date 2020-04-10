package wallet.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import wallet.bean.Account;
import wallet.bean.Transaction;

public class AccountDaoImpl implements AccountDao {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;

	}

	public int saveAccount(Account account) {

		String sql = "INSERT into account1 VALUES('" + account.getAccountId() + "','"
				+ account.getAccountName() + "','" + account.getAccountBalance() + "','" + account.getAccountPin()
				+ "')";
		return jdbcTemplate.update(sql);
	}

	public int withdraw(Account account, int amount) {

		int accountBalance = 0;

		accountBalance = getAccountById(account.getAccountId()).getAccountBalance() - amount;
		if (accountBalance < amount) {
			System.out.println("Insufficient amount");
		}
		account.setAccountBalance(accountBalance);
		String sql = "UPDATE account1 SET balance='"+accountBalance+"' WHERE id='"+account.getAccountId()+"'";
		int jdbc = jdbcTemplate.update(sql);
		String query = "insert into transactions values('"+account.getAccountId()+"','"+amount+"','"+accountBalance+"',CURRENT_TIMESTAMP())";
		int transaction = jdbcTemplate.update(query);
		return jdbc;
	}

	public Account getAccountById(String accountId) {
		Account account = null;
		try {
			java.sql.Connection connection = wallet.util.DBUtil.getConnection();

			String sql = "SELECT * FROM account1 WHERE id='" + accountId + "'";
			java.sql.Statement statement = connection.createStatement();
			java.sql.ResultSet result = statement.executeQuery(sql);
			if (result.next()) {
				String name = result.getString(2);
				int balance = result.getInt(3);
				String pin = result.getString(4);
				account = new Account(accountId, name, balance, pin);
			} else {
				System.out.println("Account with id:" + accountId + "not Found!");
			}
			connection.close();
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return account;
	}

	public int deposit(Account account, int amount) {
		int accountBalance = 0;

		accountBalance = getAccountById(account.getAccountId()).getAccountBalance() + amount;
		account.setAccountBalance(accountBalance);
		String sql = "UPDATE account1 SET balance='"+accountBalance+"' WHERE id='"+account.getAccountId()+"'";
		int jdbc = jdbcTemplate.update(sql);
		String query = "insert into transactions values('"+account.getAccountId()+"','"+amount+"','"+accountBalance+"',CURRENT_TIMESTAMP())";
		int transaction = jdbcTemplate.update(query);
		return jdbc;
	}

	public String pin() {
		Random random = new Random();
		int firstNumber = (int) (random.nextDouble() * 10);
		int secondNumber = (int) (random.nextDouble() * 10);
		int thirdNumber = (int) (random.nextDouble() * 10);
		int fourthNumber = (int) (random.nextDouble() * 10);

		return "" + firstNumber + secondNumber + thirdNumber + fourthNumber;
	}

	public String id() throws ClassNotFoundException, SQLException {
		java.sql.Connection connection = wallet.util.DBUtil.getConnection();

		String accountId = "";
		String sql = "select max(id) from account1";
		java.sql.PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		if (result.next()) {
			String maxAccountId = result.getString(1);
			maxAccountId = (maxAccountId != null) ? maxAccountId.substring(2) : "0";
			int newAccountId = 1 + Integer.parseInt(maxAccountId);
			accountId = String.format("%010d", newAccountId);
		}
		return accountId;
	}

	public List<Transaction> transaction(String accountId) {
		String sql = "SELECT * FROM transactions WHERE id='"+accountId+"' ORDER BY ts DESC limit 5";
		List<Transaction> transaction=jdbcTemplate.query(sql,new RowMapper<Transaction>(){

			public Transaction mapRow(ResultSet rs, int rn) throws SQLException {
				Transaction trans=new Transaction();
				trans.setAccountId(rs.getString(1));
				trans.setAccountAmount(rs.getInt(2));
				trans.setAccountBalance(rs.getInt(3));
				trans.setTimeStamp(rs.getString(4));
				return trans;
			}
			
		});
		return transaction;
	}
}
