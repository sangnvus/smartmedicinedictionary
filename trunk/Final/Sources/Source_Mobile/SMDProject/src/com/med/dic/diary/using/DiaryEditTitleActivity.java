package com.med.dic.diary.using;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.med.dic.AlarmChecker;
import com.med.dic.AlarmInfo;
import com.med.dic.DefaultValueConstant;
import com.med.dic.R;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class DiaryEditTitleActivity extends Activity {

	private MedicationDiaryDatabase myDB;
	private DiaryTitle diaryTitle;
	private EditText diaryTitleEdt;
	private boolean dateStartFlag, dateEndFlag;
	private ArrayList<DiaryMedicine> myList;
	private Calendar myCalendar = Calendar.getInstance();
	private DatePickerDialog.OnDateSetListener date;
	private DiaryMedicineAdapter adapter;
	private Button diarySaveBtn, diaryCancelBtn, diaryAddMedicineBtn, diaryStartBtn, diaryEndBtn;
	private List<AlarmInfo> dt = new ArrayList<AlarmInfo>();
	private AlarmManager alarmManager;
	private PendingIntent sender;
	private int REPEAT_TIME = 1000*60*1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.diary_new_title);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("THÔNG TIN ĐƠN THUỐC");
		initUI();
		myDB = new MedicationDiaryDatabase(this);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			diaryTitle = (DiaryTitle) extras.getSerializable(MedicationDiaryActivity.MESSAGE);
			diaryTitleEdt.setText(diaryTitle.getTitleName());
			if (!diaryTitle.getStartDate().equals("") && diaryTitle.getStartDate() != null && !diaryTitle.getStartDate().equals(DefaultValueConstant.DIARY_USING_NO_VALUE))
				diaryStartBtn.setText(diaryTitle.getStartDate());
			if (!diaryTitle.getEndDate().equals("") && diaryTitle.getEndDate() != null && !diaryTitle.getEndDate().equals(DefaultValueConstant.DIARY_USING_NO_VALUE))
				diaryEndBtn.setText(diaryTitle.getEndDate());
		}

		date = new OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {

				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
			}
		};

		diaryStartBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(DiaryEditTitleActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
				dateStartFlag = true;
			}
		});

		diaryEndBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				new DatePickerDialog(DiaryEditTitleActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH)).show();
				dateEndFlag = true;
			}
		});

		myList = new ArrayList<DiaryMedicine>();
		myDB.open();
		Cursor mCursor = myDB.getDiaryMedicineByTitleId(diaryTitle.getTitleId());
		try {
			if (mCursor.getCount() > 0) {
				for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
					myList.add(new DiaryMedicine(Integer.parseInt(mCursor.getString(0)), Integer.parseInt(mCursor.getString(1)), mCursor.getString(2), mCursor.getString(3), mCursor.getString(4),
							 mCursor.getString(5),  mCursor.getString(6),  mCursor.getString(7),  mCursor.getString(8),  mCursor.getString(9),  mCursor.getString(10),  mCursor.getString(11),
							 mCursor.getString(12), diaryTitle.getTitleName(), diaryTitle.getStartDate(), diaryTitle.getEndDate()));
				}
			}
		} finally {
			mCursor.close();
			myDB.close();
		}
		ListView diaryMedicineLv = (ListView) findViewById(R.id.diaryMedicineLv);
		adapter = new DiaryMedicineAdapter(DiaryEditTitleActivity.this, android.R.layout.simple_list_item_1, myList);
		diaryMedicineLv.setAdapter(adapter);

		diaryMedicineLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				DiaryMedicine medicine = new DiaryMedicine();
				String title = ((EditText) findViewById(R.id.diaryTitleEdt)).getText().toString();
				String diaryStart = ((Button) findViewById(R.id.diaryStartBtn)).getText().toString();
				String diaryEnd = ((Button) findViewById(R.id.diaryEndBtn)).getText().toString();
				medicine = adapter.getItem(position);
				medicine.setDiaryTitle(title);
				medicine.setDiaryStart(diaryStart);
				medicine.setDiaryEnd(diaryEnd);
				medicine.setMode("MODE_EDIT");
				Intent _intent = new Intent(DiaryEditTitleActivity.this, DiaryViewMedicineActivity.class);
				_intent.putExtra(MedicationDiaryActivity.MESSAGE, medicine);
				startActivity(_intent);

			}
		});

		diarySaveBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String title = ((EditText) findViewById(R.id.diaryTitleEdt)).getText().toString();
				String diaryStart = ((Button) findViewById(R.id.diaryStartBtn)).getText().toString();
				String diaryEnd = ((Button) findViewById(R.id.diaryEndBtn)).getText().toString();

				if(title == null || title.equals("")) {
					AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryEditTitleActivity.this);
					_builder.setTitle("Lỗi");
					_builder.setMessage("Vui lòng điền thông tin vào 'Tiêu đề'");
					_builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
					_builder.show();
				} else {
					if (compareDate(diaryStart, diaryEnd)) {
						if ((!diaryStart.equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE) && diaryEnd.equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE)) ||
								(diaryStart.equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE) && !diaryEnd.equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE))) {
							AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryEditTitleActivity.this);
							_builder.setTitle("Lỗi");
							_builder.setMessage("Bạn phải chọn cả ngày bắt đầu và ngày kết thúc");
							_builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									// TODO Auto-generated method stub

								}
							});
							_builder.show();
						} else {
							myDB.open();
							if (diaryTitle != null)
								myDB.editDiaryTitle(diaryTitle.getTitleId(), title, diaryStart, diaryEnd);
							myDB.close();

							// Set alarm
							AlarmChecker checker = new AlarmChecker();
							Context context = getBaseContext();
							dt = checker.checkAlarm(context);
							if (dt.size() > 0) {
								alarmManager = (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);
							}

							for (int j = 0; j < dt.size(); j++) {
								GregorianCalendar calendar1 = new GregorianCalendar(dt.get(j).getYear(), dt.get(j).getMonth() - 1, dt.get(j).getDay(), dt.get(j).getHour(), dt.get(j).getMinute());
								String _putTitle = dt.get(j).getTitle();
								String _putNotes = "Thuốc: " + dt.get(j).getMedicine() + "\n" + "Liều lượng: " + dt.get(j).getAmount();
								Intent alarmintent = new Intent("com.med.dic.alarmreceiveractivity");
								alarmintent.putExtra("Title", _putTitle);
								alarmintent.putExtra("Note", _putNotes);
								String value = "" + dt.get(j).getTitleId() + dt.get(j).getMedicineId() + j;
								int _id = Integer.parseInt(value);
								alarmintent.putExtra("Id", _id);
								sender = PendingIntent.getActivity(getApplicationContext(), _id, alarmintent, PendingIntent.FLAG_UPDATE_CURRENT);

								long cal = calendar1.getTimeInMillis();

								/** Setting an alarm, which invokes the operation at alart_time */
								alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal, REPEAT_TIME, sender);
							}

							Intent _intent = new Intent(DiaryEditTitleActivity.this, MedicationDiaryActivity.class);
							startActivity(_intent);
							finish();
						}
					} else {
						AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryEditTitleActivity.this);
						_builder.setTitle("Lỗi");
						_builder.setMessage("Ngày bắt đầu không được sau ngày kết thúc");
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

		diaryCancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder _builder = new AlertDialog.Builder(DiaryEditTitleActivity.this);
				_builder.setTitle("Bạn chắc chắn muốn hủy bỏ chứ ?");
				_builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {

						if (diaryTitle != null && diaryTitle.getTitleId() > 0) {
							myDB.open();
							Cursor mCursor = myDB.getDiaryTitleById(diaryTitle.getTitleId());
							try {
								if (mCursor.getCount() == 0) {
									myDB.removeDiaryMedicineByDiaryTitleId(diaryTitle.getTitleId());
									myDB.removeDiaryTitle(diaryTitle.getTitleId());
								}
							} finally {
								mCursor.close();
								myDB.close();
							}
						}

						Intent _intent = new Intent(DiaryEditTitleActivity.this, MedicationDiaryActivity.class);
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

		diaryAddMedicineBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String title = diaryTitleEdt.getText().toString();
				String startDate = diaryStartBtn.getText().toString();
				String endDate = diaryEndBtn.getText().toString();
				int id = diaryTitle.getTitleId();
				diaryTitle = new DiaryTitle(id, title, startDate, endDate);
				diaryTitle.setMode("MODE_EDIT");
				Intent _intent = new Intent(DiaryEditTitleActivity.this, DiaryNewMedicineActivity.class);
				_intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
				startActivity(_intent);

			}
		});
	}

	/**
	 * get view
	 */
	private void initUI() {
		diaryTitleEdt = (EditText) findViewById(R.id.diaryTitleEdt);
		diaryStartBtn = (Button) findViewById(R.id.diaryStartBtn);
		diaryEndBtn = (Button) findViewById(R.id.diaryEndBtn);
		diarySaveBtn = (Button) findViewById(R.id.diarySaveBtn);
		diaryCancelBtn = (Button) findViewById(R.id.diaryCancelBtn);
		diaryAddMedicineBtn = (Button) findViewById(R.id.diaryTitleAddMedicineBtn);
	}

	/**
	 * convert date picker to text
	 */
	private void updateLabel() {

		String myFormat = "dd/MM/yyyy"; // In which you need put here
		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

		if (dateStartFlag) {
			diaryStartBtn = (Button) findViewById(R.id.diaryStartBtn);
			diaryStartBtn.setText(sdf.format(myCalendar.getTime()));
			dateStartFlag = false;
		}

		if (dateEndFlag) {
			diaryEndBtn = (Button) findViewById(R.id.diaryEndBtn);
			diaryEndBtn.setText(sdf.format(myCalendar.getTime()));
			dateEndFlag = false;
		}
	}

	/**
	 * Compare date
	 */
	@SuppressLint("SimpleDateFormat")
	private boolean compareDate(String dateStart, String dateEnd) {
		if (!dateStart.equals("") && !dateStart.equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE) && !dateEnd.equals("") && !dateEnd.equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE)) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        	Date date1;
        	Date date2;
			try {
				date1 = sdf.parse(dateStart);
				date2 = sdf.parse(dateEnd);
				if (date1.after(date2)) {
	        		return false;
	        	}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		return true;
	}

	/**
	 * go to find location function
	 */
	public void duToFindLocation(View view) {
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to seaerch medicine function
	 */
	public void duToSearchMedicine(View view) {
		Intent _intent = new Intent(this, SearchMedicineActivity.class);
		startActivity(_intent);
	}

	/**
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
