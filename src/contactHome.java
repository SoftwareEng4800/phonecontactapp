import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.sql.*;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;




public class contactHome {

	protected Shell shell;

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
		shell.setSize(450, 398);
		shell.setText("Home Screen");
		
		Label lblHome = new Label(shell, SWT.NONE);
		lblHome.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		lblHome.setBounds(159, 10, 100, 42);
		lblHome.setText("Contacts");
		
		Button btnNewContact = new Button(shell, SWT.NONE);
		btnNewContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				ContactAppGUI newWindow = new ContactAppGUI();
				newWindow.open();
				
			}
		});
		btnNewContact.setBounds(169, 300, 90, 30);
		btnNewContact.setText("New Contact");

		
		
	}
}
