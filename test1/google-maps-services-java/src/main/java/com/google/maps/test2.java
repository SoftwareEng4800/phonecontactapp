package com.google.maps;

import java.io.IOException;
import com.google.maps.errors.ApiException;



import com.google.maps.StaticMapsRequest.ImageFormat;
import com.google.maps.StaticMapsRequest.Markers;
import com.google.maps.StaticMapsRequest.Markers.CustomIconAnchor;
import com.google.maps.StaticMapsRequest.Markers.MarkersSize;
import com.google.maps.StaticMapsRequest.Path;
import com.google.maps.StaticMapsRequest.StaticMapType;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import com.google.maps.model.Size;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import javax.imageio.ImageIO;



public class test2 {
	
	  private final static int WIDTH = 640;  // width of the map
	  private final static int HEIGHT = 480;		// height of the map 

	  
	  public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		  
		  
		GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey("AIzaSyCj11Xr9nAVmCWloNL7O7Ea3FNhUanTfz8")  // key
		    .build();
		
		LatLng CenterOfMap = new LatLng(37.5253538802915, -120.8558066197085); // build the center of the map 
		LatLng CenterOfMap2 = new LatLng(37.5227322802915, -120.871377919708585);
		
		 StaticMapsRequest req = StaticMapsApi.newRequest(context, new Size(WIDTH, HEIGHT));  // make map request
	      
	      
	      
	      Markers markers = new Markers();        // add the markers 
	      markers.size(MarkersSize.small);
	      markers.customIcon("http://not.a/real/url", CustomIconAnchor.bottomleft, 2);
	      markers.color("blue");
	      markers.label("A");
	      markers.addLocation(CenterOfMap);
	      markers.addLocation(CenterOfMap2);
	      
	      req.markers(markers);

	      ByteArrayInputStream bais = new ByteArrayInputStream(req.await().imageData);
	      BufferedImage img = ImageIO.read(bais); 
	      
	      File outputfile = new File("mapone.png");  // save it in a file
	      ImageIO.write(img, "png", outputfile);
	      System.out.println("Saved!");
		
		
	
	}
}
