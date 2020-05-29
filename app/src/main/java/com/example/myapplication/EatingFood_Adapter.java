package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EatingFood_Adapter extends ArrayAdapter<EatingFood> { // класс адаптер для вывода данных на экран

public EatingFood_Adapter (Context context, ArrayList arr) {
        super(context, R.layout.list_adapter, arr);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

final EatingFood food = getItem(position);

        if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_adapter, null);
        }

        TextView textView = convertView.findViewById(R.id.TXTtext);
        TextView timeView = convertView.findViewById(R.id.TXTtime);
        textView.setText(food.getName() + "\n" + "Хлебная единица : " + food.getXe());
        timeView.setText(food.getTime());

        return convertView;
    }
}
