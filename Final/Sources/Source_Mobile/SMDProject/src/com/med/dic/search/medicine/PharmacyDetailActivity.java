package com.med.dic.search.medicine;

import java.io.IOException;
import java.util.Properties;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.med.dic.MainActivity;
import com.med.dic.R;
import com.med.dic.SMDWebserviceClient;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.diary.using.MedicationDiaryActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;
import com.med.dic.find.pharmarcy.PharmacyOnMapActivity;

public class PharmacyDetailActivity extends FragmentActivity {

	private Pharmacy pharmacy;
	private SMDWebserviceClient client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.pharmacy_detail);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("THÔNG TIN NHÀ THUỐC");

		TextView nameTv = (TextView) findViewById(R.id.pharmacyName);
		TextView addressTv = (TextView) findViewById(R.id.addressDetail);
		TextView homePhoneTv = (TextView) findViewById(R.id.homePhoneDetail);
		TextView businessLicenseTv = (TextView) findViewById(R.id.businessLicenseDetail);
		TextView gppNoTv = (TextView) findViewById(R.id.gppNoDetail);
		TextView ofCompanyTv = (TextView) findViewById(R.id.ofCompanyDetail);
		TextView notesTv = (TextView) findViewById(R.id.notesDetail);

		// Handle object return from another activity
		Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
        	pharmacy = (Pharmacy) extras.getSerializable(FindPharmarcyActivity.MESSAGE);
        	if (pharmacy != null) {
        		client = new SMDWebserviceClient();
        		try {
					pharmacy = client.loadPharmacyDataByNameAndAddressFromWS(pharmacy.getPharmacyName(), pharmacy.getAddress());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					showAlertServerConnection();
					e.printStackTrace();
				}
        	} else {
        		pharmacy = (Pharmacy) extras.getSerializable(MedicineDetailActivity.MESSAGE);
        	}
        	nameTv.setText(pharmacy.getPharmacyName());
        	addressTv.setText(pharmacy.getAddress());
        	homePhoneTv.setText(pharmacy.getHomePhone());
        	businessLicenseTv.setText(pharmacy.getBusinessLicense());
        	if (!pharmacy.getGppNo().equals("anyType{}")) {
        		gppNoTv.setText(pharmacy.getGppNo());
        	}
        	if (!pharmacy.getPharmaceutical().equals("anyType{}")) {
        		ofCompanyTv.setText(pharmacy.getPharmaceutical());
        	}
        	if (!pharmacy.getNotes().equals("anyType{}")) {
        		notesTv.setText(pharmacy.getNotes());
        	}
        	Properties prop = new Properties();
    		try {
    			prop.load(SMDWebserviceClient.class.getClassLoader().getResourceAsStream("application.properties"));
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
    		String HOST_NAME = prop.getProperty("smd.host.name");
    		String APP_NAME = prop.getProperty("smd.app.name");
    		String FOLDER_NAME = prop.getProperty("smd.folder.pharmacy.img");
    		String imgPath = HOST_NAME + APP_NAME + FOLDER_NAME + pharmacy.getImagePath();

        	//hard-code
        	AQuery aq = new AQuery(this);
            aq.id(R.id.pharmacyImage).image(imgPath, true, true, 120, 0);//50 = imageWidth

        }

        Button locationDetailBtn = (Button) findViewById(R.id.locationDetail);
        locationDetailBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(PharmacyDetailActivity.this, PharmacyOnMapActivity.class);
				intent.putExtra(MedicineDetailActivity.MESSAGE, pharmacy);
				startActivity(intent);
			}
		});

	}


	/*
	 * go to find location function
	 */
	public void smToFindLocation(View view) {
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/*
	 * go to historical using function
	 */
	public void smToDiaryUsing(View view) {
		Intent _intent = new Intent(this, MedicationDiaryActivity.class);
		startActivity(_intent);
	}

	/*
	 * go to search medicine function
	 */
	public void pdToSearchMedicine(View view) {
		Intent _intent = new Intent(this, SearchMedicineActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to about us
	 */
	public void smToAboutUs(View view) {
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
		AlertDialog.Builder _builder = new AlertDialog.Builder(PharmacyDetailActivity.this);
		_builder.setTitle("Lỗi kết nối mạng !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(PharmacyDetailActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();_builder.show();
	}

	/**
	 * Warning message when connection web service fail
	 */
	public void showAlertServerConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(PharmacyDetailActivity.this);
		_builder.setTitle("Lỗi kết nối với máy chủ !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(PharmacyDetailActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}
}
