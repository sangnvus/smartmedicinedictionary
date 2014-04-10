package com.med.dic;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.WindowManager.LayoutParams;

public class AlarmReceiverActivity extends FragmentActivity {
	private MediaPlayer mAlarmPlayer;
	private String title;
	private String note;
	private int id;
	private AlarmManager alarmManager;
	private PendingIntent sender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			title = extras.getString("Title");
			note = extras.getString("Note");
			id = extras.getInt("Id");
			mAlarmPlayer = MediaPlayer.create(this, R.raw.alarm);
			mAlarmPlayer.setLooping(true);
			mAlarmPlayer.start();

			this.getWindow().addFlags(LayoutParams.FLAG_TURN_SCREEN_ON | LayoutParams.FLAG_DISMISS_KEYGUARD);
			AlertDialog.Builder _builder = new AlertDialog.Builder(AlarmReceiverActivity.this);
			_builder.setTitle(title);
			_builder.setMessage(note);
			_builder.setPositiveButton("Nhắc lại(5')",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							mAlarmPlayer.pause();
							dialog.dismiss();
							finish();
						}
					});
			_builder.setNegativeButton("Bỏ qua",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							mAlarmPlayer.pause();
							alarmManager = (AlarmManager) getBaseContext().getSystemService(ALARM_SERVICE);
							Intent alarmintent = new Intent("com.med.dic.alarmreceiveractivity");
							sender = PendingIntent.getActivity(getApplicationContext(), id, alarmintent, PendingIntent.FLAG_UPDATE_CURRENT);
							alarmManager.cancel(sender);
							finish();
						}
					});
			AlertDialog alert = _builder.create();
			alert.setCanceledOnTouchOutside(false);
			alert.setCancelable(false);
			alert.show();
		}
	}

//	@Override
//	protected void onDestroy() {
//		alarmManager.cancel(sender);
//		mAlarmPlayer.pause();
//		super.onDestroy();
//	}


//	@Override
//	protected void onPause() {
//		alarmManager.cancel(sender);
//		mAlarmPlayer.pause();
//		super.onPause();
//	}
}
