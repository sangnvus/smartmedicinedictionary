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
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.med.dic.AlarmChecker;
import com.med.dic.DefaultValueConstant;
import com.med.dic.R;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class DiaryViewMedicineActivity extends Activity {

	private TextView time1Tv, time2Tv, time3Tv, time4Tv, time5Tv, amount1Tv, amount2Tv, amount3Tv, amount4Tv, amount5Tv, titleTv;
	private MedicationDiaryDatabase myDB;
	private DiaryMedicine medicine;
	private DiaryTitle diaryTitle;
	private Button medicineDeleteBtn, medicineEditBtn;
	private int diaryTitleId;
	private AlarmManager alarmManager;
	private PendingIntent sender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.diary_view_medicine);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("THÔNG TIN THUỐC UỐNG");
		initUI();
		myDB = new MedicationDiaryDatabase(this);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			medicine = (DiaryMedicine) extras.getSerializable(MedicationDiaryActivity.MESSAGE);
			if (medicine != null) {
				titleTv.setText(medicine.getDiaryMedicineTitle());
				if (medicine.getTime1().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME) || medicine.getTime1() == null)
					time1Tv.setText(DefaultValueConstant.DIARY_USING_NO_VALUE);
				else
					time1Tv.setText(medicine.getTime1());

				if (medicine.getTime2().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME) || medicine.getTime2() == null)
					time2Tv.setText(DefaultValueConstant.DIARY_USING_NO_VALUE);
				else
					time2Tv.setText(medicine.getTime2());

				if (medicine.getTime3().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME) || medicine.getTime3() == null)
					time3Tv.setText(DefaultValueConstant.DIARY_USING_NO_VALUE);
				else
					time3Tv.setText(medicine.getTime3());

				if (medicine.getTime4().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME) || medicine.getTime4() == null)
					time4Tv.setText(DefaultValueConstant.DIARY_USING_NO_VALUE);
				else
					time4Tv.setText(medicine.getTime4());

				if (medicine.getTime5().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME) || medicine.getTime5() == null)
					time5Tv.setText(DefaultValueConstant.DIARY_USING_NO_VALUE);
				else
					time5Tv.setText(medicine.getTime5());

				amount1Tv.setText(medicine.getAmount1());
				amount2Tv.setText(medicine.getAmount2());
				amount3Tv.setText(medicine.getAmount3());
				amount4Tv.setText(medicine.getAmount4());
				amount5Tv.setText(medicine.getAmount5());
				diaryTitleId = medicine.getDiaryTitleId();

				diaryTitle = new DiaryTitle();
				diaryTitle.setTitleName(medicine.getDiaryTitle());
				diaryTitle.setStartDate(medicine.getDiaryStart());
				diaryTitle.setEndDate(medicine.getDiaryEnd());
				diaryTitle.setTitleId(diaryTitleId);
			}
		}

		medicineDeleteBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder _builderConfirm = new AlertDialog.Builder(DiaryViewMedicineActivity.this);
				_builderConfirm.setTitle("Bạn có chắc chắn muốn xóa không ?");
				_builderConfirm.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
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
						myDB.removeDiaryMedicineWithDiaryTitleId(medicine.getDiaryMedicineId(), medicine.getDiaryTitleId());
						myDB.close();
						Toast.makeText(getBaseContext(), "Xóa dữ liệu thành công!", Toast.LENGTH_SHORT).show();
						Intent _intent = new Intent(DiaryViewMedicineActivity.this, DiaryViewTitleActivity.class);
						_intent.putExtra(MedicationDiaryActivity.MESSAGE, diaryTitle);
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

		medicineEditBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent _intent = new Intent(DiaryViewMedicineActivity.this, DiaryEditMedicineActivity.class);
				_intent.putExtra(MedicationDiaryActivity.MESSAGE, medicine);
				startActivity(_intent);
			}
		});
	}

	/*
	 *
	 */
	private void initUI() {
		titleTv = (TextView) findViewById(R.id.diaryMedicineTv);
		time1Tv = (TextView) findViewById(R.id.medicineTime1Tv);
		time2Tv = (TextView) findViewById(R.id.medicineTime2Tv);
		time3Tv = (TextView) findViewById(R.id.medicineTime3Tv);
		time4Tv = (TextView) findViewById(R.id.medicineTime4Tv);
		time5Tv = (TextView) findViewById(R.id.medicineTime5Tv);
		amount1Tv = (TextView) findViewById(R.id.medicineAmount1Tv);
		amount2Tv = (TextView) findViewById(R.id.medicineAmount2Tv);
		amount3Tv = (TextView) findViewById(R.id.medicineAmount3Tv);
		amount4Tv = (TextView) findViewById(R.id.medicineAmount4Tv);
		amount5Tv = (TextView) findViewById(R.id.medicineAmount5Tv);
		medicineDeleteBtn = (Button) findViewById(R.id.medicineDeleteBtn);
		medicineEditBtn = (Button) findViewById(R.id.medicineEditBtn);
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