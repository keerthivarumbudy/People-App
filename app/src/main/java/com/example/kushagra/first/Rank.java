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
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
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
                int count=0;sum=0;

                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    sum=sum+Integer.parseInt(i.child("Keerthi").child("Score").getValue().toString());
                    count++;

                }
                float s1=(float)((sum/count)*100.0);
                entries.add(new BarEntry(1,s1));

                count=0;sum=0;
                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    sum=sum+Integer.parseInt(i.child("Kush").child("Score").getValue().toString());
                    count++;

                }
                float s2=(float)((sum/count)*100.0);
                entries.add(new BarEntry(1,s2));

                count=0;sum=0;
                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    sum=sum+Integer.parseInt(i.child("Kush").child("Score").getValue().toString());
                    count++;

                }
                float s3=(float)((sum/count)*100.0);
                entries.add(new BarEntry(1,s3));



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* db1=FirebaseDatabase.getInstance().getReference("Rating").child("kush");

        db1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count=0;sum=0;

                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    sum=sum+Integer.parseInt(i.child("Score").getValue().toString());
                    count++;

                }
                float s1=(float)((sum/count)*100.0);
                entries.add(new BarEntry(2,s1));
                //print(sum,count,j);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        db2=FirebaseDatabase.getInstance().getReference("Rating").child("sarkar");

        db2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int count=0;sum=0;

                for(DataSnapshot i : dataSnapshot.getChildren())
                {
                    sum=sum+Integer.parseInt(i.child("Score").getValue().toString());
                    count++;

                }
                float s1=(float)((sum/count));
                Log.d("sff",""+s1);
                entries.add(new BarEntry(3,s1));
                //print(sum,count,j);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/




       barChart.setDrawBarShadow(false);
       barChart.setDrawValueAboveBar(true);
       barChart.setMaxVisibleValueCount(50);
       barChart.setPinchZoom(true);
       barChart.canScrollHorizontally(10);
       barChart.setDrawGridBackground(true);






    }




}
