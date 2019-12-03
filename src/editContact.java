import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;

public class editContact {

	protected Shell shell;

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
		shell.open();
		shell.setLocation(800, 200);
		contactHome contact = new contactHome();
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblNewLabel.setBounds(10, 27, 118, 45);
		lblNewLabel.setText("Editing: ");
		
		Label lblFirstName = new Label(shell, SWT.NONE);
		lblFirstName.setText("First Name:");
		lblFirstName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFirstName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblFirstName.setBounds(10, 93, 77, 20);
		Text fnameTxtBox = new Text(shell, SWT.BORDER);
		fnameTxtBox.setToolTipText("");
		fnameTxtBox.setBounds(93, 90, 193, 26);
		fnameTxtBox.setText(contact.fname);
		
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblName.setBounds(10, 128, 77, 20);
		lblName.setText("Last Name:");		
		Text lnameTextBox = new Text(shell, SWT.BORDER);
		lnameTextBox.setToolTipText("");
		lnameTextBox.setBounds(93, 122, 193, 26);
		lnameTextBox.setText(contact.lname);		
		
		Label lblPhone = new Label(shell, SWT.NONE);
		lblPhone.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblPhone.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPhone.setBounds(30, 157, 57, 20);
		lblPhone.setText("Phone #:");
		Text phonenum = new Text(shell, SWT.BORDER);
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
		phonenum.setText(contact.phone);	
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblEmail.setBounds(47, 189, 40, 20);
		lblEmail.setText("Email:");
		Text emailTextBox = new Text(shell, SWT.BORDER);
		emailTextBox.setBounds(93, 186, 193, 26);
		emailTextBox.setText(contact.email);
		
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblAddress.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblAddress.setBounds(30, 221, 57, 20);
		lblAddress.setText("Address:");
		Text addressTextBox = new Text(shell, SWT.BORDER);
		addressTextBox.setBounds(93, 218, 193, 26);
		addressTextBox.setText(contact.address);		
		
		Label lblFacebook = new Label(shell, SWT.NONE);
		lblFacebook.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblFacebook.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblFacebook.setBounds(21, 253, 66, 20);
		lblFacebook.setText("Facebook:");
		Text fbTextBox = new Text(shell, SWT.BORDER);
		fbTextBox.setBounds(93, 250, 193, 26);
		fbTextBox.setText(contact.facebook);
		
		Label lblTwitter = new Label(shell, SWT.NONE);
		lblTwitter.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblTwitter.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblTwitter.setBounds(39, 285, 48, 20);
		lblTwitter.setText("Twitter:");
		Text twitTextBox = new Text(shell, SWT.BORDER);
		twitTextBox.setBounds(93, 282, 193, 26);
		twitTextBox.setText(contact.twitter);
		
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
		shell.setText("SWT Application");

	}

}
