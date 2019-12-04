import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.sql.*;
import javax.swing.JOptionPane;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.List;

/**
 * This is the homepage file
 * @author viver
 *
 */
public class contactHome {
	//declarations for SQL queries
	public Connection conn = null;
	Statement contacts = null;
	Statement delDistance = null;
	protected Shell shlContactHome;
	/**
	 * Variables used for other windows mainly View and Edit Contact
	 */
	public static String fname = ""; 
	public static String lname = "";
	public static String email = "";
	public static String phone = "";
	public static String address = "";
	public static String facebook = "";
	public static String twitter = "";
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			contactHome window = new contactHome();
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
		shlContactHome.open();
		shlContactHome.setLocation(800, 200);//sets the window 
		conn = javaConnect.ConnectDB();//connect to javaConnect.java for DB
		
		List list = new List(shlContactHome, SWT.BORDER | SWT.V_SCROLL);
		list.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		list.setBounds(10, 58, 162, 214);
		/**
		 * Loads contacts on open
		 */
		String sqlContacts = "SELECT fname, lname FROM phonecontact";
		try {
			contacts = conn.createStatement();
			ResultSet rs = contacts.executeQuery(sqlContacts);
			
			while (rs.next()) {
				list.add(rs.getString(1) + " " + rs.getString(2));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**
		 * auto select the list so users cannot click View Contact without a contact
		 * selected.
		 */
		list.setSelection(0);
		
		Button viewContactButton = new Button(shlContactHome, SWT.NONE);
		viewContactButton.setBounds(178, 57, 117, 30);
		viewContactButton.setText("View Contact");
		viewContactButton.addSelectionListener(new SelectionAdapter() {
			@SuppressWarnings("unused")
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = list.getItem(list.getSelectionIndex());
				String[] sentence = s.split(" ");
		
				if (s != "") {
					String sqlEdit = "SELECT * FROM phonecontact WHERE fname LIKE '" + sentence[0] + "'";
					try {
						contacts = conn.createStatement();
						ResultSet rs = contacts.executeQuery(sqlEdit);//gets all of the tuples based on the query
						while (rs.next()) {
							/**
							 * sends the variable info into the global variables to use in viewContact.java
							 */
							fname = rs.getString(1);
							lname = rs.getString(2);
							phone = rs.getString(3);
							email = rs.getString(4);
							address = rs.getString(5);
							facebook = rs.getString(6);
							twitter = rs.getString(7);
							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					viewContact view = new viewContact();
					shlContactHome.close();
					view.open();
				} else if (sentence[0] == "") {
					JOptionPane.showMessageDialog(null, s);
				}
			}
		});
		
		
		Button editContactButton = new Button(shlContactHome, SWT.NONE);
		editContactButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = list.getItem(list.getSelectionIndex());
				String[] sentence = s.split(" ");
				
				String sqlEdit = "SELECT * FROM phonecontact WHERE fname LIKE '" + sentence[0] + "'";
				
				try {
					contacts = conn.createStatement();
					ResultSet rs = contacts.executeQuery(sqlEdit);
					while (rs.next()) {
						
						fname = rs.getString(1);
						lname = rs.getString(2);
						phone = rs.getString(3);
						email = rs.getString(4);
						address = rs.getString(5);
						facebook = rs.getString(6);
						twitter = rs.getString(7);
						
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				editContact edit = new editContact();
				shlContactHome.close();
				edit.open();
				
			}
		});
		
		editContactButton.setBounds(178, 93, 117, 30);
		editContactButton.setText("Edit Contact");
		
		Button deleteButton = new Button(shlContactHome, SWT.NONE);
		deleteButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = list.getItem(list.getSelectionIndex());
				String[] sentence = s.split(" ");
				/**
				 * deletes from both distance table and original contact
				 * table to ensure quality assurance. Basically
				 * we do not want a distance for a user we deleted. Unfortunately,
				 * it will not check if someone has the same first name.
				 */
				String sqlDelete = "DELETE FROM phonecontact WHERE fname LIKE '" + sentence[0] + "'";
				String sqlDelDistance = "DELETE FROM distance WHERE fname LIKE '" + sentence[0] + "'";
				try {
					contacts = conn.createStatement();
					contacts.execute(sqlDelete);
					delDistance = conn.createStatement();
					delDistance.execute(sqlDelDistance);
					shlContactHome.close();
					contactHome contact = new contactHome();
					contact.open();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		deleteButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.NORMAL));
		deleteButton.setBounds(178, 129, 117, 30);
		deleteButton.setText("Delete Contact");
		
		Button searchBtn = new Button(shlContactHome, SWT.NONE);
		searchBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				searchPage search = new searchPage();
				shlContactHome.close();
				search.open();
			}
		});
		searchBtn.setBounds(178, 164, 117, 30);
		searchBtn.setText("Search Contacts");
		
		Button logoutButton = new Button(shlContactHome, SWT.NONE);
		logoutButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginScreen logout = new LoginScreen();
				shlContactHome.close();
				logout.open();
			}
		});
		logoutButton.setBounds(178, 242, 117, 30);
		logoutButton.setText("Logout");
		shlContactHome.layout();
		while (!shlContactHome.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlContactHome = new Shell();
		shlContactHome.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shlContactHome.setSize(316, 425);
		shlContactHome.setText("Home Screen");
		
		Label lblHome = new Label(shlContactHome, SWT.NONE);
		lblHome.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblHome.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblHome.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblHome.setBounds(10, 10, 162, 42);
		lblHome.setText("Contacts");
		
		Button btnNewContact = new Button(shlContactHome, SWT.NONE);
		btnNewContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ContactAppGUI newWindow = new ContactAppGUI();
				shlContactHome.close();
				newWindow.open();
				
				
			}
		});
		btnNewContact.setBounds(51, 278, 90, 30);
		btnNewContact.setText("New Contact");

		
		
	}
}
