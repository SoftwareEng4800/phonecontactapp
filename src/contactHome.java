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
		shell.setLocation(600, 100);
		
		List list = new List(shell, SWT.BORDER);
		list.setBackground(SWTResourceManager.getColor(SWT.COLOR_GRAY));
		list.setBounds(20, 58, 243, 214);
		list.add("Test");
		list.add("Test1");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String s = (String) list.getItem(list.getSelectionIndex());
				JOptionPane.showMessageDialog(null, s);
			}
		});
		btnNewButton.setBounds(118, 278, 90, 30);
		btnNewButton.setText("New Button");
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
		btnNewContact.setBounds(20, 278, 90, 30);
		btnNewContact.setText("New Contact");

		
		
	}
}
