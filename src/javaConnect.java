import java.sql.*;
import javax.swing.*;
/**
 * This is the driver for our database. I made this file to be separate
 * from the actual program so I did not have to rewrite the database
 * boilerplate every single time I wanted to call the database. I created
 * a dialog to pop open for testing throughout the time writing the UI and
 * database. You can see this if you take the comment // out.
 * @author William Watson
 * Tested By: William Watson
 *
 */
public class javaConnect {

	Connection conn = null;
	
	public static Connection ConnectDB() {
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\viver\\git\\ContactApp\\contactApp.sqlite");
			//used for testing to make sure I was connected to Database
			//JOptionPane.showMessageDialog(null, "Connection Established");
			return conn;
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return null;
			}
	}
	
}
