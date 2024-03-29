import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
/**
 * Editing Contact UI. This screen allows the user to edit the contact they
 * choose from the previous screen, in the file contactHome.java. The fields are 
 * pulled from global variables from contactHome.java and will update the fields
 * with whatever the user indicates. Currently, if you change the address it does not 
 * change the geo coordinates. Not an oversight, just something that was
 * not implemented in time.
 * @author William Watson
 * Tested By: William Watson
 *
 */
public class editContact {
	/**
	 * SQL query prep
	 */
	public Connection conn = null;
	public PreparedStatement pst = null;
	protected Shell shlEditContact;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			editContact window = new editContact();
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
		shlEditContact.open();
		shlEditContact.setLocation(800, 200);
		conn = javaConnect.ConnectDB();
		@SuppressWarnings("unused")
		contactHome contact = new contactHome();
		
		Label backLabel = new Label(shlEditContact, SWT.NONE);
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
				shlEditContact.close();
				contact.open();				
			}			
		});
		Label lblNewLabel = new Label(shlEditContact, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblNewLabel.setBounds(10, 27, 118, 45);
		lblNewLabel.setText("Editing: ");
		
		Label lblFirstName = new Label(shlEditContact, SWT.NONE);
		lblFirstName.setText("First Name:");
		lblFirstName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFirstName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblFirstName.setBounds(10, 93, 77, 20);
		Text fnameTxtBox = new Text(shlEditContact, SWT.BORDER);
		fnameTxtBox.setToolTipText("");
		fnameTxtBox.setBounds(93, 90, 193, 26);
		fnameTxtBox.setText(contactHome.fname);
		
		Label lblName = new Label(shlEditContact, SWT.NONE);
		lblName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblName.setBounds(10, 128, 77, 20);
		lblName.setText("Last Name:");		
		Text lnameTextBox = new Text(shlEditContact, SWT.BORDER);
		lnameTextBox.setToolTipText("");
		lnameTextBox.setBounds(93, 122, 193, 26);
		lnameTextBox.setText(contactHome.lname);		
		
		Label lblPhone = new Label(shlEditContact, SWT.NONE);
		lblPhone.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblPhone.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPhone.setBounds(30, 157, 57, 20);
		lblPhone.setText("Phone #:");
		Text phonenum = new Text(shlEditContact, SWT.BORDER);
		phonenum.addListener(SWT.Verify, new Listener() {
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
		phonenum.setBounds(93, 154, 193, 26);
		phonenum.setText(contactHome.phone);	
		
		Label lblEmail = new Label(shlEditContact, SWT.NONE);
		lblEmail.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblEmail.setBounds(47, 189, 40, 20);
		lblEmail.setText("Email:");
		Text emailTextBox = new Text(shlEditContact, SWT.BORDER);
		emailTextBox.setBounds(93, 186, 193, 26);
		emailTextBox.setText(contactHome.email);
		
		Label lblAddress = new Label(shlEditContact, SWT.NONE);
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblAddress.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblAddress.setBounds(30, 221, 57, 20);
		lblAddress.setText("Address:");
		Text addressTextBox = new Text(shlEditContact, SWT.BORDER);
		addressTextBox.setBounds(93, 218, 193, 26);
		addressTextBox.setText(contactHome.address);		
		
		Label lblFacebook = new Label(shlEditContact, SWT.NONE);
		lblFacebook.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFacebook.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblFacebook.setBounds(21, 253, 66, 20);
		lblFacebook.setText("Facebook:");
		Text fbTextBox = new Text(shlEditContact, SWT.BORDER);
		fbTextBox.setBounds(93, 250, 193, 26);
		fbTextBox.setText(contactHome.facebook);
		
		Label lblTwitter = new Label(shlEditContact, SWT.NONE);
		lblTwitter.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblTwitter.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTwitter.setBounds(39, 285, 48, 20);
		lblTwitter.setText("Twitter:");
		Text twitTextBox = new Text(shlEditContact, SWT.BORDER);
		twitTextBox.setBounds(93, 282, 193, 26);
		twitTextBox.setText(contactHome.twitter);
		
		Button updateButton = new Button(shlEditContact, SWT.NONE);
		updateButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sql = "UPDATE phonecontact SET fname=?, lname=?, phone=?, email=?, address=?, fb=?, twitter=?"
						+ "WHERE fname LIKE '"+ contactHome.fname + "'";
				
				if (fnameTxtBox.getText() != "") {
					try {
					
					pst = conn.prepareStatement(sql);
					pst.setString(1, fnameTxtBox.getText());
					pst.setString(2, lnameTextBox.getText());
					pst.setString(3, phonenum.getText());
					pst.setString(4, emailTextBox.getText());
					pst.setString(5, addressTextBox.getText());
					pst.setString(6, fbTextBox.getText());
					pst.setString(7, twitTextBox.getText());
				
					
					pst.executeUpdate();
					conn.close();
					contactHome newWindow = new contactHome();
					shlEditContact.close();
					newWindow.open();
					
					}catch(Exception er){
						
						JOptionPane.showMessageDialog(null, er);
						shlEditContact.close();
					}
					}else {
					JOptionPane.showMessageDialog(null, "Name is required.");
					
					}
				
			}
		});
		updateButton.setBounds(114, 314, 90, 30);
		updateButton.setText("Update");
		
		shlEditContact.layout();
		while (!shlEditContact.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlEditContact = new Shell();
		shlEditContact.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shlEditContact.setSize(316, 425);
		shlEditContact.setText("Edit Contact");

	}

}
