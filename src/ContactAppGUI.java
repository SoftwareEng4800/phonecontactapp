import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
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
	public Connection conn = null;
	public PreparedStatement pst = null;
	protected Shell shell;
	private Text phonenum;
	private Text emailTextBox;
	private Text addressTextBox;
	private Text fbTextBox;
	private Text twitTextBox;
	private Text fnameTxtBox;
	private Text lnameTextBox;

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
		shell.open();
		shell.setLocation(600, 100);
		contactHome contact = new contactHome();
		
		
		lnameTextBox = new Text(shell, SWT.BORDER);
		lnameTextBox.setToolTipText("");
		lnameTextBox.setBounds(93, 122, 193, 26);
		//lnameTextBox.setText(contact.lname);
				
		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setText("First Name:");
		lblFirstName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFirstName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblFirstName.setBounds(10, 93, 77, 20);
		shell.layout();
		conn = javaConnect.ConnectDB();
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
		shell.setText("Create Contact");
		
		phonenum = new Text(shell, SWT.BORDER);
		phonenum.addListener(SWT.Verify, new Listener() {
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
		
		Label lblPhone = new Label(shell, SWT.NONE);
		lblPhone.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblPhone.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPhone.setBounds(30, 157, 57, 20);
		lblPhone.setText("Phone #:");
		
		emailTextBox = new Text(shell, SWT.BORDER);
		emailTextBox.setBounds(93, 186, 193, 26);
		
		addressTextBox = new Text(shell, SWT.BORDER);
		addressTextBox.setBounds(93, 218, 193, 26);
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblEmail.setBounds(47, 189, 40, 20);
		lblEmail.setText("Email:");
		
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblAddress.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblAddress.setBounds(30, 221, 57, 20);
		lblAddress.setText("Address:");
		
		fbTextBox = new Text(shell, SWT.BORDER);
		fbTextBox.setBounds(93, 250, 193, 26);
		
		
		twitTextBox = new Text(shell, SWT.BORDER);
		twitTextBox.setBounds(93, 282, 193, 26);
		
		Label lblFacebook = new Label(shell, SWT.NONE);
		lblFacebook.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFacebook.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblFacebook.setBounds(21, 253, 66, 20);
		lblFacebook.setText("Facebook:");
		
		Label lblTwitter = new Label(shell, SWT.NONE);
		lblTwitter.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblTwitter.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTwitter.setBounds(39, 285, 48, 20);
		lblTwitter.setText("Twitter:");
		
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sql = "INSERT INTO phonecontact VALUES(?,?,?,?,?,?,?)";
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
				shell.close();
				newWindow.open();
				
				}catch(Exception er){
					
					JOptionPane.showMessageDialog(null, er);
					shell.close();
				}
				}else {
				JOptionPane.showMessageDialog(null, "Name is required.");
				
				}
			}
		});
		
						
		btnSubmit.setBounds(103, 316, 90, 30);
		btnSubmit.setText("Submit");
		
		fnameTxtBox = new Text(shell, SWT.BORDER);
		fnameTxtBox.setToolTipText("");
		fnameTxtBox.setBounds(93, 90, 193, 26);
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblName.setBounds(10, 128, 77, 20);
		lblName.setText("Last Name:");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblNewLabel.setBounds(30, 36, 239, 45);
		lblNewLabel.setText("Create Contact");

	}
}
