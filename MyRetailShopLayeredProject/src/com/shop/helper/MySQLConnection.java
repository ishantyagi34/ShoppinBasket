package com.shop.helper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection
{

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/shop?useSSL=false", "root", "mySQLnirvana99_");
		
		return connection;
	}

}

