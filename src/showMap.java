
import org.eclipse.swt.graphics.Image;



import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * This file is unique. Instead of allowing WindowBuilder
 * to make the shell, I built the shell on Load to use a picture
 * of the Geo Map I got from the sql query. So essentially
 * this file is just using another file as a background.
 * @author William Watson
 * Could not be possible without Yifei's code in staticMap.java
 *
 */
public class showMap {
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			showMap window = new showMap();
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
		Image oldImage = new Image(display, viewContact.fname+viewContact.lname+".png");
		Shell shell = new Shell(display);
		shell.setBackgroundImage(oldImage);
		shell.setSize(600,600);
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