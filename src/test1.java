import java.io.IOException;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
/**
 * Test File for Lat and Long coordinates
 * @author Yifei Shi
 * Wrote tests: William, Yifei
 * Tested By: Andrew, Yifei, William
 *
 */
public class test1 {

	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
				.apiKey("AIzaSyCj11Xr9nAVmCWloNL7O7Ea3FNhUanTfz8")
				.build();
		GeocodingResult[] results = GeocodingApi.geocode(context,
				"1600 Amphitheatre Parkway Mountain View").await();
		/**
		 * yifei's code
		 */
		com.google.maps.model.Geometry x = results[0].geometry;
		/**
		 * This part wrote by william Watson
		 * I needed to grab the strings out due to how geometry reported the lat and long in a wierd
		 * class called Geometry.
		 * So I split the string a bunch to get what I needed.
		 */
		String y = x.toString();
		String[] yy = y.split(":");
		String[] yz = yy[1].split(",");
		String[] zz = yz[1].split(" ");
		
		System.out.println(zz[0]);
		System.out.println(yz[0]);
		System.out.println(yy[1]);
		System.out.println(y);		
		System.out.println(results[0].geometry);

	}

}