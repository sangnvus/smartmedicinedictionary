package com.med.dic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import com.med.dic.diary.using.DiaryMedicine;
import com.med.dic.diary.using.DiaryTitle;
import com.med.dic.diary.using.MedicationDiaryDatabase;

public class AlarmChecker {
	private MedicationDiaryDatabase myDB;
	private List<DiaryTitle> myTitleList = new ArrayList<DiaryTitle>();
	private List<DiaryMedicine> myMedicineList = new ArrayList<DiaryMedicine>();
	private List<AlarmInfo> dt = new ArrayList<AlarmInfo>();
	private SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sdfTime = new SimpleDateFormat("dd/MM/yyyy HH:mm");

	public AlarmChecker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<AlarmInfo> checkAlarm(Context context) {
		myDB = new MedicationDiaryDatabase(context);
		myDB.open();
		Cursor titleCursor = myDB.getAllDiaryTitle();
		try {
			if (titleCursor.getCount() > 0) {
				for(titleCursor.moveToFirst(); !titleCursor.isAfterLast(); titleCursor.moveToNext()) {
					myTitleList.add(new DiaryTitle(Integer.parseInt(titleCursor.getString(0)), titleCursor.getString(1), titleCursor.getString(2), titleCursor.getString(3)));
				}
			}
		} finally {
			titleCursor.close();
		}
		if (myTitleList.size() > 0) {
			for (int i = 0; i < myTitleList.size(); i++) {
				String dateStart = myTitleList.get(i).getStartDate();
				String dateEnd = myTitleList.get(i).getEndDate();

				if(dateStart.equals("") || dateStart == null || dateEnd.equals("") || dateEnd == null) {
					myTitleList.remove(i);
				} else {
					try {
						Date start = sdfDate.parse(dateStart);
						Date end = sdfDate.parse(dateEnd);
						Date currentDate = formatCurrentDate();
						if (currentDate.before(start) || currentDate.after(end) || !(start.equals(currentDate) || end.equals(currentDate))) {
							myTitleList.remove(i);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

			for (int i = 0; i < myTitleList.size(); i++) {
				Cursor medicineCursor = myDB.getDiaryMedicineByTitleId(myTitleList.get(i).getTitleId());
				try {
					if (medicineCursor.getCount() > 0) {
						for(medicineCursor.moveToFirst(); !medicineCursor.isAfterLast(); medicineCursor.moveToNext()){
							myMedicineList.add(new DiaryMedicine(Integer.parseInt(medicineCursor.getString(0)), Integer.parseInt(medicineCursor.getString(1)), medicineCursor.getString(2), medicineCursor.getString(3), medicineCursor.getString(4),
									 medicineCursor.getString(5),  medicineCursor.getString(6),  medicineCursor.getString(7),  medicineCursor.getString(8),  medicineCursor.getString(9),  medicineCursor.getString(10),  medicineCursor.getString(11),
									 medicineCursor.getString(12), myTitleList.get(i).getTitleName(), myTitleList.get(i).getStartDate(), myTitleList.get(i).getEndDate()));
						}
					}
				} finally {
					medicineCursor.close();
				}
			}

			for (int i = 0; i < myMedicineList.size(); i++) {
				if (!myMedicineList.get(i).getDiaryStart().equals(DefaultValueConstant.DIARY_USING_DEFAULT_DATE)) {
//					String[] date = myMedicineList.get(i).getDiaryStart().split("/");
//					int day = Integer.parseInt(date[0]);
//					int month = Integer.parseInt(date[1]);
//					int year = Integer.parseInt(date[2]);
					try {
						if (!myMedicineList.get(i).getTime1().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							Date time1 = sdfTime.parse(myMedicineList.get(i).getDiaryStart() + " " + myMedicineList.get(i).getTime1());
							Date currentDate = new Date();
							if (!time1.before(currentDate)) {
								String[] splitDate = splitDate(myMedicineList.get(i).getDiaryStart());
								dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time1.getHours(), time1.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
										myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime1(), myMedicineList.get(i).getAmount1(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
							} else {
								time1 = sdfTime.parse(myMedicineList.get(i).getDiaryEnd() + " " + myMedicineList.get(i).getTime1());
								if (!time1.before(currentDate)) {
									String[] splitDate = splitDate(myMedicineList.get(i).getDiaryEnd());
									dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time1.getHours(), time1.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
											myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime1(), myMedicineList.get(i).getAmount1(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
								}
							}
						}
						if (!myMedicineList.get(i).getTime2().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							Date time2 = sdfTime.parse(myMedicineList.get(i).getDiaryStart() + " " + myMedicineList.get(i).getTime2());
							Date currentDate = new Date();
							if (!time2.before(currentDate)) {
								String[] splitDate = splitDate(myMedicineList.get(i).getDiaryStart());
								dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time2.getHours(), time2.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
										myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime2(), myMedicineList.get(i).getAmount2(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
							} else {
								time2 = sdfTime.parse(myMedicineList.get(i).getDiaryEnd() + " " + myMedicineList.get(i).getTime2());
								if (!time2.before(currentDate)) {
									String[] splitDate = splitDate(myMedicineList.get(i).getDiaryEnd());
									dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time2.getHours(), time2.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
											myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime1(), myMedicineList.get(i).getAmount1(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
								}
							}
						}
						if (!myMedicineList.get(i).getTime3().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							Date time3 = sdfTime.parse(myMedicineList.get(i).getDiaryStart() + " " + myMedicineList.get(i).getTime3());
							Date currentDate = new Date();
							if (!time3.before(currentDate)) {
								String[] splitDate = splitDate(myMedicineList.get(i).getDiaryStart());
								dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time3.getHours(), time3.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
										myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime3(), myMedicineList.get(i).getAmount3(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
							} else {
								time3 = sdfTime.parse(myMedicineList.get(i).getDiaryEnd() + " " + myMedicineList.get(i).getTime3());
								if (!time3.before(currentDate)) {
									String[] splitDate = splitDate(myMedicineList.get(i).getDiaryEnd());
									dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time3.getHours(), time3.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
											myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime3(), myMedicineList.get(i).getAmount1(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
								}
							}
						}
						if (!myMedicineList.get(i).getTime4().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							Date time4 = sdfTime.parse(myMedicineList.get(i).getDiaryStart() + " " + myMedicineList.get(i).getTime4());
							Date currentDate = new Date();
							if (!time4.before(currentDate)) {
								String[] splitDate = splitDate(myMedicineList.get(i).getDiaryStart());
								dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time4.getHours(), time4.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
										myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime4(), myMedicineList.get(i).getAmount4(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
							} else {
								time4 = sdfTime.parse(myMedicineList.get(i).getDiaryEnd() + " " + myMedicineList.get(i).getTime4());
								if (!time4.before(currentDate)) {
									String[] splitDate = splitDate(myMedicineList.get(i).getDiaryEnd());
									dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time4.getHours(), time4.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
											myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime4(), myMedicineList.get(i).getAmount1(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
								}
							}
						}
						if (!myMedicineList.get(i).getTime5().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME)) {
							Date time5 = sdfTime.parse(myMedicineList.get(i).getDiaryStart() + " " + myMedicineList.get(i).getTime5());
							Date currentDate = new Date();
							if (!time5.before(currentDate)) {
								String[] splitDate = splitDate(myMedicineList.get(i).getDiaryStart());
								dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time5.getHours(), time5.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
										myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime5(), myMedicineList.get(i).getAmount5(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
							} else {
								time5 = sdfTime.parse(myMedicineList.get(i).getDiaryEnd() + " " + myMedicineList.get(i).getTime5());
								if (!time5.before(currentDate)) {
									String[] splitDate = splitDate(myMedicineList.get(i).getDiaryEnd());
									dt.add(new AlarmInfo(Integer.parseInt(splitDate[2]), Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[0]), time5.getHours(), time5.getMinutes(), myMedicineList.get(i).getDiaryTitle(),
											myMedicineList.get(i).getDiaryMedicineTitle(), myMedicineList.get(i).getTime5(), myMedicineList.get(i).getAmount1(), myMedicineList.get(i).getDiaryTitleId(), myMedicineList.get(i).getDiaryMedicineId()));
								}
							}
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		myDB.close();
		return dt;
	}

	private Date formatCurrentDate() throws ParseException {
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String date = sdf.format(currentDate);
		currentDate = sdf.parse(date);
		return currentDate;
	}

	public DiaryMedicine checkAlarmForOneTitle(Context context, int medicineId) {
		myDB = new MedicationDiaryDatabase(context);
		myDB.open();
		Cursor medicineCursor = myDB.getDiaryMedicineByMedId(medicineId);
		try {
			if (medicineCursor.getCount() > 0) {
				for(medicineCursor.moveToFirst(); !medicineCursor.isAfterLast(); medicineCursor.moveToNext()) {
					myMedicineList.add(new DiaryMedicine(Integer.parseInt(medicineCursor.getString(0)), Integer.parseInt(medicineCursor.getString(1)), medicineCursor.getString(2), medicineCursor.getString(3), medicineCursor.getString(4),
							 medicineCursor.getString(5),  medicineCursor.getString(6),  medicineCursor.getString(7),  medicineCursor.getString(8),  medicineCursor.getString(9),  medicineCursor.getString(10),  medicineCursor.getString(11),
							 medicineCursor.getString(12), null, null, null));
				}
			}
		} finally {
			medicineCursor.close();
		}
		myDB.close();
		return myMedicineList.get(0);
	}

	public List<DiaryMedicine> checkAlarmForManyTitle(Context context, int titleId) {
		myDB = new MedicationDiaryDatabase(context);
		myDB.open();
		Cursor medicineCursor = myDB.getDiaryMedicineByMedId(titleId);
		try {
			if (medicineCursor.getCount() > 0) {
				for(medicineCursor.moveToFirst(); !medicineCursor.isAfterLast(); medicineCursor.moveToNext()) {
					myMedicineList.add(new DiaryMedicine(Integer.parseInt(medicineCursor.getString(0)), Integer.parseInt(medicineCursor.getString(1)), medicineCursor.getString(2), medicineCursor.getString(3), medicineCursor.getString(4),
							 medicineCursor.getString(5),  medicineCursor.getString(6),  medicineCursor.getString(7),  medicineCursor.getString(8),  medicineCursor.getString(9),  medicineCursor.getString(10),  medicineCursor.getString(11),
							 medicineCursor.getString(12), null, null, null));
				}
			}
		} finally {
			medicineCursor.close();
		}
		myDB.close();
		return myMedicineList;
	}

	/*
	 *
	 */
	public String[] splitDate(String date) {
		String[] _splitDate = date.split("/");
		return _splitDate;
	}
}
