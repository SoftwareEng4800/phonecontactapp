import org.eclipse.swt.widgets.Display;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import java.sql.*;

import javax.swing.JOptionPane;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

public class ContactAppGUI {
	//declarations for SQL Queries
	public Connection conn = null;
	public PreparedStatement pst = null;
	Statement contacts = null;
	public PreparedStatement dist1 = null;
	protected Shell shlContactAppGui;
	//declarations for each textbox
	private Text phonenumTextBox;
	private Text emailTextBox;
	private Text addressTextBox;
	private Text fbTextBox;
	private Text twitTextBox;
	private Text fnameTxtBox;
	private Text lnameTextBox;
	//google api
	GeoApiContext context = new GeoApiContext.Builder()
			.apiKey("AIzaSyCj11Xr9nAVmCWloNL7O7Ea3FNhUanTfz8")
			.build();
	//creating objects and their variables
	distanceFinder dist = new distanceFinder();
	staticMap staticMaps = new staticMap();
	public String lat;
	public String lon;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			ContactAppGUI window = new ContactAppGUI();
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
		shlContactAppGui.open();
		shlContactAppGui.setLocation(800, 200);
		@SuppressWarnings("unused")
		contactHome contact = new contactHome();
		
		Label createContactLbl = new Label(shlContactAppGui, SWT.NONE);
		createContactLbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		createContactLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		createContactLbl.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		createContactLbl.setBounds(30, 36, 239, 45);
		createContactLbl.setText("Create Contact");
		
		Label lblFirstName = new Label(shlContactAppGui, SWT.NONE);
		lblFirstName.setText("First Name:");
		lblFirstName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFirstName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblFirstName.setBounds(10, 93, 77, 20);
		fnameTxtBox = new Text(shlContactAppGui, SWT.BORDER);
		fnameTxtBox.setToolTipText("");
		fnameTxtBox.setBounds(93, 90, 193, 26);
		
		Label lblLastName = new Label(shlContactAppGui, SWT.NONE);
		lblLastName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblLastName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblLastName.setBounds(10, 128, 77, 20);
		lblLastName.setText("Last Name:");
		lnameTextBox = new Text(shlContactAppGui, SWT.BORDER);
		lnameTextBox.setToolTipText("");
		lnameTextBox.setBounds(93, 122, 193, 26);
		
		Label lblPhone = new Label(shlContactAppGui, SWT.NONE);
		lblPhone.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblPhone.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPhone.setBounds(30, 157, 57, 20);
		lblPhone.setText("Phone #:");
		phonenumTextBox = new Text(shlContactAppGui, SWT.BORDER);
		//only allows numbers in box
		phonenumTextBox.addListener(SWT.Verify, new Listener() {
		      @Override
			public void handleEvent(Event e) {
		        String string = e.text;
		        char[] chars = new char[string.length()];
		        string.getChars(0, chars.length, chars, 0);
		        for (int i = 0; i < chars.length; i++) {
		          if (!('0' <= chars[i] && chars[i] <= '9')) {
		            e.doit = false;
		            return;
		          }
		        }
		      }
		    });
		phonenumTextBox.setBounds(93, 154, 193, 26);
		
		Label lblEmail = new Label(shlContactAppGui, SWT.NONE);
		lblEmail.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblEmail.setBounds(47, 189, 40, 20);
		lblEmail.setText("Email:");
		emailTextBox = new Text(shlContactAppGui, SWT.BORDER);
		emailTextBox.setBounds(93, 186, 193, 26);		
		
		Label lblAddress = new Label(shlContactAppGui, SWT.NONE);
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblAddress.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblAddress.setBounds(30, 221, 57, 20);
		lblAddress.setText("Address:");
		addressTextBox = new Text(shlContactAppGui, SWT.BORDER);
		addressTextBox.setBounds(93, 218, 193, 26);
		
		Label lblFacebook = new Label(shlContactAppGui, SWT.NONE);
		lblFacebook.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFacebook.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblFacebook.setBounds(21, 253, 66, 20);
		lblFacebook.setText("Facebook:");
		fbTextBox = new Text(shlContactAppGui, SWT.BORDER);
		fbTextBox.setBounds(93, 250, 193, 26);
		
		Label lblTwitter = new Label(shlContactAppGui, SWT.NONE);
		lblTwitter.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblTwitter.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTwitter.setBounds(39, 285, 48, 20);
		lblTwitter.setText("Twitter:");
		twitTextBox = new Text(shlContactAppGui, SWT.BORDER);
		twitTextBox.setBounds(93, 282, 193, 26);		
		
		Label backLabel = new Label(shlContactAppGui, SWT.NONE);
		backLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		backLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		backLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		backLabel.setBounds(199, 10, 89, 31);
		backLabel.setText("<- Back");
		backLabel.getCursor();
		//listener for label so I didn't have to use a button
		backLabel.addListener(SWT.MouseDown, new Listener() {		
			@Override
			public void handleEvent(Event arg0) {
				contactHome contact = new contactHome();
				shlContactAppGui.close();
				contact.open();				
			}
		});
				
		shlContactAppGui.layout();
		conn = javaConnect.ConnectDB(); //connect to our JDBC for database
		while (!shlContactAppGui.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlContactAppGui = new Shell();
		shlContactAppGui.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shlContactAppGui.setSize(316, 425);
		shlContactAppGui.setText("Create Contact");		
		
		Button btnSubmit = new Button(shlContactAppGui, SWT.NONE);
		btnSubmit.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("static-access")
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sql = "INSERT INTO phonecontact VALUES(?,?,?,?,?,?,?)";
				if (fnameTxtBox.getText() != "") {
				try {
				//prepared statement to stop SQL injection
				pst = conn.prepareStatement(sql);
				pst.setString(1, fnameTxtBox.getText());
				pst.setString(2, lnameTextBox.getText());
				pst.setString(3, phonenumTextBox.getText());
				pst.setString(4, emailTextBox.getText());
				pst.setString(5, addressTextBox.getText());
				pst.setString(6, fbTextBox.getText());
				pst.setString(7, twitTextBox.getText());			
				//run the sql statement
				pst.executeUpdate();
				
				String firstName = fnameTxtBox.getText();
				String lastName = lnameTextBox.getText();
				String addy = addressTextBox.getText();
				//get geocode for our address
				GeocodingResult[] results = GeocodingApi.geocode(context,
						addy).await();
				com.google.maps.model.Geometry x = results[0].geometry;
				String y = x.toString();//gets the result into x
				String[] yy = y.split(":");//splits at colon
				String[] yz = yy[1].split(",");//splits at ,
				String[] zz = yz[1].split(" ");//splits at space
				/**The code above lets me grab both the lat and long only from the JSON 
				provided by the google maps API program				
				**/
				String sqlSearch = "SELECT * FROM myGEO";
				try {
					contacts = conn.createStatement();
					ResultSet rs = contacts.executeQuery(sqlSearch);
					while (rs.next()) {
						lat = rs.getString(1);
						lon = rs.getString(2);						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//converts all of the strings into doubles to use in distanceFinder constructor
				double latD = Double.parseDouble(lat);
				double lonD = Double.parseDouble(lon);
				double yzD = Double.parseDouble(yz[0]);
				double zzD = Double.parseDouble(zz[0]);
				@SuppressWarnings("static-access")
				Double distance = dist.distance(latD, yzD, lonD, zzD);//in distanceFinder.java
				String sqlDist = "INSERT INTO distance VALUES(?,?,?)";
				dist1 = conn.prepareStatement(sqlDist);
				
				dist1.setString(1, firstName);
				dist1.setString(2, lastName);
				dist1.setString(3, distance.toString());
				
				dist1.executeUpdate();
				
				conn.close();
				//start making map file
				staticMaps.statMap(yzD, lonD, firstName, lastName);//in staticMap.java
				contactHome newWindow = new contactHome();
				shlContactAppGui.close();
				newWindow.open();
				
				}catch(Exception er){
					
					JOptionPane.showMessageDialog(null, er);
					shlContactAppGui.close();
				}
				}else {
				JOptionPane.showMessageDialog(null, "Name is required.");
				
				}
			}
		});
		
						
		btnSubmit.setBounds(103, 316, 90, 30);
		btnSubmit.setText("Submit");
		
		

	}
}
