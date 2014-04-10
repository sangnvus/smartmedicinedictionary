package com.med.dic.search.medicine;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.med.dic.R;

public class ClickButtonSearchAdapter extends ArrayAdapter<Medicine> {
	private Context context;
	private ArrayList<Medicine> values;

	public ClickButtonSearchAdapter(Context context, int textViewResourceId, ArrayList<Medicine> values) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
	}

	public static class ViewHolder {
        public TextView item2;
        public TextView item3;
    }

	@Override
	public int getCount() {
		return values.size();
	}

	@Override
	public Medicine getItem(int position) {
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
            v = inflater.inflate(R.drawable.item_search_btn, parent, false);
            holder = new ViewHolder();
            holder.item2 = (TextView) v.findViewById(R.id.medicineName);
            holder.item3 = (TextView) v.findViewById(R.id.manufacturer);
            v.setTag(holder);
        } else {
            holder=(ViewHolder)v.getTag();
        }
        final Medicine medicine = values.get(position);
        if (medicine!=null) {
        	holder.item2.setText(medicine.getName());
        	holder.item3.setText(medicine.getManufacture());
        }
        return v;
	}

}
