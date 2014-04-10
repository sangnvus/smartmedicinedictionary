package com.med.dic.webservice;

import java.net.URLEncoder;

public class demo {

	private static final String URL = "http://maps.googleapis.com/maps/api/geocode/json"; //- See more at: http://www.javaroots.com/2013/08/convert-location-lat-long-java.html#sthash.vOlRGpen.dpuf
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String fullAddress  = "Trung kính, yên hòa, hà nội, việt nam";
			java.net.URL url = new java.net.URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false");
			fullAddress.replace(",", "").trim();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/*public GoogleResponse convertToLatLong (String fullAddress) throws IOException {
		
	}*/
}
