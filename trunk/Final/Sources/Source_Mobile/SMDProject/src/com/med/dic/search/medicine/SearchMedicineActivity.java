package com.med.dic.search.medicine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.med.dic.MainActivity;
import com.med.dic.R;
import com.med.dic.SMDWebserviceClient;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.diary.using.MedicationDiaryActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;

public class SearchMedicineActivity extends Activity {
	public static String MESSAGE = "com.med.dic.search.medicine.SearhMedicineActivity";
	private static int NUMBER_ITEM_AUTOCOMPLETE = 50;
	private static int NUMBER_ITEM_BTN_SEARCH = 100;
	private ListView listItemsView;
	private ImageView imageView;
	private EditText mSearchEdt;
	private ArrayList<Medicine> listMedicine;
	private ArrayList<Medicine> listMedicineAutocomplete = new ArrayList<Medicine>();
	private ArrayList<Medicine> listMedicineClickBtn = new ArrayList<Medicine>();
	private ArrayList<Medicine> listStart = new ArrayList<Medicine>();
	private AutocompleteSearchAdapter adapter;
	private ClickButtonSearchAdapter listAdapter;
	private boolean isAutocomplete;
	private SMDWebserviceClient dbLoader;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.search_medicine);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("TRA CỨU THUỐC");

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		listItemsView = (ListView) findViewById(R.id.list_view);
		mSearchEdt = (EditText) findViewById(R.id.inputSearch);
		if (isOnline()) {
			try {
				dbLoader = new SMDWebserviceClient();
				listMedicine = dbLoader.loadMedicinesDataFromWS(mSearchEdt.getText().toString());
			} catch (Exception ex) {
				ex.printStackTrace();
				showAlertServerConnection();
			}
		} else {
			showAlertInternetConnection();
		}
		listItemsView.setVisibility(View.GONE);

		// Handle action click items on list view
		listItemsView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Medicine md = new Medicine();
				if (isAutocomplete) {
					md = adapter.getItem(position);
				} else {
					md = listAdapter.getItem(position);
				}
				int k = md.getMedicineId();
				Map<Integer, Medicine> key = new HashMap<Integer, Medicine>();
				for (Medicine medicine : listMedicine) {
					key.put(medicine.getMedicineId(), medicine);
				}
				Medicine medicine = key.get(k);
				Intent _intent = new Intent(SearchMedicineActivity.this, MedicineDetailActivity.class);
				_intent.putExtra(MESSAGE, medicine);
				startActivity(_intent);
			}
		});

		// Handle action text change
		mSearchEdt.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				listItemsView.setVisibility(View.VISIBLE);
				mSearchEdt = (EditText) findViewById(R.id.inputSearch);
				String items = mSearchEdt.getText().toString().trim();
				imageState(items);
				isAutocomplete = true;
				listMedicineAutocomplete.clear();
				listMedicineAutocomplete = modifyListMedicine(listMedicine, items, NUMBER_ITEM_AUTOCOMPLETE);
				if (items.equals("") || items == null) {
					adapter = new AutocompleteSearchAdapter(SearchMedicineActivity.this, android.R.layout.simple_list_item_1, listStart);
				} else {
					adapter = new AutocompleteSearchAdapter(SearchMedicineActivity.this, android.R.layout.simple_list_item_1, listMedicineAutocomplete);
				}
				listItemsView.setAdapter(adapter);
			}
		});

		// Handle action click search button
		Button btn = (Button) findViewById(R.id.buttonSearch);
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				listItemsView.setVisibility(View.VISIBLE);
				mSearchEdt = (EditText) findViewById(R.id.inputSearch);
				String items = mSearchEdt.getText().toString();
				imageState("NA");
				listMedicineClickBtn.clear();
				listMedicineClickBtn = modifyListMedicine(listMedicine, items, NUMBER_ITEM_BTN_SEARCH);
				listAdapter = new ClickButtonSearchAdapter(SearchMedicineActivity.this, android.R.layout.simple_list_item_1, listMedicineClickBtn);
				listItemsView.setAdapter(listAdapter);
				isAutocomplete = false;
				InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
				inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Modify the number of item
	 */
	private ArrayList<Medicine> modifyListMedicine(ArrayList<Medicine> listMedicine, String items, int limit) {

		ArrayList<Medicine> listShow = new ArrayList<Medicine>();
		int countList = 0;
		for (int i = 0; i < listMedicine.size(); i++) {
			if(listMedicine.get(i).getName().toLowerCase().contains(items.toLowerCase())) {
				listShow.add(listMedicine.get(i));
				countList++;
			}
			if (countList >= limit) {
				break;
			}
		}
		return listShow;
	}

	/**
	 * Set enable and disable image for advertising
	 */
	private void imageState(String txtInput) {
		imageView = (ImageView) findViewById(R.id.advertising);
		if (txtInput.equals("") || txtInput==null)
			imageView.setVisibility(View.VISIBLE);
		else
			imageView.setVisibility(View.GONE);
	}

	/**
	 * go to find location function
	 */
	public void smToFindLocation(View view) {
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to historical using function
	 */
	public void smToDiaryUsing(View view) {
		Intent _intent = new Intent(this, MedicationDiaryActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to historical using function
	 */
	public void smOnClick(View view) {

	}

	/**
	 * go to about us
	 */
	public void smToAboutUs(View view) {
		Intent _intent = new Intent(this, AboutUsActivity.class);
		startActivity(_intent);
	}

	@Override
	public void onBackPressed() {
//		   Log.i("HA", "Finishing");
		   Intent intent = new Intent(Intent.ACTION_MAIN);
		   intent.addCategory(Intent.CATEGORY_HOME);
		   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   startActivity(intent);
		 }

	/**
	 *	Check internet connection
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
	 * Warning message when connection web service fail
	 */
	public void showAlertServerConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(SearchMedicineActivity.this);
		_builder.setTitle("Lỗi kết nối với máy chủ !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(SearchMedicineActivity.this, MainActivity.class);
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
	public void showAlertInternetConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(SearchMedicineActivity.this);
		_builder.setTitle("Lỗi kết nối mạng !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(SearchMedicineActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}

}
