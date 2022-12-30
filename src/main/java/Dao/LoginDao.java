package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import User.Login;
import User.User;

public class LoginDao {
	
	private static LoginDao loginDao = null;
	
	private LoginDao() {
		
	}
	
	private synchronized static void createInstance() {
		if(loginDao == null) {
			loginDao = new LoginDao();
		}
	}
	
	public static LoginDao getInstance() {
		if(loginDao == null) {
			createInstance();
		}
		
		return loginDao;
	}

	private Connection conn;
	
	protected void connect() throws ClassNotFoundException, SQLException {
		conn = DBCPDataSource.getConnection();
	}
	
	protected void disconnect() throws SQLException {
		conn.close();
	}
	
	public boolean insertLogin(Login login) throws ClassNotFoundException, SQLException {
		String query = "INSERT INTO login(user_name,password) VALUES(?,?)";
		boolean b;
		connect();
		PreparedStatement pre = conn.prepareStatement(query);
		pre.setString(1, login.getKadi());
		pre.setString(2, login.getSifre());
		b = pre.executeUpdate() > 0;
		pre.close();
		disconnect();
		return b;
	}
	
	public boolean userControl(String kadi,String sifre) throws ClassNotFoundException, SQLException {
		String query = "SELECT * FROM login WHERE user_name =? and password = ?";
		connect();
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, kadi);
		statement.setString(2, sifre);
		ResultSet rs = statement.executeQuery();
		boolean b;
		b = rs.next();
		disconnect();
		return b;
	
	}
	
}
