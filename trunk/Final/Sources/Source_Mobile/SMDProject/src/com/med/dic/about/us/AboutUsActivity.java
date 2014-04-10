package com.med.dic.about.us;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.med.dic.R;
import com.med.dic.diary.using.MedicationDiaryActivity;
import com.med.dic.find.pharmarcy.FindPharmarcyActivity;
import com.med.dic.search.medicine.SearchMedicineActivity;

public class AboutUsActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.about_us);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
		TextView tv = (TextView) findViewById(R.id.txtView);
		tv.setText("GIỚI THIỆU");

		TextView contentTv = (TextView) findViewById(R.id.auContent);
		TextView groupTv = (TextView) findViewById(R.id.auGroup);
		String content = "  Từ điển thuốc thông minh là một ứng dụng được xây dựng dựa trên ý " +
						"tưởng về một cuốn từ điển y dược cho phép người dùng có thể " +
						"tra cứu thông tin về thuốc một cách nhanh chóng và thuận tiện thông " +
						"qua internet, không những vậy thông qua website người dùng còn có " +
						"thể tìm kiếm thông tin về các cửa hàng thuốc có bán loại thuốc mà người dùng " +
						"quan tâm.";
		String group = " Phần mềm được xây dựng bởi nhóm đồ án tốt nghiệp khóa 5B trường đại học FPT";
		contentTv.setText(content);
		groupTv.setText(group);
	}


	/*
	 * go to search medicine function
	 */
	public void auToSearchMedicine(View view) {
		Intent _intent = new Intent(this, SearchMedicineActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to find location function
	 */
	public void auToFindLocation(View view) {
		Intent _intent = new Intent(this, FindPharmarcyActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to historical using function
	 */
	public void auToDiaryUsing(View view) {
		Intent _intent = new Intent(this, MedicationDiaryActivity.class);
		startActivity(_intent);
	}

	/**
	 * go to historical using function
	 */
	public void auOnClick(View view) {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
