package com.google.maps;

import java.io.IOException;
import com.google.maps.errors.ApiException;




import com.google.maps.StaticMapsRequest.Markers;
import com.google.maps.StaticMapsRequest.Markers.CustomIconAnchor;
import com.google.maps.StaticMapsRequest.Markers.MarkersSize;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;



public class initialStaticMAP {
	
	  private final static int WIDTH = 640;  // width of the result map
	  private final static int HEIGHT = 480;		// height of the result map 

	  
	  public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		  
		  
		GeoApiContext context = new GeoApiContext.Builder()   
		    .apiKey("AIzaSyCj11Xr9nAVmCWloNL7O7Ea3FNhUanTfz8")  // build the API key ; store the key in variable name context
		    .build();
		
		LatLng ContactLocation = new LatLng(37.5253538802915, -120.8558066197085); /* set up the location of the marker 
																				  type: Latlng  variable name: ContactLocation
																				  uses com.google.maps.model.LatLng;  */
		
		 StaticMapsRequest req = StaticMapsApi.newRequest(context, new Size(WIDTH, HEIGHT));  /* make map request (req) passing contest and size
		 																					 construct size using com.google.maps.model.Size */	
	  
	      	Markers ContactMarkers = new Markers();        // set up the markers (type: Markers , variable name: ContactMarkers)
	      	ContactMarkers.size(MarkersSize.small);   		// marker size
	      	ContactMarkers.customIcon("http://not.a/real/url", CustomIconAnchor.bottomleft, 2); // marker icon
	      	ContactMarkers.color("red");		// marker color 
	      	ContactMarkers.label("A");		// ?marker label
	      	ContactMarkers.addLocation(ContactLocation);    	// add the location to ContactMarkers
	   
	      	req.markers(ContactMarkers);     /* add the ContactMarkers to the map request (req) 
	      								  		sent ContactMarkers to the class : markers (located in StaticMapsRequest.java)
	      									*/
	      ByteArrayInputStream bais = new ByteArrayInputStream(req.await().imageData);
	      BufferedImage img = ImageIO.read(bais);           // img is the map that got back        		
	      
	      File outputfile = new File("mapone.png");  // save it in a file (named mapone.png here) 
	      ImageIO.write(img, "png", outputfile);     // print img in mapone.png
	      System.out.println("Saved!");    // just prints out Saved 
		
		
	
	}
}
