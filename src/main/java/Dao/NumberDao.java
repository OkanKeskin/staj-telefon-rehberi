package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import User.Numbers;


public class NumberDao {
	private static NumberDao numberDao = null;
	
	private NumberDao() {
		
	}
	
	private synchronized static void createInstance() {
		if(numberDao ==null) {
			numberDao = new NumberDao();
		}
	}
	
	public static NumberDao getInstance() {
		if(numberDao == null) {
			createInstance();
		}
		return numberDao;
	}
		

	private Connection conn;
	

	protected void connect() throws SQLException, ClassNotFoundException{
		conn = DBCPDataSource.getConnection();
	}
	
	protected void disconnect() throws SQLException, ClassNotFoundException{
		conn.close();
	}

/*
	protected void connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
		}
		catch (ClassNotFoundException e){
			throw new SQLException(e);
		}
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/form?useUnicode=true&characterEncoding=UTF-8","okan","906700Ok.");
	}
	*/
	public boolean insertNumber(int id,String number) throws SQLException, ClassNotFoundException {
		String query = "INSERT INTO number(userId,number) VALUES(?,?)";
		connect();
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1, id);
		pstm.setString(2,number);
		boolean p = pstm.executeUpdate() > 0;
		disconnect();
		return p;
	}
	
	public List<Numbers> listNumber(int id) throws ClassNotFoundException, SQLException{
		List<Numbers> numbers = new ArrayList<>();
		String query = "Select * from number Where userId = ?";
		connect();
		
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setLong(1, id);
		ResultSet result = stmt.executeQuery();
		
		while(result.next()) {
			String number = result.getString("number");
			int numberId = result.getInt("numberId");
			int userId = id;
			Numbers numbera = new Numbers(numberId,userId,number);
			numbers.add(numbera);		
		}
		
		result.close();
		stmt.close();
		disconnect();
		return numbers;
		
	}
	
	public int deleteNumber(int numberId) throws ClassNotFoundException, SQLException {
		int status = 0;
		String query = "DELETE FROM number where numberId =?";
		connect();
		PreparedStatement pstm = conn.prepareStatement(query);
		pstm.setInt(1 ,numberId);
		status = pstm.executeUpdate();
		disconnect();
		return status;
	}
}
