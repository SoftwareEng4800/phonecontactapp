

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;

public class LoginScreen {

	protected Shell shlLoginScreen;
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LoginScreen window = new LoginScreen();
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
		shlLoginScreen.open();
		shlLoginScreen.setLocation(600, 100);
		shlLoginScreen.layout();
		while (!shlLoginScreen.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlLoginScreen = new Shell();
		shlLoginScreen.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shlLoginScreen.setSize(316, 424);
		shlLoginScreen.setText("Login Screen");
		
		text = new Text(shlLoginScreen, SWT.BORDER);
		text.setBounds(115, 155, 135, 26);
		
		text_1 = new Text(shlLoginScreen, SWT.BORDER | SWT.PASSWORD);
		
		text_1.setBounds(115, 187, 135, 26);
		
		Label lblUsername = new Label(shlLoginScreen, SWT.NONE);
		lblUsername.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblUsername.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblUsername.setBounds(39, 158, 70, 20);
		lblUsername.setText("Username:");
		
		Label lblPassword = new Label(shlLoginScreen, SWT.NONE);
		lblPassword.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPassword.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblPassword.setBounds(45, 190, 64, 20);
		lblPassword.setText("Password:");
		
		Button btnLogin = new Button(shlLoginScreen, SWT.NONE);
		btnLogin.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				contactHome newWindow = new contactHome();
				shlLoginScreen.close();
				newWindow.open();
			}
		});
		btnLogin.setBounds(160, 251, 90, 30);
		btnLogin.setText("Login");
		
		Button btnRegister = new Button(shlLoginScreen, SWT.NONE);
		btnRegister.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				registerPage newWindow = new registerPage();
				shlLoginScreen.close();
				newWindow.open();
			}
		});
		btnRegister.setText("Register");
		btnRegister.setBounds(51, 251, 90, 30);
		
		Label lblNewLabel = new Label(shlLoginScreen, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(90, 48, 115, 55);
		lblNewLabel.setText("Login");
		
	

	}
}
