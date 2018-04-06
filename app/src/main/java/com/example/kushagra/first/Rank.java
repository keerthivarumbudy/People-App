package com.example.kushagra.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Rank extends AppCompatActivity {

    private BarChart barChart;
    private BarData barData;
    ArrayList<BarEntry>  entries;
    private DatabaseReference db;
    private DatabaseReference db1;
    private DatabaseReference db2;
    private int  sum;
    private  int j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        barChart=(BarChart)findViewById(R.id.rankbar);
        barChart.setDrawBarShadow(false);
        barChart.setMaxVisibleValueCount(100);
        entries=new ArrayList<>();
        db=FirebaseDatabase.getInstance().getReference("Rating");

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count=0,sum=0,s=0;

                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    for (DataSnapshot j:  i.getChildren())
                    {

                        sum=sum+Integer.parseInt(j.child("Score").getValue().toString());
                        count++;

                    }
                    Log.d("msm"+s,"sum="+sum+"count"+count);
                    float s1=(float)((sum/count));
                    entries.add(new BarEntry(s1,s));
                    s++;
                    count=0;
                    sum=0;

                }



                barChart.setDrawBarShadow(false);
                barChart.setDrawValueAboveBar(true);
                barChart.setMaxVisibleValueCount(100);
                barChart.setPinchZoom(true);
                barChart.setDrawGridBackground(true);
                barChart.setTouchEnabled(true);
                barChart.setDragEnabled(true);
                barChart.setSaveEnabled(true);
                barChart.animateXY(3000,3000);


                BarDataSet barDataSet=new BarDataSet(entries,"Scores");
                barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                ArrayList<String> names=new ArrayList<>();
                names.add("Keerthi");
                names.add("Kush");
                names.add("Sarkar");
                BarData barData=new BarData(names,barDataSet);


                barChart.setData(barData);







            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }



}
