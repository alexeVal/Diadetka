package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;


public class MyAdapter extends ArrayAdapter<Food> {   // адаптер для списка продуктов

    public MyAdapter(Context context, ArrayList<Food> arr) {
        super(context, R.layout.food_list_adapter, arr);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Food food = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.food_list_adapter, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.name)).setText(food.getName() + "\n" + food.getXe());
        Picasso.with(getContext()).load(food.getUrl()).into((ImageView) convertView.findViewById(R.id.picher_food));  // загружаем изображения в фоновом потоке

        return convertView;
    }
    String getXE (int position){
        Food food1 = getItem(position);
        return food1.getXe();
    }
    String getName (int position){
        Food food1 = getItem(position);
        return food1.getName();
    }

}

