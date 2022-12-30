package Dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

public class DBCPDataSource {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Context ctx = null;

        try {
			ctx = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
        DataSource ds = null;
		try {
			ds = (DataSource) ctx.lookup("java:/comp/env/jdbc/MyDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return ds.getConnection();
		
	}
	
	
	/*
	private static BasicDataSource ds = new BasicDataSource();
	
	static {
		ds.setUrl("jdbc:mysql://localhost:3306/form");
		ds.setUsername("okan");
		ds.setPassword("906700Ok.");
		ds.setDriverClassName("com.mysql.jdbc.Driver");
	}
	
	public static Connection getConnection() throws SQLException {
		
		return ds.getConnection();
		
	}*/
	
}