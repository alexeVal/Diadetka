package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView  butt1;
    ImageView  butt2;
    ImageView  butt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        butt1 = findViewById(R.id.butt1);
        butt2 = findViewById(R.id.butt2);
        butt3 = findViewById(R.id.butt3);

        butt1.setImageResource(R.drawable.med2);
        butt2.setImageResource(R.drawable.food2);
        butt3.setImageResource(R.drawable.jornal2);

        ImageView imageView = findViewById(R.id.img1);
        imageView.setImageResource(R.drawable.bear);


        butt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calculator.class);
                startActivity(intent);
            }
        });

        butt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Food_jornal.class);
                startActivity(intent);
            }
        });

        butt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Jornal.class);
                startActivity(intent);
            }
        });
    }
}

