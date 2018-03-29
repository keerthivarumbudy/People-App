package com.example.kushagra.first;

import android.*;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class complaints extends AppCompatActivity {

    private DatabaseReference DatabaseComplaints1;
    private DatabaseReference DatabaseComplaints2;
    private DatabaseReference DatabaseComplaints3;
    public int count;
    public gpslocation gp;
    private Double longi;
    private Double latt;
    private Float dis;
    private ProgressDialog pro;
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
        final Toast toast5=Toast.makeText(this,"Please enter ward 1,2 or 3 Correctly!!",Toast.LENGTH_LONG);
        final Toast toast6=Toast.makeText(this,"NOT UNDER ANY AUTHORITY !",Toast.LENGTH_LONG);
        final Toast toast7=Toast.makeText(this,"Try Again !",Toast.LENGTH_LONG);
        ActivityCompat.requestPermissions(complaints.this,new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},123);
        gp=new gpslocation(getApplicationContext());
        pro=new ProgressDialog(this);


        button.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                String complaintType = ComplaintType.getSelectedItem().toString();
                String complaint = Complaint.getText().toString();
                String complaintLocation = ComplaintLocation.getText().toString();
                String complaintDetails = ComplaintDetails.getText().toString();
                String complaintWard= ComplaintWard.getText().toString();
                if (complaintWard.isEmpty())
                {
                    toast4.show();
                }
                else{
                   pro.setMessage("Submitting...");
                    pro.show();

                    int foo = Integer.parseInt(complaintWard);

                    Location l=gp.getlocation();

                    if (l!=null)
                    {
                        latt=l.getLatitude();
                        longi=l.getLongitude();

                        Location l1=new Location("");
                        Location l2=new Location("");
                        l1.setLatitude(latt);
                        l1.setLongitude(longi);
                        l2.setLatitude(12.31183258);
                        l2.setLongitude(76.61282032);
                         dis=l1.distanceTo(l2);


                        if(dis<2000)
                        {
                            if(foo!=1)
                            {toast5.show();
                            foo=4;
                            }


                       }
                       else if (dis>2000)
                        {
                            l2.setLatitude(12.311827);
                            l2.setLongitude(76.652985);
                            dis=l1.distanceTo(l2);
                            if(dis<2000)
                            {
                                if(foo!=2)
                                {toast5.show();
                                    foo=4;}


                            }
                            else if(dis>2000)
                                {
                                l2.setLatitude(12.304202);
                                l2.setLongitude(76.632607);
                                dis=l1.distanceTo(l2);
                                if(dis<2000)
                                {pro.dismiss();
                                    if(foo!=3)
                                    {toast5.show();
                                        foo=4;}


                                }

                            }
                            else {
                                toast6.show();
                                foo=4;

                                   }
                            {

                            }
                        }






                    }
                    else {toast7.show();
                    foo=4;}


                if(foo==1)
                {

                   final String complaintID1 = DatabaseComplaints1.push().getKey();
                    String complaintTime=new SimpleDateFormat("HH:mm").format(CurrentTime);
                    ComplaintInformation information= new ComplaintInformation(complaintTime,currentDate,complaintType,complaintDetails,complaintLocation,complaint);
                    DatabaseComplaints1.child(complaintID1).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                             pro.dismiss();
                            toast1.show();
                            count++;
                            AlertDialog.Builder builder= new AlertDialog.Builder(complaints.this);
                            builder.setMessage("Complaint sent  to Keerthi. Complaint id is "+ complaintID1.toString())
                                    .setPositiveButton("Ok", null);

                            AlertDialog alert= builder.create();
                            alert.show();

                        }
                    });


                }
                else if(foo==2)
                {

                    String complaintID2 = DatabaseComplaints2.push().getKey();
                    String complaintTime=new SimpleDateFormat("HH:mm").format(CurrentTime);
                    ComplaintInformation information= new ComplaintInformation(complaintTime,currentDate,complaintType,complaintDetails,complaintLocation,complaint);
                    DatabaseComplaints2.child(complaintID2).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            pro.dismiss();
                            toast2.show();
                            count++;

                        }
                    });

                }
                else if(foo==3)
                {

                    String complaintID3 = DatabaseComplaints3.push().getKey();
                    String complaintTime=new SimpleDateFormat("HH:mm").format(CurrentTime);
                    ComplaintInformation information= new ComplaintInformation(complaintTime,currentDate,complaintType,complaintDetails,complaintLocation,complaint);
                    DatabaseComplaints3.child(complaintID3).setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            toast3.show();
                            pro.dismiss();
                            count++;

                        }
                    });

                }
                else if (foo==4)
                {
                    pro.dismiss();
                    /* time pass*/
                }
                else
                {
                    pro.dismiss();
                    toast4.show();
                }

            }}



            });

    latt=0.0;
    longi=0.0;
    }








}

