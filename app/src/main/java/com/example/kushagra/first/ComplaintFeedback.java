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

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ComplaintFeedback extends AppCompatActivity {
    private Button bfeedback;
    private EditText Cid;
    private String CId;
    private DatabaseReference database;
    private String acceptValue;
    private ProgressBar feedbackProgressBar;
    private String SubmissionDatestring;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_feedback);
        feedbackProgressBar= (ProgressBar) findViewById(R.id.feedbackProgressBar);
        feedbackProgressBar.setVisibility(View.GONE);
        bfeedback= (Button) findViewById(R.id.bfeedback);
        Cid = (EditText) findViewById(R.id.IdforFeedback);
        bfeedback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("Check 2", "On click entered");
                CId = Cid.getText().toString().trim();
                Log.d("THE COMPLAINT ID IS",CId);
                database = FirebaseDatabase.getInstance().getReference();
                Log.d("database working?", database.toString());
                feedbackProgressBar.setVisibility(View.VISIBLE);
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
                    ref=CId.toString();
                    Log.d("COMPARED ID ", ds2.getKey() + "  perfectly");
                    for (DataSnapshot ds3 :ds2.getChildren()){
                        if(ds3.getKey().equals("ComplaintDate")){
                            SubmissionDatestring = ds3.getValue().toString();
                            EnterOrNot(SubmissionDatestring);
                            feedbackProgressBar.setVisibility(View.GONE);
                            popupWindow(CId,ds.getKey());
                            break outerloop;
                        }

                    }
                }
            }
        }
        if(ref.equals("nothing")){
            feedbackProgressBar.setVisibility(View.GONE);
            Toast toast=Toast.makeText(this,"This complaint does not exist",Toast.LENGTH_LONG);
            toast.show();
        }

    }

    public void popupWindow(String CId, String ref){
        Intent intent10=new Intent(ComplaintFeedback.this,RatingPage.class);
        intent10.putExtra("CID",CId);
        intent10.putExtra("Politician",ref);
        ComplaintFeedback.this.startActivity(intent10);

    }
    public void EnterOrNot (String SubmissionDateString){
        Calendar SubmissionCal =Calendar.getInstance();
       /*SubmissionCal.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(SubmissionDateString,new ParsePosition(0)));
        try{
            SubmissionDate = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH).parse(SubmissionDateString);
        }
        catch (Exception e)
        {Log.d("DATE PROBLEMS","DATE HAS PROBLEMS: "+SubmissionDate.toString());
        }
         Calendar now = Calendar.getInstance();
       now.add(Calendar.DATE, -10);
        if (now.getTime().before(SubmissionDate)){
            Toast toast=Toast.makeText(this,"Deadline is not yet over. Wait for 10 days after the date of Complaint Submission",Toast.LENGTH_LONG);
            toast.show();
        }
        else{
            Toast toast=Toast.makeText(this,"All works well.",Toast.LENGTH_LONG);
            toast.show();
        }*/
    }
}
