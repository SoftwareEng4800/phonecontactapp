import org.eclipse.swt.widgets.Display;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * User Registers in this UI
 * @author viver
 *
 */
public class registerPage {
	/**
	 * SQL Query Prep
	 */
	public Connection conn = null;
	public PreparedStatement pst = null;
	public PreparedStatement pst1 = null;
	Statement delRegister = null;
	Statement delMyGeo = null;
	protected Shell shlRegister;
	public Text unameTxtBox;
	public Text pwordTextBox;
	public Text verifyPwdTextBox;
	private Text addressTextBox;
	/**
	 * Hooks to google API
	 */
	GeoApiContext context = new GeoApiContext.Builder()
			.apiKey("AIzaSyCj11Xr9nAVmCWloNL7O7Ea3FNhUanTfz8")
			.build();
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			registerPage window = new registerPage();
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
		shlRegister.open();
		shlRegister.setLocation(800, 200);
		
		Button cancelBtn = new Button(shlRegister, SWT.NONE);
		cancelBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginScreen login = new LoginScreen();
				shlRegister.close();
				login.open();
			}
		});
		cancelBtn.setBounds(172, 266, 90, 30);
		cancelBtn.setText("Cancel");
		
		Label lblAddress = new Label(shlRegister, SWT.NONE);
		lblAddress.setText("Address:");
		lblAddress.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblAddress.setBounds(85, 169, 56, 20);
		
		addressTextBox = new Text(shlRegister, SWT.BORDER);
		addressTextBox.setBounds(147, 163, 115, 26);
		
		shlRegister.layout();
		conn = javaConnect.ConnectDB();
		while (!shlRegister.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlRegister = new Shell();
		shlRegister.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shlRegister.setSize(316, 425);
		shlRegister.setText("Register");
		
		Label lblRegister = new Label(shlRegister, SWT.NONE);
		lblRegister.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblRegister.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblRegister.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblRegister.setBounds(89, 55, 147, 73);
		lblRegister.setText("Register");
		
		Label userLbl = new Label(shlRegister, SWT.NONE);
		userLbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		userLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		userLbl.setBounds(71, 134, 70, 20);
		userLbl.setText("Username:");
		unameTxtBox = new Text(shlRegister, SWT.BORDER);
		unameTxtBox.setBounds(147, 134, 115, 26);
		
		Label passwordLbl = new Label(shlRegister, SWT.NONE);
		passwordLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		passwordLbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		passwordLbl.setBounds(77, 195, 64, 20);
		passwordLbl.setText("Password:");
		pwordTextBox = new Text(shlRegister, SWT.BORDER | SWT.PASSWORD);
		pwordTextBox.setBounds(147, 192, 115, 26);
		
		Label passwordTwoLbl = new Label(shlRegister, SWT.NONE);
		passwordTwoLbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		passwordTwoLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		passwordTwoLbl.setBounds(16, 227, 125, 20);
		passwordTwoLbl.setText("Re-enter Password:");
		verifyPwdTextBox = new Text(shlRegister, SWT.BORDER | SWT.PASSWORD);
		verifyPwdTextBox.setBounds(147, 224, 115, 26);
		
		Button submitButton = new Button(shlRegister, SWT.NONE);
		submitButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String deleteRegister = "DELETE FROM register";
				String deleteMyGeo = "DELETE FROM myGeo";
				/**
				 * this code ensures only one user per device
				 * Future design will allow multiple once we 
				 * get a different database feature.
				 */
				try {
					delRegister = conn.createStatement();
					delRegister.execute(deleteRegister);
					delMyGeo = conn.createStatement();
					delMyGeo.execute(deleteMyGeo);
					
				} catch (SQLException e2) {
				
					e2.printStackTrace();
				}
				
				
				String sql = "INSERT INTO register VALUES(?,?)";
				LoginScreen newWindow = new LoginScreen();
				
				if (pwordTextBox.getText().equals(verifyPwdTextBox.getText())) {
					
					try {
						pst = conn.prepareStatement(sql);
						
						pst.setString(1, unameTxtBox.getText());
						pst.setString(2, pwordTextBox.getText());
						
						pst.execute();
						
						String addy = addressTextBox.getText();
						
						GeocodingResult[] results = GeocodingApi.geocode(context,
								addy).await();
						com.google.maps.model.Geometry x = results[0].geometry;
						String y = x.toString();
						String[] yy = y.split(":");
						String[] yz = yy[1].split(",");
						String[] zz = yz[1].split(" ");
						
						String sqlLat = "INSERT INTO myGeo VALUES(?,?)";
						pst1 = conn.prepareStatement(sqlLat);
						pst1.setString(1, yz[0]);
						pst1.setString(2, zz[0]);	
						pst1.execute();
						
						conn.close();
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Use your new credentials to login.");								
					shlRegister.close();
					newWindow.open();
				}
			}
		});
		submitButton.setBounds(65, 266, 90, 30);
		submitButton.setText("Submit");

	}
}
