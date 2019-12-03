package com.google.maps;

import java.io.IOException;

import java.util.Arrays; //

import com.google.appengine.repackaged.com.google.gson.Gson;
import com.google.appengine.repackaged.com.google.gson.GsonBuilder;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

public class test1 {
	public static void main(String[] args) throws ApiException, InterruptedException, IOException {
		GeoApiContext context = new GeoApiContext.Builder()
		    .apiKey("AIzaSyCj11Xr9nAVmCWloNL7O7Ea3FNhUanTfz8")  // key
		    .build();
		GeocodingResult[] results =  GeocodingApi.geocode(context,
		    "2014 W Monte Vista Ave, Turlock, CA 95382").await();  // user address
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		//System.out.println(gson.toJson(results[0].addressComponents));  // prints out the address entered in JSON
		
		System.out.println(gson.toJson(results[0].geometry)); // reads result in JSON for the address just entered
		
		//System.out.println(Arrays.toString(results)); // prints out results as a whole
	}
}
