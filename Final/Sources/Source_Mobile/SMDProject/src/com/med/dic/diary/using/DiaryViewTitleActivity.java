package com.med.dic.diary.using;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.med.dic.AlarmChecker;
import com.med.dic.DefaultValueConstant;
import com.med.dic.R;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class DiaryViewTitleActivity extends Activity {
	private MedicationDiaryDatabase myDB;
	private DiaryTitle diaryTitle;
	private TextView diaryTitleTv, diaryStartTv, diaryEndTv;
	private Button diaryEditBtn, diaryDeleteBtn;
	private DiaryMedicineAdapter adapter;
	private ArrayList<DiaryMedicine> myList;
	private int diaryId;
	private AlarmManager alarmManager;
	private PendingIntent sender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.diary_view_title);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("THÔNG TIN ĐƠN THUỐC");
		initUI();
		myDB = new MedicationDiaryDatabase(this);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			diaryTitle = (DiaryTitle) extras.getSerializable(MedicationDiaryActivity.MESSAGE);
			if (diaryTitle != null) {
				diaryTitleTv.setText(diaryTitle.getTitleName());
				if (diaryTitle.getStartDate().equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE) || diaryTitle.getStartDate() == null)
					diaryStartTv.setText(DefaultValueConstant.DIARY_USING_NO_VALUE);
				else
					diaryStartTv.setText(diaryTitle.getStartDate());

				if (diaryTitle.getEndDate().equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE) || diaryTitle.getEndDate() == null)
					diaryEndTv.setText(DefaultValueConstant.DIARY_USING_NO_VALUE);
				else
					diaryEndTv.setText(diaryTitle.getEndDate());
				diaryId = diaryTitle.getTitleId();
			}
		}

		myList = new ArrayList<DiaryMedicine>();
		myDB.open();
		Cursor mCursor = myDB.getDiaryMedicineByTitleId(diaryId);
		try {
			if (mCursor.getCount() > 0) {
				for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
					myList.add(new DiaryMedicine(Integer.parseInt(mCursor.getString(0)), Integer.parseInt(mCursor.getString(1)), mCursor.getString(2), mCursor.getString(3), mCursor.getString(4),
							 mCursor.getString(5),  mCursor.getString(6),  mCursor.getString(7),  mCursor.getString(8),  mCursor.getString(9),  mCursor.getString(10),  mCursor.getString(11),
							 mCursor.getString(12), null, null, null));
				}
			}
		} finally {
			mCursor.close();
			myDB.close();
		}
		ListView diaryMedicineLv = (ListView) findViewById(R.id.diaryMedicineLv);
		adapter = new DiaryMedicineAdapter(DiaryViewTitleActivity.this, android.R.layout.simple_list_item_1, myList);
		diaryMedicineLv.setAdapter(adapter);

		diaryMedicineLv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				DiaryMedicine medicine = new DiaryMedicine();
				medicine = adapter.getItem(position);
				medicine.setDiaryTitle(diaryTitle.getTitleName());
				medicine.setDiaryStart(diaryTitle.getStartDate());
				medicine.setDiaryEnd(diaryTitle.getEndDate());
				medicine.setMode("MODE_EDIT");
				Intent _intent = new Intent(DiaryViewTitleActivity.this, DiaryViewMedicineActivity.class);
				_intent.putExtra(MedicationDiaryActivity.MESSAGE, medicine);
				startActivity(_intent);

			}
		});

		diaryEditBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String title = diaryTitleTv.getText().toString();
				String startDate = diaryStartTv.getText().toString();
				String endDate = diaryEndTv.getText().toString();

				diaryTitle.setTitleName(title);
				diaryTitle.setStartDate(startDate);
				diaryTitle.setEndDate(endDate);

				Intent _intent = new Intent(DiaryViewTitleActivity.this, DiaryEditTitleActivity.class);
				_intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
				startActivity(_intent);
			}
		});

		diaryDeleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder _builderConfirm = new AlertDialog.Builder(DiaryViewTitleActivity.this);
				_builderConfirm.setTitle("Bạn có chắc chắn muốn xóa không ?");
				_builderConfirm.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						AlarmChecker checker = new AlarmChecker();
						Context context = getBaseContext();
						List<DiaryMedicine> diaryRmv = checker.checkAlarmForManyTitle(context, diaryId);
						if (diaryRmv != null) {
							alarmManager = (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);
						}
						Intent alarmintent = new Intent("com.med.dic.alarmreceiveractivity");
						String value = "";
						int _id = 0;
						List<String> listRmv = new ArrayList<String>();
						for (int i = 0; i < diaryRmv.size(); i++) {
							value += diaryRmv.get(i).getDiaryTitleId() + diaryRmv.get(i).getDiaryMedicineId();
							if (!diaryRmv.get(i).getTime1().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.get(i).getTime1().equals("") && !diaryRmv.get(i).getTime1().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
								value += 0;
								listRmv.add(value);
							}
							if (!diaryRmv.get(i).getTime2().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.get(i).getTime2().equals("") && !diaryRmv.get(i).getTime2().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
								value += 1;
								listRmv.add(value);
							}
							if (!diaryRmv.get(i).getTime3().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.get(i).getTime3().equals("") && !diaryRmv.get(i).getTime3().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
								value += 2;
								listRmv.add(value);
							}
							if (!diaryRmv.get(i).getTime4().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.get(i).getTime4().equals("") && !diaryRmv.get(i).getTime4().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
								value += 3;
								listRmv.add(value);
							}
							if (!diaryRmv.get(i).getTime5().equals(DefaultValueConstant.DIARY_USING_NO_VALUE) && !diaryRmv.get(i).getTime5().equals("") && !diaryRmv.get(i).getTime5().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
								value += 4;
								listRmv.add(value);
							}
						}
						for (int i = 0; i < listRmv.size(); i++) {
							_id = Integer.parseInt(listRmv.get(i));
							sender = PendingIntent.getActivity(getApplicationContext(), _id, alarmintent, 0);
							alarmManager.cancel(sender);
						}
//						String value = "" + diaryRmv.getDiaryTitleId() + diaryRmv.getDiaryMedicineId();

						myDB.open();
						myDB.removeDiaryTitle(diaryId);
						myDB.removeDiaryMedicineByDiaryTitleId(diaryId);
						myDB.close();
						Toast.makeText(getBaseContext(), "Xóa dữ liệu thành công!", Toast.LENGTH_SHORT).show();
						Intent _intent = new Intent(DiaryViewTitleActivity.this, MedicationDiaryActivity.class);
						startActivity(_intent);
					}
				}).setNegativeButton("Từ chối", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
				_builderConfirm.show();
			}
		});
	}

	/*
	 *
	 */
	private void initUI() {
		diaryTitleTv = (TextView) findViewById(R.id.diaryTitleTv);
		diaryStartTv = (TextView) findViewById(R.id.diaryStartTv);
		diaryEndTv = (TextView) findViewById(R.id.diaryEndTv);
		diaryEditBtn = (Button) findViewById(R.id.diaryEditBtn);
		diaryDeleteBtn = (Button) findViewById(R.id.diaryDeleteBtn);
	}

	/*
	 * go to find location function
	 */
	public void duToFindLocation(View view) {
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/*
	 * go to search medicine function
	 */
	public void duToSearchMedicine(View view) {
		Intent _intent = new Intent(this, SearchMedicineActivity.class);
		startActivity(_intent);
	}

	/*
	 * go to diary using function
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