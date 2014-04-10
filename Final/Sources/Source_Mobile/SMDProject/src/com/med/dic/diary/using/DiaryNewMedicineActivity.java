package com.med.dic.diary.using;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.med.dic.DefaultValueConstant;
import com.med.dic.MainActivity;
import com.med.dic.R;
import com.med.dic.SMDWebserviceClient;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;
import com.med.dic.search.medicine.AutocompleteSearchAdapter;
import com.med.dic.search.medicine.Medicine;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class DiaryNewMedicineActivity extends Activity {
	private TimePickerDialog.OnTimeSetListener time;
	private Calendar myCalendar = Calendar.getInstance();
	private Button time1, time2, time3, time4, time5;
	private Button medicineCancelBtn, medicineSaveBtn;
	private boolean time1Flag, time2Flag, time3Flag, time4Flag, time5Flag;
	private static int NUMBER_ITEM_AUTOCOMPLETE = 50;
	private ArrayList<Medicine> listMedicineAutocomplete = new ArrayList<Medicine>();
	private ArrayList<Medicine> listStart = new ArrayList<Medicine>();
	private AutocompleteSearchAdapter adapter;
	private int diaryId;
	private MedicationDiaryDatabase myDB;
	private DiaryTitle diaryTitle;
	private int i = 1;
	private Intent intent;
	private SMDWebserviceClient dbLoader;
	private ArrayList<Medicine> listMedicine;
	private EditText medicineTitle;
	private ListView listItemsView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.diary_new_medicine);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("THÊM THUỐC VÀO ĐƠN THUỐC");

		myDB = new MedicationDiaryDatabase(this);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			diaryTitle = (DiaryTitle) extras.getSerializable(MedicationDiaryActivity.MESSAGE);
		}

		medicineTitle = (EditText) findViewById(R.id.diaryMedicineEdt);
		listItemsView = (ListView) findViewById(R.id.diaryMedicineLv);
		listItemsView.setVisibility(View.GONE);
		if (isOnline()) {
			try {
				dbLoader = new SMDWebserviceClient();
				listMedicine = dbLoader.loadMedicinesDataFromWS(medicineTitle.getText().toString());
			} catch (Exception ex) {
//				ex.printStackTrace();
//				showAlertServerConnection();
			}
		} else {
			listMedicine = new ArrayList<Medicine>();
		}

		// Handle action click item on ListView
		medicineTitle.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				listItemsView.setVisibility(View.VISIBLE);
				String items = medicineTitle.getText().toString().trim();
				listMedicineAutocomplete.clear();
				if (listMedicine != null || listMedicine.size() > 0) {
					listMedicineAutocomplete = modifyListMedicine(listMedicine, items, NUMBER_ITEM_AUTOCOMPLETE);

					if (listMedicineAutocomplete == null || listMedicineAutocomplete.size() == 0) {
						adapter = new AutocompleteSearchAdapter(DiaryNewMedicineActivity.this, android.R.layout.simple_list_item_1, listStart);
						listItemsView.setVisibility(View.GONE);
					} else {
						if (items.equals("") || items == null) {
							adapter = new AutocompleteSearchAdapter(DiaryNewMedicineActivity.this, android.R.layout.simple_list_item_1, listStart);
							listItemsView.setVisibility(View.GONE);
						} else
							adapter = new AutocompleteSearchAdapter(DiaryNewMedicineActivity.this, android.R.layout.simple_list_item_1, listMedicineAutocomplete);
					}
					listItemsView.setAdapter(adapter);
				}
			}
		});

		listItemsView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Medicine md = adapter.getItem(position);
				int k = md.getMedicineId();
				Map<Integer, Medicine> key = new HashMap<Integer, Medicine>();
				for (Medicine medicine : listMedicine) {
					key.put(medicine.getMedicineId(), medicine);
				}
				Medicine medicine = key.get(k);
				medicineTitle.setText(medicine.getName());
				listItemsView.setVisibility(View.GONE);
			}
		});

		medicineSaveBtn = (Button) findViewById(R.id.medicineSaveBtn);
		medicineSaveBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String medicineTitle = ((EditText) findViewById(R.id.diaryMedicineEdt)).getText().toString();
				String time1 = ((Button) findViewById(R.id.medicineTime1Btn)).getText().toString();
				String time2 = ((Button) findViewById(R.id.medicineTime2Btn)).getText().toString();
				String time3 = ((Button) findViewById(R.id.medicineTime3Btn)).getText().toString();
				String time4 = ((Button) findViewById(R.id.medicineTime4Btn)).getText().toString();
				String time5 = ((Button) findViewById(R.id.medicineTime5Btn)).getText().toString();

				String amount1 = ((EditText) findViewById(R.id.medicineAmount1Edt)).getText().toString();
				String amount2 = ((EditText) findViewById(R.id.medicineAmount2Edt)).getText().toString();
				String amount3 = ((EditText) findViewById(R.id.medicineAmount3Edt)).getText().toString();
				String amount4 = ((EditText) findViewById(R.id.medicineAmount4Edt)).getText().toString();
				String amount5 = ((EditText) findViewById(R.id.medicineAmount5Edt)).getText().toString();
				if(medicineTitle == null || medicineTitle.trim().equals("")) {
					AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryNewMedicineActivity.this);
					_builder.setTitle("Lỗi");
					_builder.setMessage("Điền thông tin vào 'Tên thuốc'");
					_builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
					_builder.show();
				} else {
					if (validateTimeAndAmount(time1, amount1) && validateTimeAndAmount(time2, amount2) && validateTimeAndAmount(time3, amount3)
							&& validateTimeAndAmount(time4, amount4) && validateTimeAndAmount(time5, amount5)) {

						myDB.open();
						Cursor cursor = myDB.getAllDiaryTitle();
						try {
							if (cursor.getCount() == 0) {
								diaryId = 1;
							} else {
								cursor.moveToFirst();
								diaryId = Integer.parseInt(cursor.getString(0)) + 1;
							}
							Cursor mCursor = myDB.getAllDiaryMedicine();

							if (diaryTitle.getMode().equals("MODE_ADD")) {
								if (mCursor.getCount() == 0) {
									myDB.insertDiaryMedicine(i, diaryId, medicineTitle, time1, time2, time3, time4, time5, amount1, amount2, amount3, amount4, amount5);
								} else {
									mCursor.moveToFirst();
									int sequence = Integer.parseInt(mCursor.getString(0)) + 1;
									myDB.insertDiaryMedicine(sequence, diaryId, medicineTitle, time1, time2, time3, time4, time5, amount1, amount2, amount3, amount4, amount5);
								}

								// Return previous screen
								intent = new Intent(DiaryNewMedicineActivity.this, DiaryNewTitleActivity.class);
								if(diaryTitle != null) {
									diaryTitle.setTitleId(diaryId);
									intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
								}
							} else if (diaryTitle.getMode().equals("MODE_EDIT")) {
								if (mCursor.getCount() == 0) {
									myDB.insertDiaryMedicine(i, diaryTitle.getTitleId(), medicineTitle, time1, time2, time3, time4, time5, amount1, amount2, amount3, amount4, amount5);
								} else {
									mCursor.moveToFirst();
									int sequence = Integer.parseInt(mCursor.getString(0)) + 1;
									myDB.insertDiaryMedicine(sequence, diaryTitle.getTitleId(), medicineTitle, time1, time2, time3, time4, time5, amount1, amount2, amount3, amount4, amount5);
								}
								intent = new Intent(DiaryNewMedicineActivity.this, DiaryEditTitleActivity.class);
								intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
							}
						} finally {
							cursor.close();
							myDB.close();
						}
						startActivity(intent);
						finish();
					} else {
						AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryNewMedicineActivity.this);
						_builder.setTitle("Lỗi");
						_builder.setMessage("'Thời gian' và 'Liều lượng' bắt buộc phải điền cùng nhau");
						_builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
							}
						});
						_builder.show();
					}
				}
			}
		});

		medicineCancelBtn = (Button) findViewById(R.id.medicineCancelBtn);
		medicineCancelBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder _builderConfirm = new AlertDialog.Builder(DiaryNewMedicineActivity.this);
				_builderConfirm.setTitle("Bạn có chắc chắn hủy bỏ không ?");
				_builderConfirm.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(DiaryNewMedicineActivity.this, DiaryNewTitleActivity.class);
						if(diaryTitle != null) {
							_intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
						}
						startActivity(_intent);
				}}).setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				_builderConfirm.show();
			}
		});

		time1 = (Button) findViewById(R.id.medicineTime1Btn);
		time2 = (Button) findViewById(R.id.medicineTime2Btn);
		time3 = (Button) findViewById(R.id.medicineTime3Btn);
		time4 = (Button) findViewById(R.id.medicineTime4Btn);
		time5 = (Button) findViewById(R.id.medicineTime5Btn);

		time = new OnTimeSetListener() {

			@Override
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
				myCalendar.set(Calendar.MINUTE, minute);
				updateLabel();

			}
		};

		time1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryNewMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time1Flag = true;
			}
		});

		time2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryNewMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time2Flag = true;
			}
		});

		time3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryNewMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time3Flag = true;
			}
		});

		time4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryNewMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time4Flag = true;
			}
		});

		time5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryNewMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time5Flag = true;
			}
		});
	}

	/* convert date picker to edit text */
	private void updateLabel() {

		String myFormat = "HH:mm"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		if (time1Flag) {
			time1 = (Button) findViewById(R.id.medicineTime1Btn);
			time1.setText(sdf.format(myCalendar.getTime()));
			time1Flag = false;
		}

		if (time2Flag) {
			time2 = (Button) findViewById(R.id.medicineTime2Btn);
			time2.setText(sdf.format(myCalendar.getTime()));
			time2Flag = false;
		}

		if (time3Flag) {
			time3 = (Button) findViewById(R.id.medicineTime3Btn);
			time3.setText(sdf.format(myCalendar.getTime()));
			time3Flag = false;
		}

		if (time4Flag) {
			time4 = (Button) findViewById(R.id.medicineTime4Btn);
			time4.setText(sdf.format(myCalendar.getTime()));
			time4Flag = false;
		}

		if (time5Flag) {
			time5 = (Button) findViewById(R.id.medicineTime5Btn);
			time5.setText(sdf.format(myCalendar.getTime()));
			time5Flag = false;
		}
	}

	/*
	 * go to find location function
	 */
	public void duToFindLocation(View view) {
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/*
	 * go to seaerch medicine function
	 */
	public void duToSearchMedicine(View view) {
		Intent _intent = new Intent(this, SearchMedicineActivity.class);
		startActivity(_intent);
	}

	/*
	 * go to diary using fuction
	 */
	public void duToDiaryUsing(View view) {
		Intent _intent = new Intent(this, MedicationDiaryActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to about us
	 */
	public void duToAboutUs(View view) {
		Intent _intent = new Intent(this, AboutUsActivity.class);
		startActivity(_intent);
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
	 * Warning message when connection web service fail
	 */
	public void showAlertServerConnection() {
		AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryNewMedicineActivity.this);
		_builder.setTitle("Lỗi kết nối với máy chủ !");
		_builder.setPositiveButton("Vui lòng thử lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent _intent = new Intent(DiaryNewMedicineActivity.this, MainActivity.class);
						startActivity(_intent);
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}


	/**
	 *
	 */
	private boolean validateTimeAndAmount(String time, String amount) {
		if (!time.equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
			if (amount.trim().equals(""))
				return false;
		} else
			if (!amount.equals(""))
				return false;
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
