package com.example.kushagra.first;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class update extends AppCompatActivity {
    private EditText nemail ;
    private EditText phn;
    private EditText  name;
    private Button enter;
    private Button chpass;
    private DatabaseReference db;
    private FirebaseUser user;
    private String oldemail;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        nemail =(EditText)findViewById(R.id.editText3);
        chpass =(Button) findViewById(R.id.cp);
        name=(EditText)findViewById(R.id.name);
        phn=(EditText)findViewById(R.id.number);
        enter=(Button)findViewById(R.id.update);
        db= FirebaseDatabase.getInstance().getReference("User Information");

        user= FirebaseAuth.getInstance().getCurrentUser();
        oldemail=user.getEmail().toString();
        progressDialog=new ProgressDialog(this);





        chpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(update.this, changepass.class);


                update.this.startActivity(intent1);


            }
        });



        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                up();
            }
        });




    }
    public void up() {
        final String newemail = nemail.getText().toString().trim();
        String newname = name.getText().toString().trim();
        String phno = phn.getText().toString().trim();
        if (TextUtils.isEmpty(newname)) {
            Toast.makeText(this, "Enter User Name", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(newemail)) {
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(newemail).matches()) {
            Toast.makeText(this, "Enter valid Email Address", Toast.LENGTH_LONG).show();
            return;

        } else if (TextUtils.isEmpty(phno)) {
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_LONG).show();
            return;
        } else
            {

            final HashMap<String, Object> details = new HashMap<String, Object>();
            details.put("EmailId", newemail);
            details.put("Phone", phno);
            details.put("UserName", newname);

            db.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot i : dataSnapshot.getChildren()) {
                        String s = i.child("EmailId").getValue().toString();
                        if (s.equals(oldemail)) {
                            final String s1 = i.getKey().toString();
                            progressDialog.setMessage("Updating...");
                            progressDialog.show();
                            user.updateEmail(newemail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                   /* db.child(s1).setValue(ui).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isComplete())
                                            {
                                                Toast.makeText(getApplicationContext(),"Changed Successfully !",Toast.LENGTH_SHORT);
                                                Intent intent1 = new Intent(update.this, ComplaintsMenu.class);


                                                update.this.startActivity(intent1);
                                                progressDialog.dismiss();
                                            }
                                            else
                                                Toast.makeText(getApplicationContext(),"Failed !",Toast.LENGTH_SHORT);

                                        }
                                    });*/
                                        db.child(s1).updateChildren(details).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {


                                                    Toast.makeText(getApplicationContext(), "Changed Successfully !", Toast.LENGTH_SHORT).show();
                                                    Intent intent1 = new Intent(update.this, ComplaintsMenu.class);


                                                    update.this.startActivity(intent1);
                                                    progressDialog.dismiss();

                                                } else
                                                    Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                    }
                                }
                            });


                        }

                    }


                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
    }
}
