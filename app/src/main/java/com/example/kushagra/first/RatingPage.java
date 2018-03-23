package com.example.kushagra.first;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;

public class RatingPage extends AppCompatActivity {
    private RadioButton NoResponse;
    private RadioButton ProblemSolved;
    private EditText DaysEditText;
    private RatingBar ratingBar;
    private float Rating;
    private Button ratingSubmissionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_page);
        ProblemSolved= (RadioButton) findViewById(R.id.ProblemSolvedradioButton);
        NoResponse =(RadioButton) findViewById(R.id.NoResponseradioButton);
        ratingBar= (RatingBar) findViewById(R.id.ratingBar);
        DaysEditText=(EditText) findViewById(R.id.NumOfDays);
        ratingSubmissionButton=(Button) findViewById(R.id.ratingSubmissionButton);
        ratingBar.setOnRatingBarChangeListener(
                new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                        Rating=rating;
                    }
                }
        );
        ratingSubmissionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final int days= Integer.parseInt(DaysEditText.getText().toString());
            }
        });


    }
}
