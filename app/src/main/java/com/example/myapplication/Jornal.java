package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Jornal extends AppCompatActivity {

    private ListView listView;
    private List_db db;
    private NotificationHelp notification;
    private List_adapter list_adapter;

    private int count = 0;
    private int lastPosition;

    @Override
    protected void onCreate(final Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.jornal);

        getSupportActionBar().hide();

        notification = new NotificationHelp(getApplicationContext());

        db = new List_db(getApplicationContext());

        listView = findViewById(R.id.lister);

        list_adapter = new List_adapter(this, db.selectAll());

        list_adapter.clear();

        list_adapter.addAll(db.selectAll());

        listView.setAdapter(list_adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              deleteRemember(position);
            }
        });
    }

       public void returnClick(View view) {
           Intent intent = new Intent(Jornal.this, MainActivity.class);
           startActivity(intent);
           finish();
       }

       public void addClick(View view) {
           Intent intent = new Intent(Jornal.this, Add_list.class);
           startActivity(intent);
           finish();
       }

       public void deleteRemember(int position) {

           if(lastPosition == position){
               count++;
           }
           if(count == 3){

               String text = list_adapter.getItem(position).getText();

               lastPosition = position;

               listView = findViewById(R.id.lister);

               db.delete(db.searchID(text));

               list_adapter.clear();

               list_adapter.addAll(db.selectAll());

               count = 0;

               Toast.makeText(this,"Заметка удалена",Toast.LENGTH_LONG).show();
           }
           lastPosition = position;
       }
    }



