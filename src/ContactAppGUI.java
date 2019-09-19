import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import java.sql.*;

import javax.swing.JOptionPane;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ContactAppGUI {
	public Connection conn = null;
	public PreparedStatement pst = null;
	protected Shell shell;
	private Text phonenum;
	private Text emailTextBox;
	private Text addressTextBox;
	private Text fbTextBox;
	private Text twitTextBox;
	private Text nameTxtBox;

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
		shell.setSize(314, 425);
		shell.setText("Create Contact");
		
		phonenum = new Text(shell, SWT.BORDER);
		phonenum.setBounds(93, 154, 193, 26);
		
		Label lblPhone = new Label(shell, SWT.NONE);
		lblPhone.setBounds(30, 157, 57, 20);
		lblPhone.setText("Phone #:");
		
		emailTextBox = new Text(shell, SWT.BORDER);
		emailTextBox.setBounds(93, 186, 193, 26);
		
		addressTextBox = new Text(shell, SWT.BORDER);
		addressTextBox.setBounds(93, 218, 193, 26);
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setBounds(47, 189, 40, 20);
		lblEmail.setText("Email:");
		
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setBounds(30, 221, 57, 20);
		lblAddress.setText("Address:");
		
		fbTextBox = new Text(shell, SWT.BORDER);
		fbTextBox.setBounds(93, 250, 193, 26);
		
		twitTextBox = new Text(shell, SWT.BORDER);
		twitTextBox.setBounds(93, 282, 193, 26);
		
		Label lblFacebook = new Label(shell, SWT.NONE);
		lblFacebook.setBounds(21, 253, 66, 20);
		lblFacebook.setText("Facebook:");
		
		Label lblTwitter = new Label(shell, SWT.NONE);
		lblTwitter.setBounds(39, 285, 48, 20);
		lblTwitter.setText("Twitter:");
		
		Button btnSubmit = new Button(shell, SWT.NONE);
		btnSubmit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sql = "INSERT INTO phonecontact VALUES(?,?,?,?,?,?)";
				try {
				
				pst = conn.prepareStatement(sql);
				pst.setString(1, nameTxtBox.getText());
				pst.setString(2, phonenum.getText());
				pst.setString(3, emailTextBox.getText());
				pst.setString(4, addressTextBox.getText());
				pst.setString(5, fbTextBox.getText());
				pst.setString(6, twitTextBox.getText());
			
				
				pst.executeUpdate();
				conn.close();
				contactHome newWindow = new contactHome();
				shell.close();
				newWindow.open();
				
				}catch(Exception er){
					
					JOptionPane.showMessageDialog(null, er);
					shell.close();
				}
			}
		});
		
						
		btnSubmit.setBounds(129, 314, 90, 30);
		btnSubmit.setText("Submit");
		
		nameTxtBox = new Text(shell, SWT.BORDER);
		nameTxtBox.setBounds(93, 122, 193, 26);
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(39, 125, 48, 20);
		lblName.setText("Name:");

	}
}
