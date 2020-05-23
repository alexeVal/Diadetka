package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Shugar_adapter extends ArrayAdapter<Shugar> {

    public Shugar_adapter(Context context, ArrayList arr) {
        super(context, R.layout.list_adapter, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Shugar shugar = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_adapter, null);
        }

// Заполняем адаптер

        TextView textView = convertView.findViewById(R.id.TXTtext);
        TextView timeView = convertView.findViewById(R.id.TXTtime);
        textView.setText(Double.toString(shugar.getLevel()));
        timeView.setText(shugar.getTime());
        return convertView;
    }
}

