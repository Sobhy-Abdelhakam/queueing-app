package com.example.queueingapp;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button deterministic;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        deterministic = findViewById(R.id.main_deterministic);

        deterministic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(getApplicationContext(), Deterministic.class);
                startActivities(new Intent[] {intent});
            }
        });

    }

}