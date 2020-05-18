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
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.print("");
		}catch(ClassNotFoundException ex){
			System.out.println("Load driver failed");
		}
		try{
			//String url="jdbc:mysql://localhost:3306/resultout? useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC\",\"root\",\"\"5357271Aiden.";
			String url="jdbc:mysql://localhost:3306/test?user=root&password=5357271Aiden.?serverTimezone=UTC";
		    //conn=DriverManager.getConnection(url,"jdbc:mysql://localhost/test:mysql://localhost:3306/test?user=root&password=5357271Aiden...?serverTimezone=UTC");
			conn=DriverManager.getConnection("jdbc:mysql://localhost/test?serverTimezone=UTC", "root", "5357271Aiden.");
			System.out.print("connection succeeded");
			return conn;
		}catch(SQLException ex){
			System.out.println("Connection failed"+ex);
			return null;
		}

	}
	
}
