package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EatingFood_Adapter extends ArrayAdapter<Food> {

public EatingFood_Adapter (Context context, ArrayList arr) {
        super(context, R.layout.list_adapter, arr);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

final Food food = getItem(position);

        if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_adapter, null);
        }

        TextView textView = convertView.findViewById(R.id.TXTtime);
        TextView timeView = convertView.findViewById(R.id.TXTtext);
        textView.setText(food.getName());
        timeView.setText(food.getXe());

        return convertView;
    }
}
