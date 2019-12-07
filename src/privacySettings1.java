import java.util.Scanner;

/**
 * Could be used in the future when it is needed.
 * @author Gurudeta Singh
 * This file was not used because there was no reason to not show the details, we have
 * no other users. This file could be helpful in the distant future if implemented
 * with the project correctly.
 *
 */
public class privacySettings1 {

	public static void main(String[] args) {
		Scanner myObj = new Scanner(System.in);
		
		// Asks users whether or not they want to show their location
		System.out.println("Would you like to show your location to other users?" + " yes/no?");
		String response = myObj.nextLine();
		if (response.equalsIgnoreCase("yes")) {
			System.out.println("Your location will be displayed to other users.");
		}
		else {
			System.out.println("Your location will not be displayed to other users.");

		}
		
		// Asks users whether or not they want to share their social media
		System.out.println("Would you like to share your social media with other users?" + " yes/no");
		String answer = myObj.nextLine();
		if (answer.equalsIgnoreCase("yes")) {
			System.out.println("Your social media can be viewed by other users.");
		}
		else {
			System.out.println("Your social media can not not be viewed by other users.");	
		}
		myObj.close();
	}  
}
