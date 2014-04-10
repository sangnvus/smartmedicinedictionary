package com.med.dic.find.pharmarcy;

import java.util.ArrayList;

import org.w3c.dom.Document;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.med.dic.MainActivity;
import com.med.dic.R;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.diary.using.MedicationDiaryActivity;
import com.med.dic.search.medicine.MedicineDetailActivity;
import com.med.dic.search.medicine.Pharmacy;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class PharmacyOnMapActivity extends FragmentActivity implements LocationListener {

	private Pharmacy pharmacy;
	private GoogleMap map;
	private Location location;
	private LocationManager locationManager;

	// flag for GPS status
	boolean isGPSEnabled = false;

	// flag for network status
	boolean isNetworkEnabled = false;

	// flag for GPS status
	boolean canGetLocation = false;

	// The minimum distance to change Updates in meters
	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters

	// The minimum time between updates in milliseconds
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.pharmacy_map);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("VỊ TRÍ NHÀ THUỐC");

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			pharmacy = (Pharmacy) extras
					.getSerializable(MedicineDetailActivity.MESSAGE);
		}

		int status = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getBaseContext());
		if (!isOnline()) {
			showAlertInternetConnection();
		}

		// Showing status
		if (status != ConnectionResult.SUCCESS) { // Google Play Services are
													// not available

			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this,
					requestCode);
			dialog.show();

		} else { // Google Play Services are available
			// Getting reference to the SupportMapFragment of find_pharmacy.xml
			SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager()
					.findFragmentById(R.id.map);

			// Getting GoogleMap object from the fragment
			map = fm.getMap();

			LatLng latLng = new LatLng(pharmacy.getLatitude(),
					pharmacy.getLongitude());
			map.clear();
			map.addMarker(new MarkerOptions()
					.title(pharmacy.getPharmacyName())
					.snippet(pharmacy.getAddress())
					.position(latLng)
					.icon(BitmapDescriptorFactory
							.fromResource(com.med.dic.R.drawable.normal_location)));

			// Showing the current location in Google Map
			map.moveCamera(CameraUpdateFactory.newLatLng(latLng));

			// Zoom in the Google Map
			map.animateCamera(CameraUpdateFactory.zoomTo(12));
		}

		Button direction = (Button) findViewById(R.id.direction);
		direction.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				map.setMyLocationEnabled(true);
				map.getUiSettings().setMyLocationButtonEnabled(false);
				getLocation();
			}
		});
	}

	/**
	 * go to find location function
	 */
	public void pmToFindPharmacy(View view) {
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to search medicine function
	 */
	public void pmToSearchMedicine(View view) {
		Intent _intent = new Intent(this, SearchMedicineActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to historical using function
	 */
	public void pmToDiaryUsing(View view) {
		Intent _intent = new Intent(this, MedicationDiaryActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to about us
	 */
	public void pmToAboutUs(View view) {
		Intent _intent = new Intent(this, AboutUsActivity.class);
		startActivity(_intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		AlertDialog.Builder _builder = new AlertDialog.Builder(
				PharmacyOnMapActivity.this);
		_builder.setTitle("Lỗi kết nối mạng !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(PharmacyOnMapActivity.this,
								MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}

	/**
	 * draw line between two location
	 */
	public void drawMap(LatLng from) {
		Direction md = new Direction();
		LatLng to = new LatLng(pharmacy.getLatitude(), pharmacy.getLongitude());
		Document doc = md.getDocument(from, to, Direction.MODE_DRIVING);
		ArrayList<LatLng> directionPoint = md.getDirection(doc);
		PolylineOptions rectLine = new PolylineOptions().width(8).color(
				Color.RED); // Màu và độ rộng

		for (int i = 0; i < directionPoint.size(); i++) {
			rectLine.add(directionPoint.get(i));
		}
		map.addPolyline(rectLine);

//		CircleOptions circleOptions = new CircleOptions()
//	    .center(from)
//	    .radius(50)
//	    .fillColor(0x65A8DFF4)
//	    .strokeColor(Color.rgb(0, 153, 204))
//	    .strokeWidth(2);
//
//	    // Get back the mutable Circle
//	    Circle circle = map.addCircle(circleOptions);

		map.addMarker(new MarkerOptions()
		.title("Vị trí của bạn")
		.position(from)
		.icon(BitmapDescriptorFactory
				.fromResource(com.med.dic.R.drawable.your_location)));

		// Showing the current location in Google Map
		map.moveCamera(CameraUpdateFactory.newLatLng(from));

		// Zoom in the Google Map
		map.animateCamera(CameraUpdateFactory.zoomTo(15));
	}

	/**
	 * Get current location using location manager
	 */
	public void getLocation() {
		try {
			locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			// getting GPS status
			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);

			// getting network status
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (!isGPSEnabled && !isNetworkEnabled) {
				// no network provider is enabled
			} else {
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							onLocationChanged(location);
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
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

		LatLng from = new LatLng(latitude, longitude);
		drawMap(from);
	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}
}
