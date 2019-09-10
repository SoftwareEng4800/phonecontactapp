import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;

public class ContactAppGUI {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

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
		shell.setSize(451, 378);
		shell.setText("SWT Application");
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.setBounds(175, 41, 105, 107);
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(154, 154, 144, 26);
		
		Label lblPhone = new Label(shell, SWT.NONE);
		lblPhone.setBounds(78, 160, 70, 20);
		lblPhone.setText("Phone #:");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(154, 186, 144, 26);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(154, 218, 144, 26);
		
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setBounds(99, 189, 55, 20);
		lblEmail.setText("Email:");
		
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setBounds(78, 218, 70, 20);
		lblAddress.setText("Address:");
		
		text_3 = new Text(shell, SWT.BORDER);
		text_3.setBounds(154, 249, 144, 26);
		
		text_4 = new Text(shell, SWT.BORDER);
		text_4.setBounds(154, 284, 144, 26);
		
		Label lblFacebook = new Label(shell, SWT.NONE);
		lblFacebook.setBounds(78, 255, 70, 20);
		lblFacebook.setText("Facebook:");
		
		Label lblTwitter = new Label(shell, SWT.NONE);
		lblTwitter.setBounds(78, 290, 70, 20);
		lblTwitter.setText("Twitter:");

	}
}
