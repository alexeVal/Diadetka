package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class List_adapter extends ArrayAdapter<Lister> {  // адаптер для заметок

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

        TextView textView = convertView.findViewById(R.id.TXTtext);
        TextView timeView = convertView.findViewById(R.id.TXTtime);

        if (lister.getIsred() == 1){                  // если необходмо выделяем текст
            textView.setBackgroundColor(Color.YELLOW);
            timeView.setBackgroundColor(Color.YELLOW);
        } else {
            textView.setBackgroundColor(Color.WHITE);
            timeView.setBackgroundColor(Color.WHITE);
        }
        textView.setText(lister.getText());
        timeView.setText("Запланировано на :"+"\n"+lister.getTime());

        return convertView;
    }
}
