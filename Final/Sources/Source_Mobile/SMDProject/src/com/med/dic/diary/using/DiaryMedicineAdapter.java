package com.med.dic.diary.using;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.med.dic.DefaultValueConstant;
import com.med.dic.R;

public class DiaryMedicineAdapter extends ArrayAdapter<DiaryMedicine> {

	private Context context;
	private ArrayList<DiaryMedicine> values;

	public DiaryMedicineAdapter(Context context, int textViewResourceId, ArrayList<DiaryMedicine> values) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
	}

	public static class ViewHolder {
        public TextView item;
        public TextView item1;
	}

	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public DiaryMedicine getItem(int position) {
		return values.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;
		ViewHolder holder;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.drawable.item_diary_title, parent, false);
            holder = new ViewHolder();
            holder.item = (TextView) v.findViewById(R.id.diaryMedicineTv);
            holder.item1 = (TextView) v.findViewById(R.id.diaryMedicineIdTv);
            v.setTag(holder);
        } else {
            holder=(ViewHolder)v.getTag();
        }
        final DiaryMedicine medicine = values.get(position);
        if (medicine!=null) {
        	holder.item.setText(medicine.getDiaryMedicineTitle());
        	if (!medicine.getTime1().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME))
        		holder.item1.setText("Thời gian: " + medicine.getTime1() + " - " + "Liều lượng: " + medicine.getAmount1());
        	else if (!medicine.getTime2().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME))
        		holder.item1.setText("Thời gian: " + medicine.getTime2() + " - " + "Liều lượng: " + medicine.getAmount2());
        	else if (!medicine.getTime3().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME))
        		holder.item1.setText("Thời gian: " + medicine.getTime3() + " - " + "Liều lượng: " + medicine.getAmount3());
        	else if (!medicine.getTime4().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME))
        		holder.item1.setText("Thời gian: " + medicine.getTime4() + " - " + "Liều lượng: " + medicine.getAmount4());
        	else if (!medicine.getTime5().equals(DefaultValueConstant.DIARY_USING_DEFAULT_TIME))
        		holder.item1.setText("Thời gian: " + medicine.getTime5() + " - " + "Liều lượng: " + medicine.getAmount5());
        	else
        		holder.item1.setText("Thời gian: " + DefaultValueConstant.DIARY_USING_NO_VALUE + " - " + "Liều lượng: " + DefaultValueConstant.DIARY_USING_NO_VALUE );
        }
        return v;
	}
}
