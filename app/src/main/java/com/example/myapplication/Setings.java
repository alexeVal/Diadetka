package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Setings extends AppCompatActivity {   // класс настроек

    private VK_ID_base vkIdBase;
    private EditText editText;
    ListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setings_activiti);
        vkIdBase = new VK_ID_base(this);
        editText = (EditText)findViewById(R.id.idText);
        listView = (ListView)findViewById(R.id.idView);
        adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_list_item_1, vkIdBase.selectAll());
        listView.setAdapter(adapter);
    }

    public void addID(View view){ // добавление id
        try {
            int id = Integer.parseInt(editText.getText().toString());
            vkIdBase.insert(id);
        } catch (NumberFormatException e){
            Toast.makeText(this,"Неверный формат! Используйте только цифры.",Toast.LENGTH_LONG).show();
        }
    }
    public void back(View view){   // на главный экран
        Intent intent = new Intent(Setings.this,MainActivity.class);
        startActivity(intent);
    }
}
