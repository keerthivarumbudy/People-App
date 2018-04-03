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
import java.text.SimpleDateFormat;
import java.util.Calendar;

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




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getIntent().getExtras();
        CId=bundle.getString("CID");
        Politician=bundle.getString("Politician");
        setContentView(R.layout.activity_rating_page);
        RadioResponseButton=(Button)findViewById(R.id.RadioResponseButton);
        ProblemSolved= (RadioButton) findViewById(R.id.ProblemSolvedradioButton);
        NoResponse =(RadioButton) findViewById(R.id.NoResponseradioButton);
        ratingBar= (RatingBar) findViewById(R.id.ratingBar);
        DaysEditText=(EditText) findViewById(R.id.NumOfDays);
        ratingSubmissionButton=(Button) findViewById(R.id.ratingSubmissionButton);
        textviewone=(TextView)findViewById(R.id.textView4);
        textview2=(TextView)findViewById(R.id.textView5);
        ratingBar.setVisibility(View.GONE);
        DaysEditText.setVisibility(View.GONE);
        textviewone.setVisibility(View.GONE);
        textview2.setVisibility(View.GONE);
        ratingSubmissionButton.setVisibility(View.GONE);
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
                    java.util.Date now = new java.util.Date();
                    currentDate = new SimpleDateFormat( "dd-MMM-yyyy").format(now);
                    mdatabaseRef= FirebaseDatabase.getInstance().getReference("Rating");
                    if (Politician.equals("Complaints_keerthi_ward1")) {
                        node = "keerthi";
                    } else if (Politician.equals("Complaints_kush_ward2")) {
                        node = "kush";
                        // mPoliticianChild=mRef.child(databaseNode);
                    } else {
                        node = "sarkar";
                    }
                    mdatabaseRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot){
                            outerloop:
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                Log.d("Check1: ",ds.getKey());
                                if(ds.getKey().trim().equals(CId)){
                                    Log.d("Check2: ",ds.getKey());
                                    for (DataSnapshot ds2 :ds.getChildren()){
                                        if(ds2.getKey().equals("Score")){
                                            Log.d("Check3: ",ds2.getValue().toString());
                                            final String stringscore=ds2.getValue().toString();
                                            score2=Float.parseFloat(stringscore);
                                            break outerloop;
                                        }
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                else if (NoResponse.isChecked()){
                    mdatabaseRef.child(node).child("Score").setValue(0);
                    mdatabaseRef.child(node).child("Date").setValue(currentDate);
                    Toast toast=Toast.makeText(RatingPage.this,"Rating Submitted ",Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });
        ratingSubmissionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Rating=ratingBar.getRating();
                days= Integer.parseInt(DaysEditText.getText().toString());
                final Float score4=score2;
                takeRating(score4,CId,Politician,Rating,days);

            }});


    }
    public void takeRating(Float score4,final String CId, String Politician, float Rating, int days){
        final float deduction= 50/33;
        score= Rating*50/5 +(50-(days*deduction));

        Log.d("Check3: ",""+score2);
        if(score4==0){
            score3=score;
        }
        else{
            score3 = (score+score4)/2;
        }

        mdatabaseRef.child(node).child("Score").setValue(score3);
        mdatabaseRef.child(node).child("Date").setValue(currentDate);
        Toast toast=Toast.makeText(this,"Rating Submitted "+ score,Toast.LENGTH_LONG);
        toast.show();
    }
}
