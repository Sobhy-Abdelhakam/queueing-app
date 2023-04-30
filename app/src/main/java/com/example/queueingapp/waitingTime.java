package com.example.queueingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class waitingTime extends AppCompatActivity {
    LinearLayout linearLayout_time, linearLayout_number;
    TextView ti,tv_time,tv_number, tv_result;
    TextInputEditText et_time, et_number;
    Spinner spinner;
    Button calc;
    double time, wqn, number;
    int nt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_time);
        intiView();

        ti.setText("ti= "+ Deterministic.ti);
        DD dd= new DD(Deterministic.µ, Deterministic.λ, Deterministic.k);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        calculateNt(dd);
                        break;
                    case 1:
                        calculateWqn(dd);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void calculateWqn(DD dd) {
        linearLayout_number.setVisibility(View.VISIBLE);
        linearLayout_time.setVisibility(View.GONE);
        calc.setVisibility(View.VISIBLE);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number= Double.parseDouble(et_number.getText().toString());
                if(Deterministic.λ > Deterministic.µ){
                    wqn= dd.get_Wqn(number);
                    tv_result.setText("Wq(n)= "+wqn);
                }
                else if(Deterministic.λ <= Deterministic.µ){
                    wqn= dd.get_Wqn_2(number);
                    tv_result.setText("Wq(n)= "+wqn);
                }
            }
        });
    }

    private void calculateNt(DD dd) {
        linearLayout_time.setVisibility(View.VISIBLE);
        linearLayout_number.setVisibility(View.GONE);
        calc.setVisibility(View.VISIBLE);

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                time= Double.parseDouble(et_time.getText().toString());
                if(Deterministic.λ > Deterministic.µ){
                    nt= (int) dd.get_nt(time);
                    tv_result.setText("n(t)= "+ nt);
                }
                else if(Deterministic.λ <= Deterministic.µ){
                    nt= (int) dd.get_nt_2(time);
                    tv_result.setText("n(t)= "+ nt);
                }
            }
        });
    }

    void intiView(){
        linearLayout_time= findViewById(R.id.wt_ll_time);
        linearLayout_number= findViewById(R.id.wt_ll_number);
        tv_time= findViewById(R.id.wt_tv_time);
        et_number = findViewById(R.id.et_number);
        tv_number= findViewById(R.id.wt_tv_number);
        ti= findViewById(R.id.wt_tv_ti);
        tv_result= findViewById(R.id.nt);
        et_time= findViewById(R.id.et_time);
        spinner= findViewById(R.id.spinner);
        calc= findViewById(R.id.wt_btn_calc);
    }
}