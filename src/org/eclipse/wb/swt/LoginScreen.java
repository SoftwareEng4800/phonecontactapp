package org.eclipse.wb.swt;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

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
		shlLoginScreen.setSize(450, 300);
		shlLoginScreen.setText("Login Screen");
		
		text = new Text(shlLoginScreen, SWT.BORDER);
		text.setBounds(173, 105, 135, 26);
		
		text_1 = new Text(shlLoginScreen, SWT.BORDER);
		text_1.setBounds(173, 137, 135, 26);
		
		Label lblUsername = new Label(shlLoginScreen, SWT.NONE);
		lblUsername.setBounds(97, 108, 70, 20);
		lblUsername.setText("Username:");
		
		Label lblPassword = new Label(shlLoginScreen, SWT.NONE);
		lblPassword.setBounds(103, 140, 64, 20);
		lblPassword.setText("Password:");
		
		Button btnLogin = new Button(shlLoginScreen, SWT.NONE);
		btnLogin.setBounds(183, 174, 90, 30);
		btnLogin.setText("Login");

	}

}
