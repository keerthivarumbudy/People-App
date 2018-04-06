package com.example.kushagra.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class Results extends AppCompatActivity {
   Button rank;
   Button comparision;
    private Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        rank=(Button)findViewById(R.id.rank);
        comparision=(Button) findViewById(R.id.auth);
        b1=(Button) findViewById(R.id.typewise);
        rank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Results.this,Rank.class);
                Results.this.startActivity(intent);

            }
        });
        comparision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Results.this,MonthlyAnalysis.class);
                Results.this.startActivity(intent);

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Results.this,TypeWise.class);
                Results.this.startActivity(intent);

            }
        });


    }
}
