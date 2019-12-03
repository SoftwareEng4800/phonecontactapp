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
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.widgets.Text;




public class contactHome {

	public Connection conn = null;
	Statement contacts = null;
	protected Shell shell;
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
		shell.open();
		shell.setLocation(600, 100);
		conn = javaConnect.ConnectDB();
		List list = new List(shell, SWT.BORDER | SWT.V_SCROLL);
		list.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		list.setBounds(10, 58, 180, 214);
		
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
		
		Button viewContactButton = new Button(shell, SWT.NONE);
		viewContactButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = (String) list.getItem(list.getSelectionIndex());
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
				viewContact view = new viewContact();
				shell.close();
				view.open();
				
			}
		});
		viewContactButton.setBounds(196, 57, 99, 30);
		viewContactButton.setText("View Contact");
		
		Button editContactButton = new Button(shell, SWT.NONE);
		editContactButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = (String) list.getItem(list.getSelectionIndex());
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
				shell.close();
				edit.open();
				
			}
		});
		
		editContactButton.setBounds(196, 93, 99, 30);
		editContactButton.setText("Edit Contact");
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shell.setSize(316, 425);
		shell.setText("Home Screen");
		
		Label lblHome = new Label(shell, SWT.NONE);
		lblHome.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblHome.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblHome.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblHome.setBounds(10, 10, 162, 42);
		lblHome.setText("Contacts");
		
		Button btnNewContact = new Button(shell, SWT.NONE);
		btnNewContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ContactAppGUI newWindow = new ContactAppGUI();
				shell.close();
				newWindow.open();
				
				
			}
		});
		btnNewContact.setBounds(51, 278, 90, 30);
		btnNewContact.setText("New Contact");

		
		
	}
}
