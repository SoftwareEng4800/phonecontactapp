import java.util.Scanner;
/**
 * This file is used to calculate our distance using owners Lat and Long vs
 * their contacts Lat and Long. Unfortunately, it calculates from A to B, 
 * not travel distance. So it calculates in a straight line, literally.
 * If you run this actual file, there are a couple of tests in this file.
 * They take user input and then converts them to distance.
 * @author Andrew Aguilar
 * Tested By: Andrew Aguilar
 * Debugged By: Yifei Shi, William Watson
 *
 */
public class distanceFinder {
	/**
	 * Constructor to calculate distances for distance table
	 * @param lat1
	 * @param lat2
	 * @param lon1
	 * @param lon2
	 * @return
	 */
	public static double distance(double lat1, double lat2, double lon1, double lon2) {
		lon1 = Math.toRadians(lon1);
		lon2 = Math.toRadians(lon2);
		lat1 = Math.toRadians(lat1);
		lat2 = Math.toRadians(lat2);
		
		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		
		double c = 2 * Math.asin(Math.sqrt(a));
		
		double radius = 3956;
		
		return(Math.round(c * radius));
				
	}
	/**
	 * our testing. Takes user input in console.
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Input the Lat of Coodinate 1: ");
		double lat1 = input.nextDouble();
		System.out.print("Input the Lon of Coodinate 1: ");
		double lon1 = input.nextDouble();
		System.out.print("Input the Lat of Coodinate 2: ");
		double lat2 = input.nextDouble();
		System.out.print("Input the Lon of Coodinate 2: ");
		double lon2 = input.nextDouble();
		
		System.out.println(distance(lat1,lat2,lon1,lon2) + " miles");
		
		input.close();

	}

}
