package com.med.dic.search.medicine;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.med.dic.R;

public class AutocompleteSearchAdapter extends ArrayAdapter<Medicine> {
	private Context context;
	private ArrayList<Medicine> values;

	public AutocompleteSearchAdapter(Context context, int textViewResourceId, ArrayList<Medicine> values) {
		super(context, textViewResourceId, values);
		this.context = context;
		this.values = values;
	}

	public static class ViewHolder {
        public TextView item1;
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
            v = inflater.inflate(R.drawable.item_autocomplete, parent, false);
            holder = new ViewHolder();
            holder.item1 = (TextView) v.findViewById(R.id.medName);
            v.setTag(holder);
        } else {
            holder=(ViewHolder)v.getTag();
        }
        final Medicine medicine = values.get(position);
        if (medicine!=null) {
        	holder.item1.setText(medicine.getName());
        }
        return v;
	}
}
