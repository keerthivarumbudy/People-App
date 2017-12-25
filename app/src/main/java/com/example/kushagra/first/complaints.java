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

    private DatabaseReference DatabaseComplaints1;
    private DatabaseReference DatabaseComplaints2;
    private DatabaseReference DatabaseComplaints3;
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
        final EditText ComplaintWard=(EditText)findViewById(R.id.editText);
        DatabaseComplaints1=FirebaseDatabase.getInstance().getReference("Complaints_keerthi_ward1");
        DatabaseComplaints2=FirebaseDatabase.getInstance().getReference("Complaints_kush_ward2");
        DatabaseComplaints3=FirebaseDatabase.getInstance().getReference("Complaints_sarkar_ward3");
        final Toast toast1=Toast.makeText(this,"Submitted to Keerthi",Toast.LENGTH_LONG);
        final Toast toast2=Toast.makeText(this,"Submitted to Kushagra",Toast.LENGTH_LONG);
        final Toast toast3=Toast.makeText(this,"Submitted to Saurov",Toast.LENGTH_LONG);
        final Toast toast4=Toast.makeText(this,"Please enter ward 1,2 or 3",Toast.LENGTH_LONG);


        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String complaintType = ComplaintType.getSelectedItem().toString();
                String complaint = Complaint.getText().toString();
                String complaintLocation = ComplaintLocation.getText().toString();
                String complaintDetails = ComplaintDetails.getText().toString();
                String complaintWard= ComplaintWard.getText().toString();
                int foo = Integer.parseInt(complaintWard);
                if(foo==1)
                {

                    String complaintID1 = DatabaseComplaints1.push().getKey();
                    String complaintTime=new SimpleDateFormat("HH:mm").format(CurrentTime);
                    ComplaintInformation information= new ComplaintInformation(complaintTime,currentDate,complaintType,complaintDetails,complaintLocation,complaint);
                    DatabaseComplaints1.child(complaintID1).setValue(information);
                    toast1.show();
                }
                else if(foo==2)
                {

                    String complaintID2 = DatabaseComplaints2.push().getKey();
                    String complaintTime=new SimpleDateFormat("HH:mm").format(CurrentTime);
                    ComplaintInformation information= new ComplaintInformation(complaintTime,currentDate,complaintType,complaintDetails,complaintLocation,complaint);
                    DatabaseComplaints2.child(complaintID2).setValue(information);
                    toast2.show();
                }
                else if(foo==3)
                {

                    String complaintID3 = DatabaseComplaints3.push().getKey();
                    String complaintTime=new SimpleDateFormat("HH:mm").format(CurrentTime);
                    ComplaintInformation information= new ComplaintInformation(complaintTime,currentDate,complaintType,complaintDetails,complaintLocation,complaint);
                    DatabaseComplaints3.child(complaintID3).setValue(information);
                    toast3.show();
                }
                else
                {
                    toast4.show();
                }

            }



            });}}

