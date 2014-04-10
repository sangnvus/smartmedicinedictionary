package com.med.dic.diary.using;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MedicationDiaryDatabase {

	// Database name
	private static final String DATABASE_NAME = "DB_SMD";

	// Version database
	private static final int DATABASE_VERSION = 2;

	// Table name and column name in DB
	private static final String TABLE_DIARY_USING = "DIARY_USING";
	public static final String COLUMN_DIARY_ID = "DIARY_ID";
	public static final String COLUMN_DIARY_TITLE = "DIARY_TITLE";
	public static final String COLUMN_DATE_START = "DATE_START";
	public static final String COLUMN_DATE_END = "DATE_END";

	private static final String TABLE_DIARY_MEDICINE = "DIARY_MEDICINE";
	public static final String COLUMN_DIARY_MEDICINE_ID = "DIARY_MEDICINE_ID";
	public static final String COLUMN_DIARY_TITLE_ID = "DIARY_TITLE_ID";
	public static final String COLUMN_DIARY_MEDICINE_TITLE = "DIARY_MEDICINE_TITLE";
	public static final String COLUMN_DIARY_MEDCICINE_TIME_1 = "TIME_1";
	public static final String COLUMN_DIARY_MEDCICINE_TIME_2 = "TIME_2";
	public static final String COLUMN_DIARY_MEDCICINE_TIME_3 = "TIME_3";
	public static final String COLUMN_DIARY_MEDCICINE_TIME_4 = "TIME_4";
	public static final String COLUMN_DIARY_MEDCICINE_TIME_5 = "TIME_5";
	public static final String COLUMN_DIARY_MEDCICINE_AMOUNT_1 = "AMOUNT_1";
	public static final String COLUMN_DIARY_MEDCICINE_AMOUNT_2 = "AMOUNT_2";
	public static final String COLUMN_DIARY_MEDCICINE_AMOUNT_3 = "AMOUNT_3";
	public static final String COLUMN_DIARY_MEDCICINE_AMOUNT_4 = "AMOUNT_4";
	public static final String COLUMN_DIARY_MEDCICINE_AMOUNT_5 = "AMOUNT_5";

	private static Context context;
	static SQLiteDatabase db;
	private OpenHelper openHelper;

	/* Constructor */
	public MedicationDiaryDatabase(Context context) {
		MedicationDiaryDatabase.context = context;
	}

	/* Connect DB */
	public MedicationDiaryDatabase open() throws SQLException {
		openHelper = new OpenHelper(context);
		db = openHelper.getWritableDatabase();
		return this;
	}

	/* Close connect DB */
	public void close() {
		openHelper.close();
	}

	/* Insert data to DB */
	public long insertDiaryTitle(int id, String title, String startDate, String endDate) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_DIARY_ID, id);
		cv.put(COLUMN_DIARY_TITLE, title);
		cv.put(COLUMN_DATE_START, startDate);
		cv.put(COLUMN_DATE_END, endDate);
		return db.insert(TABLE_DIARY_USING, null, cv);
	}

	/* Insert data to DB */
	public long insertDiaryMedicine(int id, int diaryId, String title, String time1, String time2, String time3, String time4, String time5,
			String amount1, String amount2, String amount3, String amount4, String amount5) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_DIARY_MEDICINE_ID, id);
		cv.put(COLUMN_DIARY_TITLE_ID, diaryId);
		cv.put(COLUMN_DIARY_MEDICINE_TITLE, title);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_1, time1);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_2, time2);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_3, time3);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_4, time4);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_5, time5);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_1, amount1);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_2, amount2);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_3, amount3);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_4, amount4);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_5, amount5);
		return db.insert(TABLE_DIARY_MEDICINE, null, cv);
	}

	/* Update data from DB */
	public boolean editDiaryTitle(int id, String title, String startDate, String endDate) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_DIARY_ID, id);
		cv.put(COLUMN_DIARY_TITLE, title);
		cv.put(COLUMN_DATE_START, startDate);
		cv.put(COLUMN_DATE_END, endDate);
		return db.update(TABLE_DIARY_USING, cv, COLUMN_DIARY_ID + "=" + id,
				null) > 0;
	}

	/* Update data from DB */
	public boolean editDiaryMedicine(int medicineId, int diaryId, String title, String time1, String time2, String time3, String time4, String time5,
			String amount1, String amount2, String amount3, String amount4, String amount5) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_DIARY_MEDICINE_ID, medicineId);
		cv.put(COLUMN_DIARY_TITLE_ID, diaryId);
		cv.put(COLUMN_DIARY_MEDICINE_TITLE, title);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_1, time1);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_2, time2);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_3, time3);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_4, time4);
		cv.put(COLUMN_DIARY_MEDCICINE_TIME_5, time5);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_1, amount1);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_2, amount2);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_3, amount3);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_4, amount4);
		cv.put(COLUMN_DIARY_MEDCICINE_AMOUNT_5, amount5);
		return db.update(TABLE_DIARY_MEDICINE, cv, COLUMN_DIARY_MEDICINE_ID + "=" + medicineId,
				null) > 0;
	}

	/* Remove data from DB */
	public boolean removeDiaryTitle(int id) {
		return db.delete(TABLE_DIARY_USING, COLUMN_DIARY_ID + "=" + id, null) > 0;
	}

	public boolean removeDiaryMedicine(int id) {
		return db.delete(TABLE_DIARY_MEDICINE, COLUMN_DIARY_MEDICINE_ID + "=" + id, null) > 0;
	}

	public boolean removeDiaryMedicineWithDiaryTitleId(int medicineId, int diaryId) {
		return db.delete(TABLE_DIARY_MEDICINE, COLUMN_DIARY_MEDICINE_ID + "=" + medicineId + " AND " + COLUMN_DIARY_TITLE_ID + "=" + diaryId, null) > 0;
	}

	public boolean removeDiaryMedicineByDiaryTitleId(int diaryTitleId) {
		return db.delete(TABLE_DIARY_MEDICINE, COLUMN_DIARY_TITLE_ID + "=" + diaryTitleId, null) > 0;
	}

	/* Return data of table DIARY_USING of DB follow String format */
	public Cursor getAllDiaryTitle() {
		String[] columns = new String[] { COLUMN_DIARY_ID, COLUMN_DIARY_TITLE,
				COLUMN_DATE_START, COLUMN_DATE_END };
		Cursor c = db.query(TABLE_DIARY_USING, columns, null, null, null, null, COLUMN_DIARY_ID + " DESC");
		if (c == null)
			Log.v("Cursor", "C is NULL");
		return c;
	}

	public Cursor getDiaryTitleById(int id) {
		String[] columns = new String[] { COLUMN_DIARY_ID, COLUMN_DIARY_TITLE,
				COLUMN_DATE_START, COLUMN_DATE_END };
		Cursor c = db.query(TABLE_DIARY_USING, columns, COLUMN_DIARY_ID + " = " + id, null, null, null, COLUMN_DIARY_ID);
		if (c == null)
			Log.v("Cursor", "C is NULL");
		return c;
	}

	public Cursor getDiaryMedicineByTitleId(int diaryTilteId) {
	String[] columns = new String[] { COLUMN_DIARY_MEDICINE_ID, COLUMN_DIARY_TITLE_ID, COLUMN_DIARY_MEDICINE_TITLE,
			COLUMN_DIARY_MEDCICINE_TIME_1, COLUMN_DIARY_MEDCICINE_TIME_2, COLUMN_DIARY_MEDCICINE_TIME_3,
			COLUMN_DIARY_MEDCICINE_TIME_4, COLUMN_DIARY_MEDCICINE_TIME_5, COLUMN_DIARY_MEDCICINE_AMOUNT_1,
			COLUMN_DIARY_MEDCICINE_AMOUNT_2, COLUMN_DIARY_MEDCICINE_AMOUNT_3, COLUMN_DIARY_MEDCICINE_AMOUNT_4, COLUMN_DIARY_MEDCICINE_AMOUNT_5};
	Cursor c = db.query(TABLE_DIARY_MEDICINE, columns, COLUMN_DIARY_TITLE_ID + " = " + diaryTilteId, null, null, null, COLUMN_DIARY_MEDICINE_ID + " DESC");
	if (c == null)
		Log.v("Cursor", "C is NULL");
	return c;
	}

	public Cursor getDiaryMedicineByMedId(int diaryMedicineId) {
		String[] columns = new String[] { COLUMN_DIARY_MEDICINE_ID, COLUMN_DIARY_TITLE_ID, COLUMN_DIARY_MEDICINE_TITLE,
				COLUMN_DIARY_MEDCICINE_TIME_1, COLUMN_DIARY_MEDCICINE_TIME_2, COLUMN_DIARY_MEDCICINE_TIME_3,
				COLUMN_DIARY_MEDCICINE_TIME_4, COLUMN_DIARY_MEDCICINE_TIME_5, COLUMN_DIARY_MEDCICINE_AMOUNT_1,
				COLUMN_DIARY_MEDCICINE_AMOUNT_2, COLUMN_DIARY_MEDCICINE_AMOUNT_3, COLUMN_DIARY_MEDCICINE_AMOUNT_4, COLUMN_DIARY_MEDCICINE_AMOUNT_5};
		Cursor c = db.query(TABLE_DIARY_MEDICINE, columns, COLUMN_DIARY_MEDICINE_ID + " = " + diaryMedicineId, null, null, null, null);
		if (c == null)
			Log.v("Cursor", "C is NULL");
		return c;
		}

	public Cursor getAllDiaryMedicine() {
		String[] columns = new String[] { COLUMN_DIARY_MEDICINE_ID, COLUMN_DIARY_TITLE_ID, COLUMN_DIARY_MEDICINE_TITLE,
				COLUMN_DIARY_MEDCICINE_TIME_1, COLUMN_DIARY_MEDCICINE_TIME_2, COLUMN_DIARY_MEDCICINE_TIME_3,
				COLUMN_DIARY_MEDCICINE_TIME_4, COLUMN_DIARY_MEDCICINE_TIME_5, COLUMN_DIARY_MEDCICINE_AMOUNT_1,
				COLUMN_DIARY_MEDCICINE_AMOUNT_2, COLUMN_DIARY_MEDCICINE_AMOUNT_3, COLUMN_DIARY_MEDCICINE_AMOUNT_4, COLUMN_DIARY_MEDCICINE_AMOUNT_5};
		Cursor c = db.query(TABLE_DIARY_MEDICINE, columns, null, null, null, null, COLUMN_DIARY_MEDICINE_ID + " DESC");
		if (c == null)
			Log.v("Cursor", "C is NULL");
		return c;
		}
	// ---------------- class OpenHelper ------------------
	private static class OpenHelper extends SQLiteOpenHelper {

		/* Constructor */
		public OpenHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}

		/* Create DB */
		@Override
		public void onCreate(SQLiteDatabase arg0) {
			try {
				arg0.execSQL("CREATE TABLE " + TABLE_DIARY_USING + " ("
						+ COLUMN_DIARY_ID
						+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ COLUMN_DIARY_TITLE + " TEXT NOT NULL, "
						+ COLUMN_DATE_START + " TEXT NOT NULL, "
						+ COLUMN_DATE_END + " TEXT NOT NULL);");
				arg0.execSQL("CREATE TABLE " + TABLE_DIARY_MEDICINE + " ("
						+ COLUMN_DIARY_MEDICINE_ID
						+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
						+ COLUMN_DIARY_TITLE_ID + " TEXT NOT NULL, "
						+ COLUMN_DIARY_MEDICINE_TITLE + " TEXT NOT NULL, "
						+ COLUMN_DIARY_MEDCICINE_TIME_1 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_TIME_2 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_TIME_3 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_TIME_4 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_TIME_5 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_AMOUNT_1 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_AMOUNT_2 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_AMOUNT_3 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_AMOUNT_4 + " TEXT, "
						+ COLUMN_DIARY_MEDCICINE_AMOUNT_5 + " TEXT);");
			} catch (SQLException e) {
				Log.v("Create DB", e.toString());
			}
		}

		/* Check version of DB to change appropriately */
		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY_USING);
			onCreate(arg0);
		}
	}
}
