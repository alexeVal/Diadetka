package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class List_adapter extends ArrayAdapter<Lister> {

    public List_adapter(Context context, ArrayList arr) {
        super(context, R.layout.list_adapter, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Lister lister = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_adapter, null);
        }

// Заполняем адаптер

        TextView textView = convertView.findViewById(R.id.TXTtime);
        TextView timeView = convertView.findViewById(R.id.TXTtext);
        textView.setText(lister.getText());
        timeView.setText(lister.getTime());

        return convertView;
    }
}
