package com.example.kushagra.first;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ComplaintTracking extends AppCompatActivity {
    private Button btracking;
    private EditText Cid;
    private String CId;
    private DatabaseReference database;
    private String acceptValue;
    private ProgressBar trackingProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_tracking);
        btracking = (Button) findViewById(R.id.btracking);
        Log.d("check1", "Button initialised");
        trackingProgressBar= (ProgressBar) findViewById(R.id.trackingProgressBar);
        trackingProgressBar.setVisibility(View.GONE);
        btracking.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Check 2", "On click entered");
                Cid = (EditText) findViewById(R.id.IdforTracking);
                CId = Cid.getText().toString().trim();
                Log.d("THE COMPLAINT ID IS",CId);
                database = FirebaseDatabase.getInstance().getReference();
                Log.d("database working?", database.toString());
                trackingProgressBar.setVisibility(View.VISIBLE);
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        iterateFB(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

        });

    }

    public void iterateFB(DataSnapshot dataSnapshot) {
        String ref ="nothing";
        outerloop:
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Log.d("INSIDE FIRST ITERATION",ds.getKey());
            for (DataSnapshot ds2 : ds.getChildren()){
                Log.d("INSIDE SECOND ITERATION",ds2.getKey());
                if(ds2.getKey().trim().equals(CId)){
                    Log.d("COMPARED ID ", ds2.getKey() + "  perfectly");
                    for (DataSnapshot ds3 :ds2.getChildren()) {
                        Log.d("CHILDREN", ds3.getKey());
                        if (ds3.getKey().equals("Accepted")) {
                            if (ds3.getValue().equals("Yes")) {
                                Log.d("ACCEPTED", "YES");
                                ref = ds.getKey();
                                trackingProgressBar.setVisibility(View.GONE);
                                popupWindow(CId, ds.getKey());
                                break outerloop;
                            } else {
                                trackingProgressBar.setVisibility(View.GONE);
                                Toast toast = Toast.makeText(this, "Your complaint " + CId + " was submitted to " + ds.getKey() + "\n It has been not yet been Accepted", Toast.LENGTH_LONG);
                                toast.show();
                                break outerloop;
                            }

                        }
                    }
                    }
                }
            }
            if(ref.equals("nothing")) {
                trackingProgressBar.setVisibility(View.GONE);
                Toast toast=Toast.makeText(this,"This complaint does not exist",Toast.LENGTH_LONG);
                toast.show();

        }


        }
        public void popupWindow(String CId, String ref){
            Toast toast=Toast.makeText(this,"Your complaint "+CId+" was submitted to "+ref+"\n It has been Accepted",Toast.LENGTH_LONG);
            toast.show();
            }
    }
