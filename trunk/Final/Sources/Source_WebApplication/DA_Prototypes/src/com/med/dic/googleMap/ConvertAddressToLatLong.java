package com.med.dic.googleMap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.codehaus.jackson.map.ObjectMapper;

import com.med.dic.model.Address;

public class ConvertAddressToLatLong {
	 private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json";
	 public GoogleResponse convertToLatLong(String fullAddress) throws IOException {
			URL url = new URL(URL + "?address="
					+ URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream() ;
			ObjectMapper mapper = new ObjectMapper();
			GoogleResponse response = (GoogleResponse)mapper.readValue(in,GoogleResponse.class);
			in.close();
			return response;
	   }
	 
	 public static Address returnLatLng(String address, Address addObject) throws IOException {
		try {

			GoogleResponse res = new ConvertAddressToLatLong()
					.convertToLatLong(address);
			int count = 0;
			if (res.getStatus().equals("OK")) {
				for (Result result : res.getResults()) {
					if (count == 0) {
						addObject.setLat(result.getGeometry().getLocation()
								.getLat());
						addObject.setLng(result.getGeometry().getLocation()
								.getLng());
						System.out.println(result.getGeometry().getLocation()
								.getLat());
						System.out.println(result.getGeometry().getLocation()
								.getLng());
						System.out.println(result.getGeometry()
								.getLocation_type());
					} else {
						break;
					}
					count++;
				}
			} else {
				System.out.println(res.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addObject;
	                 
	 }
}
