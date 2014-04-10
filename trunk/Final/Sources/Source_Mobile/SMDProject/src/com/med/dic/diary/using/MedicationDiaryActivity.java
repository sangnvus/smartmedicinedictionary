package com.med.dic.diary.using;

import java.util.ArrayList;

import android.app.Activity;
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

import com.med.dic.R;
import com.med.dic.about.us.AboutUsActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class MedicationDiaryActivity extends Activity {

	public static String MESSAGE = "com.med.dic.diary.using.MedicationDiaryActivity";
	private DiaryViewAdapter adapter;
	private ArrayList<DiaryTitle> myList = new ArrayList<DiaryTitle>();
	private MedicationDiaryDatabase myDB;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.diary_using);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("NHẬT KÝ UỐNG THUỐC");

		listView = (ListView) findViewById(R.id.diaryUsingLv);
		myDB = new MedicationDiaryDatabase(this);

		myDB.open();
		Cursor mCursor = myDB.getAllDiaryTitle();
		try {
			if (mCursor.getCount() > 0) {
				for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()){
					myList.add(new DiaryTitle(Integer.parseInt(mCursor.getString(0)), mCursor.getString(1), mCursor.getString(2), mCursor.getString(3)));
				}
			}
		} finally {
			mCursor.close();
			myDB.close();
		}

		adapter = new DiaryViewAdapter(MedicationDiaryActivity.this, android.R.layout.simple_list_item_1, myList);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				DiaryTitle diary = new DiaryTitle();
				diary = adapter.getItem(position);
				Intent _intent = new Intent(MedicationDiaryActivity.this, DiaryViewTitleActivity.class);
				_intent.putExtra(MESSAGE, diary);
				startActivity(_intent);
			}
		});

		//kill children have no parent
		myDB.open();
		Cursor cursorCheck = myDB.getAllDiaryMedicine();
		try {
			if (cursorCheck.getCount() > 0) {
				for(cursorCheck.moveToFirst(); !cursorCheck.isAfterLast(); cursorCheck.moveToNext()){
					Cursor cursorRemove = myDB.getDiaryTitleById(Integer.parseInt(cursorCheck.getString(1)));
					if (cursorRemove.getCount() <= 0) {
						myDB.removeDiaryMedicine(Integer.parseInt(cursorCheck.getString(1)));
					}
				}
			}
		} finally {
			cursorCheck.close();
			myDB.close();
		}


		Button addDiary = (Button) findViewById(R.id.addNewDiaryBtn);
		addDiary.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent _intent = new Intent(MedicationDiaryActivity.this, DiaryNewTitleActivity.class);
				startActivity(_intent);
			}
		});
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
	 *
	 */
	public void duOnClick(View view) {

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
