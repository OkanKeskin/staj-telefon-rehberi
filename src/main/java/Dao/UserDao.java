package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import User.User;

public class UserDao {
	private Connection conn;
	
	private static UserDao userDao = null;
	
	private UserDao() {
		
	}
	
	private synchronized static void createInstance() {
		if(userDao == null) {
			userDao = new UserDao();
		}
	}
	
	public static UserDao getInstance() {
		if(userDao == null) {
			createInstance();
		}
		return userDao;
	}

	protected void connect() throws SQLException, ClassNotFoundException{
		conn = DBCPDataSource.getConnection();
	}
	
	protected void disconnect() throws SQLException {
		conn.close();
	}
	
	/*protected void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		catch (ClassNotFoundException e){
			throw new SQLException(e);
		}
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/form?useUnicode=true&characterEncoding=UTF-8","okan","906700Ok.");
	}*/
	
	public boolean insertUser(User user) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO user(name,surname,tckn) VALUES (?,?,?)";
		connect();
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setString(1, user.getName());
		pstm.setString(2, user.getSurname());
		pstm.setString(3, user.getTckn());
		
		boolean rowInserted = pstm.executeUpdate() >0;
		pstm.close();
		disconnect();
		return rowInserted;
	}
	
	public List<User> listUser() throws SQLException, ClassNotFoundException{
		List<User> listUser = new ArrayList<>();
		String query = "SELECT * FROM user";
		connect();
		
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		
		while(resultSet.next()) {
			String name = resultSet.getString("name");
			String surname = resultSet.getString("surname");
			String tckn = resultSet.getString("tckn");
			int id = resultSet.getInt("id");;
			User user = new User(id,name,surname,tckn);
			listUser.add(user);
		}
		
		resultSet.close();
		statement.close();
		disconnect();
		return listUser;
	}
	
	public User selectUser(int id) throws SQLException, ClassNotFoundException {
		User user = null;
		String query = "SELECT * FROM user where id = ?";
		connect();
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()) {
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			String tckn = rs.getString("tckn");
			user = User.getInstance();
			user.setID(id);
			user.setName(name);
			user.setSurname(surname);
			user.setTckn(tckn);
		}
		disconnect();
		return user;
		
	}
	
	public User selectUserForName(String names, String surname) throws SQLException, ClassNotFoundException {
		User user = null;
		String query = "SELECT * FROM user where name = ? and surname = ?";
		connect();
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setString(1, names);
		pstm.setString(2, surname);
		ResultSet rs = pstm.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String tckn = rs.getString("tckn");
			user = User.getInstance();
			user.setID(id);
			user.setName(names);
			user.setSurname(surname);
			user.setTckn(tckn);
		}
		disconnect();
		return user;
		
	}
	
	public int deleteUser(int id) throws SQLException, ClassNotFoundException {
		int status = 0;
		String query = "DELETE FROM user where id = ?";
		connect();
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1, id);
		status = pstm.executeUpdate();
		disconnect();
		return status;
	}
	
	public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
		String query = "UPDATE user set name = ?,surname=?,tckn=? where id=?";
		connect();
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setString(1, user.getName());
		pstm.setString(2, user.getSurname());
		pstm.setString(3, user.getTckn());
		pstm.setInt(4, user.getId());
		boolean d = pstm.executeUpdate() > 0;		
		
		disconnect();
		return d;
	}
}
