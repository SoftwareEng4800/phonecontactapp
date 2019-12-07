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
/**
 * View Contact UI. Grabs the local variables in contactHome.java
 * and puts them in a viewable window in this UI. You can then navigate
 * via the buttons.
 * @author William Watson
 * Tested By: William Watson
 *
 */
public class viewContact {
	//exporting variables
		public static String fname = "";
		public static String lname = "";
	protected Shell shlView;

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
		shlView.setLocation(800, 200);
		shlView.open();
		@SuppressWarnings("unused")
		contactHome view = new contactHome();
		Label fnameLabel = new Label(shlView, SWT.NONE);
		fnameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		fnameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		fnameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		fnameLabel.setBounds(33, 80, 225, 31);
		fnameLabel.setText(contactHome.fname);
		
		Label lnameLabel = new Label(shlView, SWT.NONE);
		lnameLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lnameLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lnameLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		lnameLabel.setBounds(33, 117, 225, 31);
		lnameLabel.setText(contactHome.lname);
		
		Label phoneLabel = new Label(shlView, SWT.NONE);
		phoneLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		phoneLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		phoneLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		phoneLabel.setBounds(33, 154, 225, 31);
		phoneLabel.setText(contactHome.phone);
		
		Label emailLabel = new Label(shlView, SWT.NONE);
		emailLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		emailLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		emailLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		emailLabel.setBounds(33, 191, 225, 31);
		emailLabel.setText(contactHome.email);
		
		Label addressLabel = new Label(shlView, SWT.NONE);
		addressLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		addressLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		addressLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		addressLabel.setBounds(33, 228, 225, 31);
		addressLabel.setText(contactHome.address);
		
		Label facebookLabel = new Label(shlView, SWT.NONE);
		facebookLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		facebookLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		facebookLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		facebookLabel.setBounds(33, 265, 225, 31);
		facebookLabel.setText(contactHome.facebook);
		
		Label twitterLabel = new Label(shlView, SWT.NONE);
		twitterLabel.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		twitterLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		twitterLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		twitterLabel.setBounds(33, 302, 225, 31);
		twitterLabel.setText(contactHome.twitter);
		
		Label backLabel = new Label(shlView, SWT.NONE);
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
				shlView.close();
				contact.open();				
			}			
		});
		
		Button viewMapBtn = new Button(shlView, SWT.NONE);
		viewMapBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fname = fnameLabel.getText();
				lname = lnameLabel.getText();
				showMap show = new showMap();
				show.open();
			}
		});
		viewMapBtn.setBounds(75, 338, 150, 30);
		viewMapBtn.setText("View Map Location");
		shlView.layout();
		
		
		
		while (!shlView.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlView = new Shell();
		shlView.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		shlView.setSize(316, 425);
		shlView.setText("View Contact");
		
		
		Label viewLabel = new Label(shlView, SWT.NONE);
		viewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		viewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		viewLabel.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		viewLabel.setBounds(23, 34, 119, 31);
		viewLabel.setText("Viewing:");
	    
		
	}
}
