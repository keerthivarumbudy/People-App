package com.example.kushagra.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class complaints extends AppCompatActivity {

    private DatabaseReference DatabaseComplaints;
    ArrayAdapter<CharSequence>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        Button button=(Button) findViewById(R.id.SubmitButton);
        final EditText Complaint= (EditText)findViewById(R.id.complaint);
        //Spinner stuff
        final Spinner ComplaintType = (Spinner) findViewById(R.id.complaintType);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.complaintOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ComplaintType.setAdapter(adapter);
        //calender stuff
        Date now = new Date();
        final String currentDate = new SimpleDateFormat( "dd-MMM-yyyy").format(now);
        final Date CurrentTime = Calendar.getInstance().getTime();
        final EditText ComplaintLocation= (EditText)findViewById(R.id.complaintLocation);
        final EditText ComplaintDetails= (EditText)findViewById(R.id.complaintDetails);
        DatabaseComplaints=FirebaseDatabase.getInstance().getReference("Complaints");
        final Toast toast=Toast.makeText(this,"Submitted",Toast.LENGTH_LONG);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String complaintType = ComplaintType.getSelectedItem().toString();
                String complaint = Complaint.getText().toString();
                String complaintLocation = ComplaintLocation.getText().toString();
                String complaintDetails = ComplaintDetails.getText().toString();
                String complaintID = DatabaseComplaints.push().getKey();
                String complaintTime=new SimpleDateFormat("HH:mm").format(CurrentTime);
                ComplaintInformation information= new ComplaintInformation(complaintTime,currentDate,complaintType,complaintDetails,complaintLocation,complaint);
                DatabaseComplaints.child(complaintID).setValue(information);
                toast.show();

            }



            });}}

