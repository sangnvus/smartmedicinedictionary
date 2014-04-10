package com.med.dic;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

import com.med.dic.search.medicine.SearchMedicineActivity;

public class MainActivity extends Activity {

	private List<AlarmInfo> dt = new ArrayList<AlarmInfo>();
	private AlarmManager alarmManager;
	private PendingIntent sender;
	private static Context context;
	private SMDWebserviceClient dbLoader;

	public static Context getAppContext(){
	    return MainActivity.context;
	}
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/** Hiding Title bar of this activity screen */
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        /** Making this activity, full screen */
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

//		MainActivity.context = getApplicationContext();
//		AlarmChecker checker = new AlarmChecker();
//		dt = checker.checkAlarm(this);
//		if (dt.size() > 0) {
//			alarmManager = (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);
//		}
//		for (int j = 0; j < dt.size(); j++) {
//			GregorianCalendar calendar1 = new GregorianCalendar(dt.get(j).getYear(), dt.get(j).getMonth() - 1, dt.get(j).getDay(), dt.get(j).getHour(), dt.get(j).getMinute());
//			Intent alarmintent = new Intent("com.med.dic.alarmreceiveractivity");
//			alarmintent.putExtra("Title", dt.get(j).getTitle());
//			alarmintent.putExtra("Note", dt.get(j).getMedicine() + "\n" + dt.get(j).getAmount());
//
////			int _id = (int) System.currentTimeMillis();
//			String value = "" + dt.get(j).getTitleId() + dt.get(j).getMedicineId();
//			int _id = Integer.parseInt(value);
//
//			sender = PendingIntent.getActivity(getBaseContext(), _id, alarmintent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//			long cal = calendar1.getTimeInMillis();
//
//			/** Setting an alarm, which invokes the operation at alart_time */
//			alarmManager.set(AlarmManager.RTC_WAKEUP, cal, sender);
//		}

		 /** Creates a count down timer, which will be expired after 5000 milliseconds */
        new CountDownTimer(2000,1000) {

        	/** This method will be invoked on finishing or expiring the timer */
			@Override
			public void onFinish() {

				if (isOnline()) {
					try {
						dbLoader = new SMDWebserviceClient();
						String test = dbLoader.test();
						if (!test.equals("Connected")) {
							throw new Exception();
						}

						/** Creates an intent to start new activity */
						Intent intent = new Intent(getBaseContext(), SearchMedicineActivity.class);

						/** Creates a new activity, on finishing this timer */
						startActivity(intent);

						/** Close this activity screen */
						finish();
					} catch (Exception ex) {
						ex.printStackTrace();
						showAlertServerConnection();
					}
				} else {
					showAlertInternetConnection();
				}

			}

			/** This method will be invoked in every 1000 milli seconds until
			* this timer is expired.Because we specified 1000 as tick time
			* while creating this CountDownTimer
			*/
			@Override
			public void onTick(long millisUntilFinished) {

			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		AlertDialog.Builder _builder = new AlertDialog.Builder(MainActivity.this);
		_builder.setTitle("Lỗi kết nối với máy chủ !");
		_builder.setPositiveButton("Kết nối lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = getIntent();
						finish();
						startActivity(intent);
					}
				});
		_builder.setNegativeButton("Thoát ra",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
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
		AlertDialog.Builder _builder = new AlertDialog.Builder(MainActivity.this);
		_builder.setTitle("Lỗi kết nối mạng !");
		_builder.setPositiveButton("Kết nối lại",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = getIntent();
						finish();
						startActivity(intent);
					}
				});
		_builder.setNegativeButton("Thoát ra",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						finish();
					}
				});
		AlertDialog alert = _builder.create();
		alert.setCanceledOnTouchOutside(false);
		alert.setCancelable(false);
		alert.show();
	}
}
