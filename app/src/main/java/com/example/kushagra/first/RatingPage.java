package com.example.kushagra.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class RatingPage extends AppCompatActivity {
    private RadioButton NoResponse;
    private RadioButton ProblemSolved;
    private EditText DaysEditText;
    private RatingBar ratingBar;
    private float Rating;
    private Button ratingSubmissionButton;
    private TextView textviewone;
    private TextView textview2;
    private Button RadioResponseButton;
    private String CId;
    private String Politician;
    private int days;
    private DatabaseReference mdatabaseRef;
    private DatabaseReference mdatabaseRef2;
    private float score2;
    private float score3;
    private float score;
    private String node;
    private String currentDate;
    private String complaintType;
    private String complaintID2;
    private float x;
    private String SubmissionDatestring2;
    private java.util.Date SubmissionDate;
    private java.util.Date now;
    private java.util.Date old;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        CId = bundle.getString("CID");
        Politician = bundle.getString("Politician");
        setContentView(R.layout.activity_rating_page);
        RadioResponseButton = (Button) findViewById(R.id.RadioResponseButton);
        ProblemSolved = (RadioButton) findViewById(R.id.ProblemSolvedradioButton);
        NoResponse = (RadioButton) findViewById(R.id.NoResponseradioButton);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        DaysEditText = (EditText) findViewById(R.id.NumOfDays);
        ratingSubmissionButton = (Button) findViewById(R.id.ratingSubmissionButton);
        textviewone = (TextView) findViewById(R.id.textView4);
        textview2 = (TextView) findViewById(R.id.textView5);
        ratingBar.setVisibility(View.GONE);
        DaysEditText.setVisibility(View.GONE);
        textviewone.setVisibility(View.GONE);
        textview2.setVisibility(View.GONE);
        ratingSubmissionButton.setVisibility(View.GONE);
        mdatabaseRef = FirebaseDatabase.getInstance().getReference("Rating");
        if (Politician.equals("Complaints_keerthi_ward1")) {
            node = "keerthi";
        } else if (Politician.equals("Complaints_kush_ward2")) {
            node = "kush";
            // mPoliticianChild=mRef.child(databaseNode);
        } else {
            node = "sarkar";
        }
        complaintID2 = mdatabaseRef.push().getKey();
        RadioResponseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (ProblemSolved.isChecked()) {
                    ratingBar.setVisibility(View.VISIBLE);
                    DaysEditText.setVisibility(View.VISIBLE);
                    textviewone.setVisibility(View.VISIBLE);
                    textview2.setVisibility(View.VISIBLE);
                    ratingSubmissionButton.setVisibility(View.VISIBLE);
                    ProblemSolved.setVisibility(View.GONE);
                    NoResponse.setVisibility(View.GONE);
                    RadioResponseButton.setVisibility(View.GONE);
                    now = new java.util.Date();
                    currentDate = new SimpleDateFormat("dd-MMM-yyyy").format(now);
                    mdatabaseRef2 = FirebaseDatabase.getInstance().getReference(Politician).child(CId).getRef();
                    mdatabaseRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            outerloop:
                            Log.d("WHAT IS HAPPENING", "LOL");
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Log.d("Check1: ", ds.getKey());
                                Log.d("Check2: ", ds.getKey());
                                Log.d("Check2: ", ds.getValue().toString());
                                if (ds.getKey().equals("ComplaintType")) {
                                    complaintType = ds.getValue().toString();
                                    Log.d("COmplainttype!!!!!!", complaintType);

                                }
                                else if(ds.getKey().equals("ComplaintDate")){
                                 SubmissionDatestring2=ds.getValue().toString();
                                }
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                } else if (NoResponse.isChecked()) {
                    now = new java.util.Date();
                    currentDate = new SimpleDateFormat("dd-MMM-yyyy").format(now);
                    mdatabaseRef2 = FirebaseDatabase.getInstance().getReference(Politician).child(CId).getRef();
                    mdatabaseRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            outerloop:
                            Log.d("WHAT IS HAPPENING", "LOL");
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Log.d("Check1: ", ds.getKey());
                                Log.d("Check2: ", ds.getKey());
                                Log.d("Check2: ", ds.getValue().toString());
                                if (ds.getKey().equals("ComplaintType")) {
                                    complaintType = ds.getValue().toString();
                                    Log.d("COmplainttype!!!!!!", complaintType);

                                }
                                else if(ds.getKey().equals("ComplaintDate")){
                                    final String SubmissionDatestring=ds.getValue().toString();
                                    x=EnterOrNot(SubmissionDatestring);
                                    if (x!=0 ){
                                        Toast toast = Toast.makeText(RatingPage.this, "There are still "+x+" days left to solve the complaint", Toast.LENGTH_LONG);
                                        toast.show();
                                    }
                                    else if(x==-1){

                                    }
                                    else {
                                        Toast toast = Toast.makeText(RatingPage.this, "Rating Submitted ", Toast.LENGTH_LONG);
                                        toast.show();
                                        mdatabaseRef.child(node).child(complaintID2).child("Score").setValue(0);
                                        mdatabaseRef.child(node).child(complaintID2).child("Date").setValue(currentDate);
                                        mdatabaseRef.child(node).child(complaintID2).child("ComplaintType").setValue(complaintType);
                                    }
                                }
                            }
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }
        });
        ratingSubmissionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Rating = ratingBar.getRating();
                days = Integer.parseInt(DaysEditText.getText().toString());
                final Float score4 = score2;
                takeRating(score4, CId, Politician, Rating, days, complaintType);
                //mdatabaseRef2.removeValue();

            }
        });
    }

    public void takeRating(Float score4, final String CId, String Politician, float Rating, int days, String complaintType) {
        final float deduction = 50 / 33;
        score = Rating * 50 / 5 + (50 - (days * deduction));

        Log.d("Check3: ", "" + score2);
        if (score4 == 0) {
            score3 = score;
        } else {
            score3 = (score + score4) / 2;
        }
        mdatabaseRef.child(node).child(complaintID2).child("Score").setValue(score3);
        mdatabaseRef.child(node).child(complaintID2).child("Date").setValue(currentDate);
        mdatabaseRef.child(node).child(complaintID2).child("ComplaintType").setValue(complaintType);
        Toast toast = Toast.makeText(this, "Rating Submitted " + score, Toast.LENGTH_LONG);
        toast.show();
    }

    public float EnterOrNot(String SubmissionDateString){

        float difference=diff(SubmissionDateString);
        if(difference>20){
            return 0;
        }
        else{

        return 20-difference;


        }

    }
    public float diff(String dateBeforeString){
        SimpleDateFormat myFormat = new SimpleDateFormat( "dd-MMM-yyyy");

        try {

            old= myFormat.parse(dateBeforeString);
            long difference= now.getTime()-old.getTime();
            float daysBetween = (difference / (1000*60*60*24));
            Log.d("CHECKING DATE", ""+daysBetween);
            /* You can also convert the milliseconds to days using this method
             * float daysBetween =
             *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
             */
            return daysBetween;


        } catch (Exception e) {
            return -2;
        }
    }

}
