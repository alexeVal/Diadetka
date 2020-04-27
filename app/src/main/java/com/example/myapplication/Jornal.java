package com.example.myapplication;


import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Jornal extends AppCompatActivity {


    ListView listView;

    List_db db;

    NotificationHelp notification;

    final int REQUEST_CODE=101;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.jornal);

        getSupportActionBar().hide();



        Intent intent=new Intent(this,MyReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),REQUEST_CODE,intent,PendingIntent.FLAG_ONE_SHOT);

        notification = new NotificationHelp(getApplicationContext());

        db = new List_db(getApplicationContext());

        listView = findViewById(R.id.lister);

        final List_adapter list_adapter = new List_adapter(this,db.selectAll());

        list_adapter.clear();

        list_adapter.addAll(db.selectAll());

        listView.setAdapter(list_adapter);

        Button butt_ret = findViewById(R.id.returnJornal);

        Button butt_add = findViewById(R.id.add_butt);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            int count = 0;
            int lastPosition;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(lastPosition == position){
                    count++;
                }
                if(count == 3){

                    String text = list_adapter.getItem(position).getText();

                    lastPosition = position;

                    listView = findViewById(R.id.lister);

                    DateFormat df = new SimpleDateFormat("dd MMM yyyy, HH:mm");

                    db.delete(db.searchID(text));

                    list_adapter.clear();

                    list_adapter.addAll(db.selectAll());

                    count = 0;

                    notification.showNote("Напоминание удалено","",NotificationHelp.PENSIL);

                }

                lastPosition = position;
            }
        });

        butt_ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jornal.this, MainActivity.class);
                startActivity(intent);
            }
        });
        butt_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Jornal.this, Add_list.class);
                startActivity(intent);
            }
        });

    }
}


