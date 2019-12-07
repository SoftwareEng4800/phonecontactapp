import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
/**
 * The login Screen. This is the screen the user will face everytime they open the app. 
 * First time users will register and be pushed to the registerPage.java UI. Once
 * you register, the credentials in both fields are checked in the register table of the 
 * contactApp.sqlite database.
 * @author William Watson
 * Tested By: William Watson
 *
 */
public class LoginScreen {
	/**
	 * SQL query prep
	 */
	public Connection conn = null;
	Statement uname = null;
	Statement pword = null;
	protected Shell shlLoginScreen;
	private Text uNameTextBox;
	private Text pwordTextBox;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginScreen window = new LoginScreen();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlLoginScreen.open();
		shlLoginScreen.setLocation(800, 200);
		shlLoginScreen.layout();
		conn = javaConnect.ConnectDB(); // connect to javaConnect.java
		while (!shlLoginScreen.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLoginScreen = new Shell();
		shlLoginScreen.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shlLoginScreen.setSize(316, 424);
		shlLoginScreen.setText("Login Screen");
		
		Label lblUsername = new Label(shlLoginScreen, SWT.NONE);
		lblUsername.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblUsername.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblUsername.setBounds(39, 158, 70, 20);
		lblUsername.setText("Username:");		
		uNameTextBox = new Text(shlLoginScreen, SWT.BORDER);
		uNameTextBox.setBounds(115, 155, 135, 26);
		
		Label lblPassword = new Label(shlLoginScreen, SWT.NONE);
		lblPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblPassword.setBounds(45, 190, 64, 20);
		lblPassword.setText("Password:");
		pwordTextBox = new Text(shlLoginScreen, SWT.BORDER | SWT.PASSWORD);		
		pwordTextBox.setBounds(115, 187, 135, 26);
				
		Label loginLabel = new Label(shlLoginScreen, SWT.NONE);
		loginLabel.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		loginLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		loginLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		loginLabel.setBounds(104, 47, 90, 55);
		loginLabel.setText("Login");
		
		Button btnLogin = new Button(shlLoginScreen, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if (uNameTextBox.getText() != "" || pwordTextBox.getText() != "") {
				String sqlName = "SELECT username FROM register WHERE username = '" + uNameTextBox.getText() + "'";
				String sqlPass = "SELECT password FROM register WHERE password = '" + pwordTextBox.getText() + "'";
				@SuppressWarnings("unused")
				String userName = "";
				String passWord = "";
				try {
					uname = conn.createStatement();
					pword = conn.createStatement();
					ResultSet rs = uname.executeQuery(sqlName);
					ResultSet pw = pword.executeQuery(sqlPass);
					userName = rs.getString("username");	
					passWord = pw.getString("password");
					uname.close();
					pword.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				/**
				 * Checks to ensure username is in Database
				 */
				if (userName.equals(uNameTextBox.getText())) {
					/**
					 * checks to ensure password is in database
					 */
					if (passWord.equals(pwordTextBox.getText())) {
						contactHome newWindow = new contactHome();
						shlLoginScreen.close();
						newWindow.open();
					}else {
						JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
						
					}
				}else {
					JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
				}						
			}else {
				JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
			}
		}
	});
		
		
		btnLogin.setBounds(160, 251, 90, 30);
		btnLogin.setText("Login");
		
		Button btnRegister = new Button(shlLoginScreen, SWT.NONE);
		btnRegister.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				registerPage newWindow = new registerPage();
				shlLoginScreen.close();
				newWindow.open();
			}
		});
		btnRegister.setText("Register");
		btnRegister.setBounds(51, 251, 90, 30);
		
		
		
	

	}
}
