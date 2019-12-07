
import org.eclipse.swt.graphics.Image;



import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * Search Page Map View. Shows a Map as a background.
 * Needed to make a separate file due to SQL limitations
 * @author William Watson
 * Could not be possible without Yifei's code in staticMap.java.
 * Tested By: William, Andrew, Yifei.
 *
 */
public class searchViewMap {	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			searchViewMap window = new searchViewMap();
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
		Image oldImage = new Image(display, searchViewContact.fname+searchViewContact.lname+".png");
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