package com.med.dic.diary.using;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.med.dic.R;

public class DiaryViewAdapter extends ArrayAdapter<DiaryTitle> {

	private Context context;
	private ArrayList<DiaryTitle> values;

	public DiaryViewAdapter(Context context, int textViewResourceId, ArrayList<DiaryTitle> values) {
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
	public DiaryTitle getItem(int position) {
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
        final DiaryTitle title = values.get(position);
        if (title!=null) {
        	holder.item.setText(title.getTitleName());
        	holder.item1.setText("Bắt đầu " + title.getStartDate() + " - " + "Kết thúc: " + title.getEndDate());
        }
        return v;
	}
}
