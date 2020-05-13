package wetalk.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*Database connection*/
public class ConnectionDao {
	public static Connection getConnection() {
		Connection conn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.print("");
		}catch(ClassNotFoundException ex){
			System.out.println("Load driver failed");
		}
		try{
			String url="jdbc:mysql://localhost:3306/test?user=root&password=t1234";
		    conn=DriverManager.getConnection(url);	
			System.out.print("connection succeeded");
			return conn;
		}catch(SQLException ex){
			System.out.println("Connection failed"+ex);
			return null;
		}

	}
	
}
