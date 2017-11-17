package com.example.kushagra.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class complaints extends AppCompatActivity {
    public Button button;
    public EditText Complaint;
    public EditText ComplaintType;
    public EditText ComplaintLocation;
    public EditText ComplaintDetails;
    private DatabaseReference DatabaseComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaints);
        Button button=(Button) findViewById(R.id.SubmitButton);
        final EditText Complaint= (EditText)findViewById(R.id.complaint);
        final EditText ComplaintType= (EditText)findViewById(R.id.complaintType);
        final EditText ComplaintLocation= (EditText)findViewById(R.id.complaintLocation);
        final EditText ComplaintDetails= (EditText)findViewById(R.id.complaintDetails);
        DatabaseComplaints=FirebaseDatabase.getInstance().getReference("Complaints");
        final Toast toast=Toast.makeText(this,"Submitted",Toast.LENGTH_LONG);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String complaintType = ComplaintType.getText().toString();
                String complaint = Complaint.getText().toString();
                String complaintLocation = ComplaintLocation.getText().toString();
                String complaintDetails = ComplaintDetails.getText().toString();
                String complaintID = DatabaseComplaints.push().getKey();
                ComplaintInformation information= new ComplaintInformation(complaintType,complaintDetails,complaintLocation,complaint);
                DatabaseComplaints.child(complaintID).setValue(information);
                toast.show();

            }



            });}}

      /*  public void AddComplaints(){
        String complaintType = ComplaintType.getText().toString();
        String complaint = Complaint.getText().toString();
        String complaintLocation = ComplaintLocation.getText().toString();
        String complaintDetails = ComplaintDetails.getText().toString();
        if(!TextUtils.isEmpty(complaintLocation)){
            String complaintID = DatabaseComplaints.push().getKey();
            AccessingComplaints access = new AccessingComplaints(complaintID,complaintDetails,complaintLocation,complaint);
            DatabaseComplaints.child(complaintID).setValue(access);
            Toast.makeText(this,"Complaint Submitted",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Enter Complaint Location",Toast.LENGTH_LONG).show();
        }

    }
    }

 class AccessingComplaints {
    String ComplaintID;
    String ComplaintDetails;
    String ComplaintLocation;
    String Complaint;
    public AccessingComplaints(){

    }




    public AccessingComplaints(String complaintID, String complaintDetails, String complaintLocation, String complaint) {
        ComplaintID = complaintID;
        ComplaintDetails = complaintDetails;
        ComplaintLocation = complaintLocation;
        Complaint = complaint;
    }

    public String getComplaintID() {
        return ComplaintID;
    }

    public String getComplaintDetails() {
        return ComplaintDetails;
    }

    public String getComplaintLocation() {
        return ComplaintLocation;
    }

    public String getComplaint() {
        return Complaint;
    }
}
*/