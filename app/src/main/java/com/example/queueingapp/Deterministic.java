package com.example.queueingapp;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class Deterministic extends AppCompatActivity {
    TextView systemCapacity, arrivalTime, result, tv_M, textToMove;
    TextInputEditText et_systemCapacity, et_arrivalTime, et_serviceTime, et_M;
    Button calc, click;

    static int k,ti, M;
    static double λ, µ,  arrTime, serTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deterministic);
        intiView();

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                k= Integer.parseInt(et_systemCapacity.getText().toString());
                arrTime= Float.parseFloat(et_arrivalTime.getText().toString());
                λ = 1/arrTime;
                serTime= Float.parseFloat(et_serviceTime.getText().toString());
                µ = 1/ serTime;
                DD dd= new DD(µ, λ, k);
                if(λ > µ) {
                    ti= dd.get_ti();
                    arrivalRateGreaterThanServiceRate(ti);
                }
                else{
                    serviceRateGreaterThanOrEqualArrivalRate(dd);

                }
            }
        });

    }

    private void serviceRateGreaterThanOrEqualArrivalRate(DD dd) {
        tv_M.setVisibility(View.VISIBLE);
        et_M.setVisibility(View.VISIBLE);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                M= Integer.parseInt(et_M.getText().toString());
                dd.M= M;
                ti= dd.get_ti_2();
                result.setText("ti= "+ ti);

                textToMove.setVisibility(View.VISIBLE);
                click.setVisibility(View.VISIBLE);
                click.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(getApplicationContext(), waitingTime.class);
                        startActivities(new Intent[]{intent});
                    }
                });
            }
        });
    }

    private void arrivalRateGreaterThanServiceRate(int ti) {
        result.setText("ti= "+ ti);

        textToMove.setVisibility(View.VISIBLE);
        click.setVisibility(View.VISIBLE);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), waitingTime.class);
                startActivities(new Intent[]{intent});
            }
        });
    }

    void intiView(){
        systemCapacity= findViewById(R.id.det_tv_sysCap);
        arrivalTime= findViewById(R.id.det_tv_arrTime);
        textToMove= findViewById(R.id.text_to_move);
        tv_M= findViewById(R.id.det_tv_M);
        et_systemCapacity= findViewById(R.id.et_sysCap);
        et_arrivalTime= findViewById(R.id.et_arrTime);
        et_serviceTime= findViewById(R.id.et_serTime);
        et_M= findViewById(R.id.et_M);
        calc= findViewById(R.id.det_btn_calc);
        click= findViewById(R.id.click_here);
        result= findViewById(R.id.det_tv_result);
    }
}