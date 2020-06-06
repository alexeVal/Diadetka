package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Shugar_list extends AppCompatActivity {   // класс истории уровня сахара

    private Shugar_db shugar_db;
    private EditText editText;
    private Shugar_adapter shugar_adapter;
    private int lastPosition,count;
    private Application_vk application_vk = new Application_vk();
    private double level;
    private VK_ID_base vkIdBase;
    GraphView graph;
    int i = 1;
    LineGraphSeries<DataPoint> series;
    DataPoint[] points;

    @Override
    public void onCreate( Bundle savedInstanceState) {  // создать необходимые объекты и активность

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activiti);
        getSupportActionBar().hide();
        shugar_db = new Shugar_db(this);
        editText = findViewById(R.id.Etxt1);
        shugar_adapter = new Shugar_adapter(this,shugar_db.selectAll());
        ListView shugar_list = (ListView)findViewById(R.id.shugarView);
        shugar_list.setAdapter(shugar_adapter);
        vkIdBase = new VK_ID_base(this);
        graph = (GraphView) findViewById(R.id.graph);
        bildGraph(shugar_db.selectAll());
        shugar_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteItem(position);
            }
        });
    }

    public void bildGraph (ArrayList<Shugar> levels){

        points = new DataPoint[levels.size()];

        for (int i = 0; i < levels.size(); i++){
            points[i] = new DataPoint(i,levels.get(i).getLevel());
        }

        series = new LineGraphSeries<>(points);

        // set manual X bounds
        graph.getViewport().setXAxisBoundsManual(true);


        graph.getViewport().setMinX(0);

        graph.getViewport().setMaxX(10);

        // enable scrolling
        graph.getViewport().setScrollable(true);

        graph.removeAllSeries();

        graph.addSeries(series);

        i++;
    }

    public void addShugar(View view){  // добавить запись
        try {
            level = Double.parseDouble(editText.getText().toString());
            shugar_db.insert(level,getStringDate());
            bildGraph(shugar_db.selectAll());
            application_vk.sendMSG(getStringDate() + "\n"+ "Новая запись в дневник уровня сахара : " + level,vkIdBase.selectAll());

        } catch (NumberFormatException e){
            Toast.makeText(this,"Неверный формат ввода! Используй только цифры и знак .",Toast.LENGTH_LONG).show();
        }
        shugar_adapter.clear();
        shugar_adapter.addAll(shugar_db.selectAll());
    }

    public void backClick(View view){     // на главный экран
        Intent intent = new Intent(Shugar_list.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    String getStringDate(){                    // получить текущую дату  время
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat dateFormat = new SimpleDateFormat("dd.MM");
        Date date = new Date();
        String time = timeFormat.format(date);
        String da = dateFormat.format(date);
        return da + " " + time ;
    }

    public void deleteItem(int position) {    // удаление записи

        if(lastPosition == position){
            count++;
        }
        if(count == 3){

            double level = shugar_adapter.getItem(position).getLevel();

            lastPosition = position;

            shugar_db.delete(shugar_db.searchID(level));

            shugar_adapter.clear();

            shugar_adapter.addAll(shugar_db.selectAll());

            count = 0;

            Toast.makeText(this,"Запись удалена",Toast.LENGTH_LONG).show();

            bildGraph(shugar_db.selectAll());
        }
        lastPosition = position;
    }

}


