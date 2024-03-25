package Helper;
import java.sql.*;

public class DBConnection {
	
	Connection c = null;
	
	public DBConnection() {
		
		
	}
	
	public Connection connDb() {
		
		try {
			this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&password=");
			
			
		} catch (SQLException e) {
			System.out.println("Bağlantı Başarısız");
			e.printStackTrace();
		}
		return c;
	}
	

}
