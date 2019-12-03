import org.eclipse.swt.widgets.Display;
import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class registerPage {
	
	public Connection conn = null;
	public PreparedStatement pst = null;
	public PreparedStatement pst1 = null;
	protected Shell shellRegister;
	public Text unameTxtBox;
	public Text pwordTextBox;
	public Text verifyPwdTextBox;
	private Text addressTextBox;
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
		shellRegister.open();
		shellRegister.setLocation(800, 200);
		
		Button cancelBtn = new Button(shellRegister, SWT.NONE);
		cancelBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginScreen login = new LoginScreen();
				shellRegister.close();
				login.open();
			}
		});
		cancelBtn.setBounds(172, 266, 90, 30);
		cancelBtn.setText("Cancel");
		
		Label lblAddress = new Label(shellRegister, SWT.NONE);
		lblAddress.setText("Address:");
		lblAddress.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblAddress.setBounds(85, 169, 56, 20);
		
		addressTextBox = new Text(shellRegister, SWT.BORDER);
		addressTextBox.setBounds(147, 163, 115, 26);
		
		shellRegister.layout();
		conn = javaConnect.ConnectDB();
		while (!shellRegister.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellRegister = new Shell();
		shellRegister.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shellRegister.setSize(316, 425);
		shellRegister.setText("SWT Application");
		
		Label lblRegister = new Label(shellRegister, SWT.NONE);
		lblRegister.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblRegister.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblRegister.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblRegister.setBounds(89, 55, 147, 73);
		lblRegister.setText("Register");
		
		Label lblNewLabel = new Label(shellRegister, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(71, 134, 70, 20);
		lblNewLabel.setText("Username:");
		
		Label lblNewLabel_1 = new Label(shellRegister, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_1.setBounds(77, 195, 64, 20);
		lblNewLabel_1.setText("Password:");
		
		Label lblNewLabel_2 = new Label(shellRegister, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(16, 227, 125, 20);
		lblNewLabel_2.setText("Re-enter Password:");
		
		unameTxtBox = new Text(shellRegister, SWT.BORDER);
		unameTxtBox.setBounds(147, 134, 115, 26);
				
		pwordTextBox = new Text(shellRegister, SWT.BORDER | SWT.PASSWORD);
		pwordTextBox.setBounds(147, 192, 115, 26);
		
		verifyPwdTextBox = new Text(shellRegister, SWT.BORDER | SWT.PASSWORD);
		verifyPwdTextBox.setBounds(147, 224, 115, 26);
		
		Button btnNewButton = new Button(shellRegister, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
														
					shellRegister.close();
					newWindow.open();
				}
			}
		});
		btnNewButton.setBounds(65, 266, 90, 30);
		btnNewButton.setText("Submit");

	}
}
