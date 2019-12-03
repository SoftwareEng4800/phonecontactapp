
import org.eclipse.swt.graphics.Image;



import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class showMap {
	

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
		Image oldImage = new Image(display, "test.jpg");
		Shell shell = new Shell(display);
		shell.setBackgroundImage(oldImage);
		shell.setSize(316,425);
		shell.open();
		shell.setLocation(800, 200);
		shell.layout();
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}