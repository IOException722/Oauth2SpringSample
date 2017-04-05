package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import common.FVConstants;

public class Helper {
	static Connection conn =null;
	private static PreparedStatement stmt = null;
	
	public static boolean validate(String username,String password){  
		boolean status=false;  
		try{  
		if(conn==null)
		 makeJDBCConnection();
		stmt=conn.prepareStatement(  
		"select * from Users where username=? and password=?");  
		stmt.setString(1,username);  
		stmt.setString(2,password);  
		      
		ResultSet rs=stmt.executeQuery();  
		status=rs.next();  
		          
		}catch(Exception e){
			System.out.println(e);
			}  
		return status;  
	}
	
	public static void insertData(String name, int age, String password)
	{
		if(conn==null)
			 makeJDBCConnection();
		
			try {
				System.out.println("conn is: "+conn);
				String insertQueryStatement = "INSERT  INTO Users Values (?,?,?,?)";
				stmt = conn.prepareStatement(insertQueryStatement);
				stmt.setString(1, null);
				stmt.setString(2, name);
				stmt.setInt(3, age);
				stmt.setString(4, password);
				stmt.executeUpdate();
				System.out.println("After insertoing the file in database"+ name);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	private static void makeJDBCConnection() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+FVConstants.dbName+"?useUnicode=true", FVConstants.usernName, FVConstants.password);
			if (conn != null) {
				System.out.println("Connection Successful");
			} else {
				System.out.println("Failed to make connection!");
			}
		} catch (SQLException e) {
			System.out.println("MySQL Connection Failed!");
			e.printStackTrace();
			return;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	

}
