import java.util.Scanner;

public class distanceFinder {

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
