import java.sql.*;
import javax.swing.*;

public class javaConnect {

	Connection conn = null;
	
	public static Connection ConnectDB() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\viver\\git\\ContactApp\\contactApp.sqlite");
			JOptionPane.showMessageDialog(null, "Connection Established");
			return conn;
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
	}
	
}
