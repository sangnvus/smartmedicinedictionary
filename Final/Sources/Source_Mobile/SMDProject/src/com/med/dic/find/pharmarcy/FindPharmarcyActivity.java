package com.med.dic.find.pharmarcy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.med.dic.MainActivity;
import com.med.dic.R;
import com.med.dic.SMDWebserviceClient;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.diary.using.MedicationDiaryActivity;
import com.med.dic.search.medicine.Pharmacy;
import com.med.dic.search.medicine.PharmacyDetailActivity;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class FindPharmarcyActivity extends FragmentActivity implements LocationListener {

	public static final String MESSAGE = "com.med.dic.find.pharmacy.FindPharmarcyActivity";
	GoogleMap map;
	private LocationManager service;
	private SMDWebserviceClient db;
	private boolean currentLocationFlag = false;
	private String provider ;
	private Location location;
	private LocationManager locationManager;
	private ArrayList<Pharmacy> pharmacy;

	// flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute


	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.find_pharmacy);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("NHÀ THUỐC GẦN BẠN");

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);

		// Getting Google Play availability status
		checkConnectionAndGPS();
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

        	int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else {	// Google Play Services are available
        	// Getting reference to the SupportMapFragment of activity_main.xml
			SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

			// Getting GoogleMap object from the fragment
			map = fm.getMap();

			// Enabling MyLocation Layer of Google Map
			map.setMyLocationEnabled(true);
			map.getUiSettings().setMyLocationButtonEnabled(false);

			getLocation();
			map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

				@Override
				public void onInfoWindowClick(Marker arg0) {
					Intent intent = new Intent(FindPharmarcyActivity.this, PharmacyDetailActivity.class);
					String pharmacyName = arg0.getTitle();
					String address = arg0.getSnippet();
					Pharmacy pharmacy = new Pharmacy(0, pharmacyName, "", "", "", "", "", address, "", 0, 0);
					intent.putExtra(MESSAGE, pharmacy);
		            startActivity(intent);

				}
			});

        }

		// Getting reference to btn_find of the layout find_location.xml
		Button btn_find = (Button) findViewById(R.id.buttonSearch);

		// Defining button click event listener for the find button
		btn_find.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Getting reference to EditText to get the user input location
				EditText etLocation = (EditText) findViewById(R.id.et_location);

				// Getting user input location
				String location = etLocation.getText().toString();

				if (location != null && !location.equals("")) {
					new GeocoderTask().execute(location);
				}
				InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		});
	}

	// An AsyncTask class for accessing the GeoCoding Web Service
	private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

		@SuppressLint("NewApi")
		@Override
		protected List<Address> doInBackground(String... locationName) {
			// Creating an instance of Geocoder class
			Geocoder geocoder = new Geocoder(getBaseContext());
			List<Address> addresses = null;
			boolean check;

			try {
				// Getting a maximum of 1 Address that matches the input text
				check = Geocoder.isPresent();
				if (check) {
					addresses = geocoder.getFromLocationName(locationName[0], 1);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return addresses;
		}

		@Override
		protected void onPostExecute(List<Address> addresses) {

			if (addresses == null || addresses.size() == 0) {
				Toast.makeText(getBaseContext(), "Không tìm thấy địa chỉ đã nhập !",
						Toast.LENGTH_SHORT).show();
			} else {
				// Clears all the existing markers on the map
				map.clear();
				currentLocationFlag = false;
				// Adding Markers on Google Map for each matching address
				for (int i = 0; i < addresses.size(); i++) {

					Address address = addresses.get(i);
					// Creating an instance of GeoPoint, to display in Google Map
					String addressText = String.format("%s, %s",
							address.getMaxAddressLineIndex() > 0 ? address
									.getAddressLine(0) : "", address
									.getCountryName());
					findLocationAround(address.getLatitude(), address.getLongitude(), addressText);
				}
			}
		}
	}

	/**
	 * find pharmacy location around one point
	 */
	public void findLocationAround(double latitude, double longitude, String address) {
		InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		map.clear();
		LatLng from = new LatLng(latitude, longitude);
		pharmacy = new ArrayList<Pharmacy>();
		db = new SMDWebserviceClient();
		try {
			pharmacy = db.loadListPharmacyLocationFromWs(from.latitude, from.longitude);
		} catch (Exception ex) {
			ex.printStackTrace();
			showAlertServerConnection();
		}
		if (pharmacy.size() > 0) {
			LatLng to = new LatLng(pharmacy.get(0).getLatitude(), pharmacy.get(0).getLongitude());
			map.addMarker(new MarkerOptions()
			.position(to)
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.nearest_location))
			.snippet(pharmacy.get(0).getAddress())
			.title(pharmacy.get(0).getPharmacyName()));
			for (int i = 1; i < pharmacy.size(); i++) {
				LatLng around = new LatLng(pharmacy.get(i).getLatitude(), pharmacy.get(i).getLongitude());
				map.addMarker(new MarkerOptions()
						.position(around)
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.normal_location))
						.snippet(pharmacy.get(i).getAddress())
						.title(pharmacy.get(i).getPharmacyName()));
			}
			if (!currentLocationFlag) {
				stopUsingGPS();
			}
			if (isOnline()) {
				drawMap(from, to);
			}
		}
		focusLocation(latitude, longitude, address);
	}

	/**
	 * When click button show current location
	 */
	public void onClickMyLocation(View view) {

		map.clear();
		currentLocationFlag = true;
		// Creating a criteria object to retrieve provider
//        Criteria criteria = new Criteria();
//		provider = locationManager.getBestProvider(criteria, true);
//		locationManager.requestLocationUpdates(provider, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
//		location = locationManager.getLastKnownLocation(provider);
//		findLocationAround(location.getLatitude(), location.getLongitude(), "");
		getLocation();
	}

	/**
	 * zoom camera to the particular location
	 */
	public void focusLocation(double latitude, double longitude, String address) {

		// Creating a LatLng object for the current location
		LatLng latLng = new LatLng(latitude, longitude);
		if (!currentLocationFlag) {
			map.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.your_location)).title(address));
		} else {
			map.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.your_location)).title("Vị trí của bạn"));
		}

		CircleOptions circleOptions = new CircleOptions()
	    .center(new LatLng(latitude, longitude))
	    .radius(5000)
	    .fillColor(0x65A8DFF4)
	    .strokeColor(Color.rgb(0, 153, 204))
	    .strokeWidth(2);

	    // Get back the mutable Circle
	    Circle circle = map.addCircle(circleOptions);

		// Showing the current location in Google Map
		map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

		// Zoom in the Google Map
		map.animateCamera(CameraUpdateFactory.zoomTo(15));
	}

	/**
	 * draw line between two location
	 */
	public void drawMap(LatLng from, LatLng to) {
		Direction md = new Direction();

		try {
			Document doc = md.getDocument(from, to, Direction.MODE_WALKING);
			ArrayList<LatLng> directionPoint = md.getDirection(doc);
			PolylineOptions rectLine = new PolylineOptions().width(8).color(
					Color.RED); // Màu và độ rộng

			for (int i = 0; i < directionPoint.size(); i++) {
				rectLine.add(directionPoint.get(i));
			}
			map.addPolyline(rectLine);
		} catch (Exception ex) {
			showAlertServerConnection();
		}
	}

	/**
	 * check GPS
	 */
	public void checkConnectionAndGPS() {
		if (!isOnline()) {
			showAlertInternetConnection();
		} else {
			service = (LocationManager) getSystemService(LOCATION_SERVICE);
			isNetworkEnabled = service.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			isGPSEnabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
			if (!isNetworkEnabled && !isGPSEnabled) {
					showAlertGPS();
			}
		}
	}

	/**
	 * Check internet connection
	 */
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/**
	 * Warning message when connection fail
	 */
	public void showAlertInternetConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(FindPharmarcyActivity.this);
		_builder.setTitle("Lỗi kết nối mạng !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(FindPharmarcyActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}

	/**
	 * Warning message when connection fail
	 */
	public void showAlertGPS() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(FindPharmarcyActivity.this);
		_builder.setTitle("Lỗi kết nối GPS !");
		_builder.setPositiveButton("Vui lòng bật GPS",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(FindPharmarcyActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}

	/**
	 * Stop using GPS listener
	 * Calling this function will stop using GPS in your app
	 * */
	public void stopUsingGPS(){
		if(locationManager != null){
			locationManager.removeUpdates(FindPharmarcyActivity.this);
		}
	}

	/**
	 * Warning message when connection web service fail
	 */
	public void showAlertServerConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(FindPharmarcyActivity.this);
		_builder.setTitle("Lỗi kết nối với máy chủ !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(FindPharmarcyActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}

	/**
	 * Get current location using location manager
	 */
	public void getLocation() {
		try {
			locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {

				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							onLocationChanged(location);
						}
					}
				}

				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER, MIN_TIME_BW_UPDATES, MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								onLocationChanged(location);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onLocationChanged(Location location) {

		// Getting latitude of the current location
		double latitude = location.getLatitude();

		// Getting longitude of the current location
		double longitude = location.getLongitude();

		currentLocationFlag = true;
		findLocationAround(latitude, longitude, "");
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
	}

	/**
	 * go to find location function
	 */
	public void fpToFindPharmacy(View view) {
		stopUsingGPS();
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to search medicine function
	 */
	public void fpToSearchMedicine(View view) {
		stopUsingGPS();
		Intent _intent = new Intent(this, SearchMedicineActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to historical using function
	 */
	public void fpToDiaryUsing(View view) {
		stopUsingGPS();
		Intent _intent = new Intent(this, MedicationDiaryActivity.class);
		startActivity(_intent);
	}

	public void fpOnClick(View view) {

	}

	/**
	 * go to about us
	 */
	public void fpToAboutUs(View view) {
		stopUsingGPS();
		Intent _intent = new Intent(this, AboutUsActivity.class);
		startActivity(_intent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
