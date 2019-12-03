import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class viewContact {
		
	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			viewContact window = new viewContact();
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
		shell.setLocation(800, 200);
		shell.open();
		contactHome view = new contactHome();
		Label fnameLabel = new Label(shell, SWT.NONE);
		fnameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		fnameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		fnameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		fnameLabel.setBounds(33, 80, 225, 31);
		fnameLabel.setText(contactHome.fname);
		
		Label lnameLabel = new Label(shell, SWT.NONE);
		lnameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lnameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lnameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lnameLabel.setBounds(33, 117, 225, 31);
		lnameLabel.setText(contactHome.lname);
		
		Label phoneLabel = new Label(shell, SWT.NONE);
		phoneLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		phoneLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		phoneLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		phoneLabel.setBounds(33, 154, 225, 31);
		phoneLabel.setText(contactHome.phone);
		
		Label emailLabel = new Label(shell, SWT.NONE);
		emailLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		emailLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		emailLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		emailLabel.setBounds(33, 191, 225, 31);
		emailLabel.setText(contactHome.email);
		
		Label addressLabel = new Label(shell, SWT.NONE);
		addressLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		addressLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		addressLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		addressLabel.setBounds(33, 228, 225, 31);
		addressLabel.setText(contactHome.address);
		
		Label facebookLabel = new Label(shell, SWT.NONE);
		facebookLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		facebookLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		facebookLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		facebookLabel.setBounds(33, 265, 225, 31);
		facebookLabel.setText(contactHome.facebook);
		
		Label twitterLabel = new Label(shell, SWT.NONE);
		twitterLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		twitterLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		twitterLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		twitterLabel.setBounds(33, 302, 225, 31);
		twitterLabel.setText(contactHome.twitter);
		
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
		
		Button viewMapBtn = new Button(shell, SWT.NONE);
		viewMapBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showMap show = new showMap();
				show.open();
			}
		});
		viewMapBtn.setBounds(75, 338, 150, 30);
		viewMapBtn.setText("View Map Location");
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
		
		
		Label viewLabel = new Label(shell, SWT.NONE);
		viewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		viewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		viewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		viewLabel.setBounds(23, 34, 119, 31);
		viewLabel.setText("Viewing:");
	    
		
	}
}
