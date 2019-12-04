import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class searchPage {
	public Connection conn = null;
	public static String fname = ""; 
	public static String lname = "";
	public static String email = "";
	public static String phone = "";
	public static String address = "";
	public static String facebook = "";
	public static String twitter = "";
	Statement contacts1 = null;
	Statement miles = null;
	Statement contacts = null;
	Statement byName = null;
	protected Shell shell;
	private Text nameTextBox;
	private Text milesTextBox;
	@SuppressWarnings("unused")
	private Button viewContactButton;
		
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			searchPage window = new searchPage();
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
				
		conn = javaConnect.ConnectDB();
		shell.setLocation(800, 200);
		shell.layout();
		List resultList = new List(shell, SWT.BORDER);
		resultList.setBounds(10, 117, 182, 236);
		
		Label backLabel = new Label(shell, SWT.NONE);
		backLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		backLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		backLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		backLabel.setBounds(199, 10, 89, 31);
		backLabel.setText("<- Back");
		backLabel.getCursor();
		backLabel.addListener(SWT.MouseDown, new Listener() {
		
			@Override
			public void handleEvent(Event arg0) {
				contactHome contact = new contactHome();
				shell.close();
				contact.open();				
			}
		});
		Label searchLbl = new Label(shell, SWT.NONE);
		searchLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		searchLbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		searchLbl.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		searchLbl.setBounds(10, -3, 162, 42);
		searchLbl.setText("Search:");
		
		
		milesTextBox = new Text(shell, SWT.BORDER);
		milesTextBox.setBounds(10, 51, 99, 26);
		
		Button distButton = new Button(shell, SWT.NONE);
		distButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resultList.removeAll();
				resultList.update();
				String mileage = milesTextBox.getText();				
				
				String sqlDist = "SELECT fname, lname FROM distance WHERE distance <= '" + mileage + "'";
				try {
					miles = conn.createStatement();
					ResultSet rs = miles.executeQuery(sqlDist);
										
					while (rs.next()) {
						resultList.add(rs.getString(1) + " " + rs.getString(2));
						resultList.update();							
					}
				 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Button viewContactButton = new Button(shell, SWT.NONE);
				viewContactButton.setBounds(198, 117, 90, 30);
				viewContactButton.setText("View Contact");
				viewContactButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						String s = resultList.getItem(resultList.getSelectionIndex());
						String[] sentence = s.split(" ");
				
						if (s != "") {
							String sqlEdit = "SELECT * FROM phonecontact WHERE fname LIKE '" + sentence[0] + "'";
							try {
								contacts1 = conn.createStatement();
								ResultSet rs = contacts1.executeQuery(sqlEdit);
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
							searchViewContact view = new searchViewContact();
							shell.close();
							view.open();
						} else if (sentence[0] == "") {
							JOptionPane.showMessageDialog(null, s);
						}
					}
				});
				
				Button clearListBox = new Button(shell, SWT.NONE);
				clearListBox.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						resultList.removeAll();
						resultList.update();
						viewContactButton.dispose();
						clearListBox.dispose();
						
					}
				});
				clearListBox.setBounds(198, 323, 90, 30);
				clearListBox.setText("Clear Search");
			
			}
		});
		distButton.setBounds(198, 47, 90, 30);
		distButton.setText("By Distance");
		
		Button nameButton = new Button(shell, SWT.NONE);
		nameButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resultList.removeAll();
				resultList.update();
				String s = nameTextBox.getText();
				String[] sentence = s.split(" ");
		
				if (s != "") {
					String sqlByName = "SELECT fname, lname FROM phonecontact WHERE fname LIKE '" + sentence[0] + "%'";
					try {
						byName = conn.createStatement();
						ResultSet rs = byName.executeQuery(sqlByName);
						while (rs.next()) {
							resultList.add(rs.getString(1) + " " + rs.getString(2));
							resultList.update();							
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else if (sentence[0] == "") {
					JOptionPane.showMessageDialog(null, s);
				}
				Button viewContactButton = new Button(shell, SWT.NONE);
				viewContactButton.setBounds(198, 117, 90, 30);
				viewContactButton.setText("View Contact");
				viewContactButton.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						
						String s = resultList.getItem(resultList.getSelectionIndex());
						String[] sentence = s.split(" ");
				
						if (s != "") {
							String sqlEdit = "SELECT * FROM phonecontact WHERE fname LIKE '" + sentence[0] + "'";
							try {
								contacts1 = conn.createStatement();
								ResultSet rs = contacts1.executeQuery(sqlEdit);
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
							searchViewContact view = new searchViewContact();
							shell.close();
							view.open();
						} else if (sentence[0] == "") {
							JOptionPane.showMessageDialog(null, s);
						}
					}
				});
				
				Button clearListBox = new Button(shell, SWT.NONE);
				clearListBox.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						resultList.removeAll();
						resultList.update();
						viewContactButton.dispose();
						clearListBox.dispose();
						
					}
				});
				clearListBox.setBounds(198, 323, 90, 30);
				clearListBox.setText("Clear Search");
			}
		});
		nameButton.setBounds(198, 83, 90, 30);
		nameButton.setText("By Name");
		
		nameTextBox = new Text(shell, SWT.BORDER);
		nameTextBox.setBounds(10, 85, 182, 26);
		
		
		
		Label milesLbl = new Label(shell, SWT.NONE);
		milesLbl.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		milesLbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		milesLbl.setBounds(122, 57, 70, 20);
		milesLbl.setText("miles");
		
		
		
		
		
		
		
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
		shell.setText("SWT Application");

	}
}
