package com.med.dic.search.medicine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.med.dic.MainActivity;
import com.med.dic.R;
import com.med.dic.SMDWebserviceClient;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.diary.using.MedicationDiaryActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;

public class MedicineSellingActivity extends Activity {
	public static final String DEFAULT_PACKAGE_TYPE = "Tất cả";
	private SMDWebserviceClient dbLoader;
	private ClickButtonSearchAdapter adapter;
	private ArrayList<Medicine> medicines;
	private ListView medicineLv;
	private ArrayAdapter<String> dataAdapter;
	private Spinner spn;
	private Pharmacy pharmacy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.medicine_selling);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("DANH SÁCH THUỐC BÁN");
		TextView pharmarcyTv = (TextView) findViewById(R.id.pharmarcySell);
		medicineLv = (ListView) findViewById(R.id.medicineSellingLv);
		spn = (Spinner) findViewById(R.id.pharmacySpn);
		Bundle extras = getIntent().getExtras();

		// Handle object return from another activity
		if (extras != null)
	    {
        	pharmacy = (Pharmacy) extras.getSerializable(MedicineDetailActivity.MESSAGE);
        	pharmarcyTv.setText(pharmacy.getPharmacyName());
        	dbLoader = new SMDWebserviceClient();
        	medicines = new ArrayList<Medicine>();
        	ArrayList<String> list = new ArrayList<String>();
        	if (isOnline()) {
        		try {
	        		medicines = dbLoader.loadMedicinesInPharmacyFromWS(pharmacy.getPharmacyId());
	        		list = dbLoader.loadPackageNameFromWS();
	        	} catch (Exception ex) {
	        		ex.printStackTrace();
	        		showAlertServerConnection();
	        	}
	    	} else {
	    		showAlertInternetConnection();
	    	}
        	for (int i = 0; i < medicines.size(); i++) {

			}
        	adapter = new ClickButtonSearchAdapter(MedicineSellingActivity.this, android.R.layout.simple_list_item_1, medicines);
        	medicineLv.setAdapter(adapter);
        	dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        	spn.setAdapter(dataAdapter);
		}

		// Handle action click item on drop-down list
		spn.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
				String name = dataAdapter.getItem(position);
				if(!name.equals(DEFAULT_PACKAGE_TYPE)) {
//					Map<String, Medicine> key = new HashMap<String, Medicine>();
//					for (Medicine medicine : medicines) {
//						key.put(medicine.getTypeOfPackage(), medicine);
//					}
//
					ArrayList<Medicine> myList = new ArrayList<Medicine>();
//					if (key.containsKey(name)) {
//						myList.add(key.get(name));
//					}
					for (int i = 0; i < medicines.size(); i++) {
						String[] med = medicines.get(i).getTypeOfPackage().split(", ");
						for (int j = 0; j < med.length; j++) {
							if (med[j].equals(name)) {
								myList.add(medicines.get(i));
							}
						}
//						if(medicines.get(i).getTypeOfPackage().contains(name)) {
//
//						}
					}
					adapter = new ClickButtonSearchAdapter(MedicineSellingActivity.this, android.R.layout.simple_list_item_1, myList);
		        	medicineLv.setAdapter(adapter);
				} else {
					adapter = new ClickButtonSearchAdapter(MedicineSellingActivity.this, android.R.layout.simple_list_item_1, medicines);
		        	medicineLv.setAdapter(adapter);
				}
		    }

		    @Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // your code here
		    }
		});

		// Handle action click item on list view
		medicineLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Medicine md = adapter.getItem(position);
				int k = md.getMedicineId();
				Map<Integer, Medicine> key = new HashMap<Integer, Medicine>();
				for (Medicine medicine : medicines) {
					key.put(medicine.getMedicineId(), medicine);
				}
				Medicine medicine = key.get(k);
				Intent _intent = new Intent(MedicineSellingActivity.this, MedicineDetailActivity.class);
				_intent.putExtra(SearchMedicineActivity.MESSAGE, medicine);
				startActivity(_intent);
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
	public void msToSearchMedicine(View view) {
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

	/* Check internet connection */
	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}
		return false;
	}

	/*
	 * Warning message when connection fail
	 */
	public void showAlertServerConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(MedicineSellingActivity.this);
		_builder.setTitle("Lỗi kết nối với máy chủ !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(MedicineSellingActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}

	/*
	 * Warning message when connection fail
	 */
	public void showAlertInternetConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(MedicineSellingActivity.this);
		_builder.setTitle("Lỗi kết nối mạng !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(MedicineSellingActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}
}
