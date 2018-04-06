package com.example.kushagra.first;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyAnalysis extends AppCompatActivity {
    LineChart lineChart;
    private ArrayList<ILineDataSet> dataSets = new ArrayList<>();
    private ArrayList<String> xVals = new ArrayList<String>();
    private DatabaseReference mDatabaseRef1;
    private DatabaseReference mDatabaseRef2;
    private DatabaseReference mDatabaseRef3;
    private java.util.Date date;


    int[] count={0,0,0,0,0,0,0,0,0,0,0,0};
    double[] arr={0,0,0,0,0,0,0,0,0,0,0,0};
//    private HashMap<String,Double> hashMap;
//    private HashMap<String,Integer> hashMap2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_analysis);
       /* hashMap.put("Jan",0.0);
        hashMap.put("Feb",0.0);
        hashMap.put("Mar",0.0);
        hashMap.put("May",0.0);
        hashMap.put("Jun",0.0);
        hashMap.put("Jul",0.0);
        hashMap.put("Aug",0.0);
        hashMap.put("Sep",0.0);
        hashMap.put("Oct",0.0);
        hashMap.put("Nov",0.0);
        hashMap.put("Dec",0.0);
        hashMap.put("Apr",0.0);*/

       /* ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Jan");
        xVals.add("Feb");
        xVals.add("Mar");
        xVals.add("Apr");
        xVals.add("May");
        xVals.add("Jun");
        xVals.add("Jul");
        xVals.add("Aug");
        xVals.add("Sep");
        xVals.add("Oct");
        xVals.add("Nov");
        xVals.add("Dec");*/

        lineChart = (LineChart) findViewById(R.id.linechart);
        mDatabaseRef1 = FirebaseDatabase.getInstance().getReference("Rating");
        mDatabaseRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (DataSnapshot ds1 : dataSnapshot.getChildren()) {
                    if (ds1.getKey().equals("keerthi")) {
                        for (DataSnapshot ds2 : ds1.getChildren()) {
                            String y = "";
                            Float z;

                            for (DataSnapshot ds : ds2.getChildren()) {

                                if (ds.getKey().equals("Date")) {
                                    y = month(ds.getValue().toString());


                                } else if (ds.getKey().equals("Score")) {
                                    z = Float.parseFloat(ds.getValue().toString());

                                    switch (y) {
                                        case "Jan":
                                            count[0] += 1;
                                            arr[0] += z;
                                            break;
                                        case "Feb":
                                            count[1] += 1;
                                            arr[1] += z;
                                            break;
                                        case "Mar":
                                            count[2] += 1;
                                            arr[2] += z;
                                            break;
                                        case "Apr":
                                            count[3] += 1;
                                            arr[3] = arr[3] + z;
                                            break;
                                        case "May":
                                            count[4] += 1;
                                            arr[4] += z;
                                            break;
                                        case "Jun":
                                            count[5] += 1;
                                            arr[5] += z;
                                            break;
                                        case "Jul":
                                            count[6] += 1;
                                            arr[6] += z;
                                            break;
                                        case "Aug":
                                            count[7] += 1;
                                            arr[7] += z;
                                            break;
                                        case "Sep":
                                            count[8] += 1;
                                            arr[8] += z;
                                            break;
                                        case "Oct":
                                            count[9] += 1;
                                            arr[9] += z;
                                            break;
                                        case "Nov":
                                            count[10] += 1;
                                            arr[10] += z;
                                            break;
                                        case "Dec":
                                            count[11] += 1;
                                            arr[11] += z;
                                            break;
                                        default:
                                            Log.d("fail", "fail");
                                    }

                                }

                            }


                        }
                        ArrayList<Entry> entries1 = new ArrayList<>();
                        for (int i = 0; i < 12; i++) {
                            float e;
                            if(count[i]==0){
                                e=0;
                            }
                            else{
                                e=(float) (arr[i] / count[i]);
                            }

                            entries1.add(new Entry(e,i+1 ));
                            Log.d("Checking array", entries1.get(i).toString());
                        }
                        LineDataSet set1 = new LineDataSet(entries1, ds1.getKey());
                        set1.setColor(Color.RED);
                        set1.setLineWidth(1f);
                        set1.setVisible(true);
                        dataSets.add(set1);
                    }

                    if (ds1.getKey().equals("kush")) {
                        for (DataSnapshot ds2 : ds1.getChildren()) {
                            String y = "";
                            Float z;
                            for (DataSnapshot ds : ds2.getChildren()) {

                                if (ds.getKey().equals("Date")) {
                                    y = month(ds.getValue().toString());


                                } else if (ds.getKey().equals("Score")) {
                                    z = Float.parseFloat(ds.getValue().toString());

                                    switch (y) {
                                        case "Jan":
                                            count[0] += 1;
                                            arr[0] += z;
                                            break;
                                        case "Feb":
                                            count[1] += 1;
                                            arr[1] += z;
                                            break;
                                        case "Mar":
                                            count[2] += 1;
                                            arr[2] += z;
                                            break;
                                        case "Apr":
                                            count[3] += 1;
                                            arr[3] = arr[3] + z;
                                            break;
                                        case "May":
                                            count[4] += 1;
                                            arr[4] += z;
                                            break;
                                        case "Jun":
                                            count[5] += 1;
                                            arr[5] += z;
                                            break;
                                        case "Jul":
                                            count[6] += 1;
                                            arr[6] += z;
                                            break;
                                        case "Aug":
                                            count[7] += 1;
                                            arr[7] += z;
                                            break;
                                        case "Sep":
                                            count[8] += 1;
                                            arr[8] += z;
                                            break;
                                        case "Oct":
                                            count[9] += 1;
                                            arr[9] += z;
                                            break;
                                        case "Nov":
                                            count[10] += 1;
                                            arr[10] += z;
                                            break;
                                        case "Dec":
                                            count[11] += 1;
                                            arr[11] += z;
                                            break;
                                        default:
                                            Log.d("fail", "fail");
                                    }

                                }

                            }


                        }
                        ArrayList<Entry> entries1 = new ArrayList<>();
                        for (int i = 0; i < 12; i++) {
                            float e;
                            if(count[i]==0){
                                e=0;
                            }
                            else{
                                e=(float) (arr[i] / count[i]);
                            }

                            entries1.add(new Entry(e,i+1));
                            Log.d("Checking array", entries1.get(i).toString());
                        }
                        LineDataSet set1 = new LineDataSet(entries1, ds1.getKey());
                        set1.setColor(Color.GREEN);
                        set1.setLineWidth(1f);
                        dataSets.add(set1);
                    }
                    if (ds1.getKey().equals("sarkar")) {
                        for (DataSnapshot ds2 : ds1.getChildren()) {
                            String y = "";
                            Float z;
                            for (DataSnapshot ds : ds2.getChildren()) {

                                if (ds.getKey().equals("Date")) {
                                    y = month(ds.getValue().toString());


                                } else if (ds.getKey().equals("Score")) {
                                    z = Float.parseFloat(ds.getValue().toString());

                                    switch (y) {
                                        case "Jan":
                                            count[0] += 1;
                                            arr[0] += z;
                                            break;
                                        case "Feb":
                                            count[1] += 1;
                                            arr[1] += z;
                                            break;
                                        case "Mar":
                                            count[2] += 1;
                                            arr[2] += z;
                                            break;
                                        case "Apr":
                                            count[3] += 1;
                                            arr[3] = arr[3] + z;
                                            break;
                                        case "May":
                                            count[4] += 1;
                                            arr[4] += z;
                                            break;
                                        case "Jun":
                                            count[5] += 1;
                                            arr[5] += z;
                                            break;
                                        case "Jul":
                                            count[6] += 1;
                                            arr[6] += z;
                                            break;
                                        case "Aug":
                                            count[7] += 1;
                                            arr[7] += z;
                                            break;
                                        case "Sep":
                                            count[8] += 1;
                                            arr[8] += z;
                                            break;
                                        case "Oct":
                                            count[9] += 1;
                                            arr[9] += z;
                                            break;
                                        case "Nov":
                                            count[10] += 1;
                                            arr[10] += z;
                                            break;
                                        case "Dec":
                                            count[11] += 1;
                                            arr[11] += z;
                                            break;
                                        default:
                                            Log.d("fail", "fail");
                                    }

                                }

                            }


                        }
                        ArrayList<Entry> entries1 = new ArrayList<>();
                        for (int i = 0; i < 12; i++) {
                            float e;
                            if(count[i]==0){
                                e=0;
                            }
                            else{
                                e=(float) (arr[i] / count[i]);
                            }

                            entries1.add(new Entry(e,i+1 ));
                            Log.d("Checking array", entries1.get(i).toString());
                        }
                        LineDataSet set1 = new LineDataSet(entries1, ds1.getKey());
                        set1.setColor(Color.BLUE);
                        set1.setLineWidth(1f);
                        dataSets.add(set1);
                    }
                    String[] xaxes = new String[] {"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEPT","OCT","NOV","DEC"};
                    /*for (int i = 0; i < 12; i++) {
                        xaxes[i] =Integer.toString(i);
                    }*/

                    LineData lineData = new LineData(xaxes,dataSets);
                    lineChart.animateXY(3000,3000);
                    lineChart.setData(lineData);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public String month(String dateBeforeString){
        SimpleDateFormat myFormat = new SimpleDateFormat( "dd-MMM-yyyy");

        try {

            java.util.Date x= myFormat.parse(dateBeforeString);
            String daysBetween=new SimpleDateFormat("MMM").format(x);
            return daysBetween;


        } catch (Exception e) {
            return "May";
        }
    }
}