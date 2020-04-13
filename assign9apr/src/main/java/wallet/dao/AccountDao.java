package wallet.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import wallet.bean.Account;
import wallet.bean.Transaction;


public interface AccountDao {

//public void setJdbcTemplate(JdbcTemplate jdbcTemplate);
	
	public int saveAccount(Account account);
	public int withdraw(Account account, int amount);
	public Account getAccountById(String id) ;
	public int deposit(Account account,int amount);
	public String pin();
	public String id() throws ClassNotFoundException, SQLException;
	public List<Transaction> transaction(String id);
}
