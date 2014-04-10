package com.med.dic.diary.using;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

import com.med.dic.AlarmChecker;
import com.med.dic.DefaultValueConstant;
import com.med.dic.R;
import com.med.dic.SMDWebserviceClient;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;
import com.med.dic.search.medicine.AutocompleteSearchAdapter;
import com.med.dic.search.medicine.Medicine;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class DiaryEditMedicineActivity extends Activity {

	private DiaryMedicine medicine;
	private DiaryTitle diaryTitle;
	private MedicationDiaryDatabase myDB;
	private Calendar myCalendar = Calendar.getInstance();
	private Button time1, time2, time3, time4, time5;
	private EditText medicineTitle, amount1, amount2, amount3, amount4, amount5;
	private boolean time1Flag, time2Flag, time3Flag, time4Flag, time5Flag;
	private Button medicineCancelBtn, medicineSaveBtn;
	private TimePickerDialog.OnTimeSetListener time;
	private Intent _intent;
	private ArrayList<Medicine> listMedicine;
	private ListView listItemsView;
	private static int NUMBER_ITEM_AUTOCOMPLETE = 50;
	private ArrayList<Medicine> listMedicineAutocomplete = new ArrayList<Medicine>();
	private ArrayList<Medicine> listStart = new ArrayList<Medicine>();
	private AutocompleteSearchAdapter adapter;
	private SMDWebserviceClient dbLoader;
	private AlarmManager alarmManager;
	private PendingIntent sender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.diary_new_medicine);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("THÔNG TIN THUỐC UỐNG");
		initUI();

		 myDB = new MedicationDiaryDatabase(this);
		 Bundle extras = getIntent().getExtras();
		 if (extras != null) {
			 medicine = (DiaryMedicine) extras.getSerializable(MedicationDiaryActivity.MESSAGE);
			 diaryTitle = new DiaryTitle();
			 diaryTitle.setTitleName(medicine.getDiaryTitle());
			 diaryTitle.setStartDate(medicine.getDiaryStart());
			 diaryTitle.setEndDate(medicine.getDiaryEnd());
			 diaryTitle.setTitleId(medicine.getDiaryTitleId());
			 diaryTitle.setMode(medicine.getMode());

			 medicineTitle.setText(medicine.getDiaryMedicineTitle());
			 if (!medicine.getTime1().equals("") && medicine.getTime1() != null)
				 time1.setText(medicine.getTime1());
			 if (!medicine.getTime2().equals("") && medicine.getTime2() != null)
				 time2.setText(medicine.getTime2());
			 if (!medicine.getTime3().equals("") && medicine.getTime3() != null)
				 time3.setText(medicine.getTime3());
			 if (!medicine.getTime4().equals("") && medicine.getTime4() != null)
				 time4.setText(medicine.getTime4());
			 if (!medicine.getTime5().equals("") && medicine.getTime5() != null)
				 time5.setText(medicine.getTime5());
			 amount1.setText(medicine.getAmount1());
			 amount2.setText(medicine.getAmount2());
			 amount3.setText(medicine.getAmount3());
			 amount4.setText(medicine.getAmount4());
			 amount5.setText(medicine.getAmount5());
		 }

		medicineTitle = (EditText) findViewById(R.id.diaryMedicineEdt);
		listItemsView = (ListView) findViewById(R.id.diaryMedicineLv);
		listItemsView.setVisibility(View.GONE);
		if (isOnline()) {
			try {
				dbLoader = new SMDWebserviceClient();
				listMedicine = dbLoader.loadMedicinesDataFromWS("");
			} catch (Exception ex) {

//				ex.printStackTrace();
//				AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryEditMedicineActivity.this);
//				_builder.setTitle("Lỗi kết nối với máy chủ !");
//				_builder.setPositiveButton("Kết nối lại",
//						new DialogInterface.OnClickListener() {
//
//							@Override
//							public void onClick(DialogInterface dialog, int which) {
//								Intent _intent = new Intent(DiaryEditMedicineActivity.this, MainActivity.class);
//								startActivity(_intent);
//							}
//						});
//				_builder.show();
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
						adapter = new AutocompleteSearchAdapter(DiaryEditMedicineActivity.this, android.R.layout.simple_list_item_1, listStart);
						listItemsView.setVisibility(View.GONE);
					} else {
						if (items.equals("") || items == null) {
							adapter = new AutocompleteSearchAdapter(DiaryEditMedicineActivity.this, android.R.layout.simple_list_item_1, listStart);
							listItemsView.setVisibility(View.GONE);
						} else
							adapter = new AutocompleteSearchAdapter(DiaryEditMedicineActivity.this, android.R.layout.simple_list_item_1, listMedicineAutocomplete);
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
				if(medicineTitle == null || medicineTitle.equals("")) {
					AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryEditMedicineActivity.this);
					_builder.setTitle("Lỗi");
					_builder.setMessage("Vui lòng điền thông tin vào 'Tên thuốc'");
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

						AlarmChecker checker = new AlarmChecker();
						Context context = getBaseContext();

						DiaryMedicine diaryRmv = checker.checkAlarmForOneTitle(context, medicine.getDiaryMedicineId());
						if (diaryRmv != null) {
							alarmManager = (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);
						}
						Intent alarmintent = new Intent("com.med.dic.alarmreceiveractivity");
						String value = "" + diaryRmv.getDiaryTitleId() + diaryRmv.getDiaryMedicineId();
						int _id = 0;
						List<String> listRmv = new ArrayList<String>();

						if (!diaryRmv.getTime1().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.getTime1().equals("") && !diaryRmv.getTime1().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							value += 0;
							listRmv.add(value);
						}
						if (!diaryRmv.getTime2().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.getTime2().equals("") && !diaryRmv.getTime2().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							value += 1;
							listRmv.add(value);
						}
						if (!diaryRmv.getTime3().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.getTime3().equals("") && !diaryRmv.getTime3().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							value += 2;
							listRmv.add(value);
						}
						if (!diaryRmv.getTime4().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.getTime4().equals("") && !diaryRmv.getTime4().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							value += 3;
							listRmv.add(value);
						}
						if (!diaryRmv.getTime5().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.getTime5().equals("") && !diaryRmv.getTime5().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							value += 4;
							listRmv.add(value);
						}
						for (int i = 0; i < listRmv.size(); i++) {
							_id = Integer.parseInt(listRmv.get(i));
							sender = PendingIntent.getActivity(getApplicationContext(), _id, alarmintent, 0);
							alarmManager.cancel(sender);
						}

						myDB.open();
						myDB.editDiaryMedicine(medicine.getDiaryMedicineId(), medicine.getDiaryTitleId(), medicineTitle, time1,time2, time3, time4, time5, amount1, amount2,amount3, amount4, amount5);
						myDB.close();

						if (diaryTitle.getMode().equals("MODE_EDIT")) {
							_intent = new Intent(DiaryEditMedicineActivity.this, DiaryEditTitleActivity.class);
							_intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
						} else {
							_intent = new Intent(DiaryEditMedicineActivity.this, DiaryNewTitleActivity.class);
							_intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
						}
						startActivity(_intent);
						finish();
					} else {
						AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryEditMedicineActivity.this);
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
				AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryEditMedicineActivity.this);
				_builder.setTitle("Bạn chắc chắn muốn hủy bỏ chứ ?");
				_builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (diaryTitle.getMode().equals("MODE_EDIT")) {
							_intent = new Intent(DiaryEditMedicineActivity.this, DiaryEditTitleActivity.class);
							_intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
						} else {
							_intent = new Intent(DiaryEditMedicineActivity.this, DiaryNewTitleActivity.class);
							_intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
						}
						startActivity(_intent);
					}
				});
				_builder.setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						//do nothing
					}
				});
				_builder.show();
			}
		});

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
				new TimePickerDialog(DiaryEditMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time1Flag = true;
			}
		});

		time2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryEditMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time2Flag = true;
			}
		});

		time3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryEditMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time3Flag = true;
			}
		});

		time4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryEditMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time4Flag = true;
			}
		});

		time5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				myCalendar = Calendar.getInstance();
				new TimePickerDialog(DiaryEditMedicineActivity.this, time, myCalendar
						.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true).show();
				time5Flag = true;
			}
		});
	}

	/*
	 *
	 */
	private void initUI() {
		medicineTitle = (EditText) findViewById(R.id.diaryMedicineEdt);
		time1 = (Button) findViewById(R.id.medicineTime1Btn);
		time2 = (Button) findViewById(R.id.medicineTime2Btn);
		time3 = (Button) findViewById(R.id.medicineTime3Btn);
		time4 = (Button) findViewById(R.id.medicineTime4Btn);
		time5 = (Button) findViewById(R.id.medicineTime5Btn);
		amount1 = (EditText) findViewById(R.id.medicineAmount1Edt);
		amount2 = (EditText) findViewById(R.id.medicineAmount2Edt);
		amount3 = (EditText) findViewById(R.id.medicineAmount3Edt);
		amount4 = (EditText) findViewById(R.id.medicineAmount4Edt);
		amount5 = (EditText) findViewById(R.id.medicineAmount5Edt);
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

	/*
	 *
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


	/*
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
