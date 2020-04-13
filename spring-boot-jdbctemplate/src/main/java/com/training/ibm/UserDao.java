package com.training.ibm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate template;

	Integer getCountOfUsers() {
		return template.queryForObject("select count(*) from account1", Integer.class);
	}

	String getUserName(String id, String pin) {
		return template.queryForObject("select name from account1 where id = ? and pin = ?", new Object[] { id, pin },
				String.class);
	}

	// get All users
	List<User> getUserDetails() {
		List<User> list1 = template.query("select * from account1", new RowMapper<User>() {

			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setAccountId(rs.getString("id"));
				user.setAccountName(rs.getString("name"));
				user.setAccountBalance(rs.getInt("balance"));
				user.setAccountPin(rs.getString("pin"));
				return user;
			}

		});
		return list1;
	}

	// add user
	void addUser(User user) {
		template.update("INSERT into account1 VALUES(?,?,?,?)",
				new Object[] { user.getAccountId(), user.getAccountName(), user.getAccountBalance(), user.getAccountPin() });
	}

	// update user
	void updateUser(String id, User user) {
		template.update("UPDATE account1 SET name = ?, balance = ?, pin = ? where id = ?",
				new Object[] { user.getAccountName(), user.getAccountBalance(), user.getAccountPin(), id });
	}
	
	//delete user
	void deleteUser(String id) {
		template.update("DELETE FROM account1 WHERE id = ?", new Object[] {id});
	}

}
