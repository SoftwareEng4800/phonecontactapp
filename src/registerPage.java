import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class registerPage {

	protected Shell shellRegister;
	private Text text;
	private Text text_1;
	private Text text_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			registerPage window = new registerPage();
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
		shellRegister.open();
		shellRegister.setLocation(600, 100);
		shellRegister.layout();
		while (!shellRegister.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shellRegister = new Shell();
		shellRegister.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shellRegister.setSize(316, 425);
		shellRegister.setText("SWT Application");
		
		Label lblRegister = new Label(shellRegister, SWT.NONE);
		lblRegister.setFont(SWTResourceManager.getFont("Segoe UI", 20, SWT.BOLD));
		lblRegister.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblRegister.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblRegister.setBounds(89, 55, 147, 73);
		lblRegister.setText("Register");
		
		Label lblNewLabel = new Label(shellRegister, SWT.NONE);
		lblNewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel.setBounds(65, 163, 70, 20);
		lblNewLabel.setText("Username:");
		
		Label lblNewLabel_1 = new Label(shellRegister, SWT.NONE);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_1.setBounds(71, 195, 64, 20);
		lblNewLabel_1.setText("Password:");
		
		Label lblNewLabel_2 = new Label(shellRegister, SWT.NONE);
		lblNewLabel_2.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lblNewLabel_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblNewLabel_2.setBounds(10, 227, 125, 20);
		lblNewLabel_2.setText("Re-enter Password:");
		
		text = new Text(shellRegister, SWT.BORDER);
		text.setBounds(147, 160, 115, 26);
		
		text_1 = new Text(shellRegister, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(147, 192, 115, 26);
		
		text_2 = new Text(shellRegister, SWT.BORDER | SWT.PASSWORD);
		text_2.setBounds(147, 224, 115, 26);
		
		Button btnNewButton = new Button(shellRegister, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LoginScreen newWindow = new LoginScreen();
				String newText1 = text_1.toString();
				String newText2 = text_2.toString();
				if (text_1.getText().equals(text_2.getText())) {
					shellRegister.close();
					newWindow.open();
				}
			}
		});
		btnNewButton.setBounds(107, 267, 90, 30);
		btnNewButton.setText("Submit");

	}
}
