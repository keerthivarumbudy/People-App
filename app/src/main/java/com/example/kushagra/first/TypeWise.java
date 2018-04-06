package com.example.kushagra.first;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.mikephil.charting.charts.BarChart;
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

public class TypeWise extends AppCompatActivity {
    private EditText name;
    private Button b;
    private BarChart barChart;
    private ArrayList<BarEntry> barEntries;
    private DatabaseReference db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_wise);
        name=(EditText)findViewById(R.id.editText7);
        b=(Button)findViewById(R.id.button9);
        barChart=(BarChart)findViewById(R.id.bar);
        barEntries=new ArrayList<>();


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db= FirebaseDatabase.getInstance().getReference().child("Rating").child(name.getText().toString());
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int[] sum=new int[6];
                        int [] count =new int [6];


                        for (DataSnapshot i : dataSnapshot.getChildren())
                        {
                            if((i.child("ComplaintType").getValue().toString()).equals("Road"))
                            {sum[0]=sum[0]+Integer.parseInt(i.child("Score").getValue().toString());
                                count[0]++;}
                            else if((i.child("ComplaintType").getValue().toString()).equals("Water Supply")) {
                                sum[1] = sum[1] + Integer.parseInt(i.child("Score").getValue().toString());
                                count[1]++;
                            }
                            else if((i.child("ComplaintType").getValue().toString()).equals("Electricity"))
                            {sum[2]=sum[2]+Integer.parseInt(i.child("Score").getValue().toString());
                                count[2]++;}
                            else if((i.child("ComplaintType").getValue().toString()).equals("Animal Control"))
                            {sum[3]=sum[3]+Integer.parseInt(i.child("Score").getValue().toString());
                                count[3]++;}
                            else if((i.child("ComplaintType").getValue().toString()).equals("Street Lights")) {
                                sum[4] = sum[4] + Integer.parseInt(i.child("Score").getValue().toString());
                                count[4]++;
                            }
                            else if((i.child("ComplaintType").getValue().toString()).equals("Other"))
                            {sum[5]=sum[5]+Integer.parseInt(i.child("Score").getValue().toString());
                                count[5]++;}


                        }
                        for(int j=0;j<6;j++)
                        {
                            if(count[j]==0)
                                count[j]=1;
                        }
                        float s1=(float)((sum[0]/count[0]));
                        barEntries.add(new BarEntry(s1,0));
                        s1=(float)((sum[1]/count[1]));
                        barEntries.add(new BarEntry(s1,1));
                        s1=(float)((sum[2]/count[2]));
                        barEntries.add(new BarEntry(s1,2));
                        s1=(float)((sum[3]/count[3]));
                        barEntries.add(new BarEntry(s1,3));
                        s1=(float)((sum[4]/count[4]));
                        barEntries.add(new BarEntry(s1,4));
                        s1=(float)((sum[5]/count[5]));
                        barEntries.add(new BarEntry(s1,5));

                        // barChart.setDrawBarShadow(false);
                        //barChart.setDrawValueAboveBar(true);
                        //barChart.setMaxVisibleValueCount(100);
                        //barChart.setPinchZoom(true);
                        //barChart.fitScreen();
                        //barChart.setDrawGridBackground(false);



                        BarDataSet barDataSet=new BarDataSet(barEntries,"Type Scores");
                        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        ArrayList<String> names=new ArrayList<>();
                        names.add("Roads");
                        names.add("Water Supply");
                        names.add("Electricity");
                        names.add("Animal Control");
                        names.add("Street lights");
                        names.add("others");

                        BarData barData=new BarData(names,barDataSet);


                        barChart.setData(barData);
                        barChart.setTouchEnabled(true);
                        barChart.setDragEnabled(true);
                        barChart.setSaveEnabled(true);
                        barChart.setScaleXEnabled(true);
                        barChart.animateXY(3000,3000);






                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });






            }
        });
    }
}