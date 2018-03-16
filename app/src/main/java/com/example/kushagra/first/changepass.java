package com.example.kushagra.first;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class changepass extends AppCompatActivity {
    private FirebaseUser mauth;
    private EditText nwpass;
    private EditText cnwpass;
    private Button change;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepass);
        nwpass = (EditText) findViewById(R.id.editText5);
        cnwpass = (EditText) findViewById(R.id.editText6);
        change = (Button) findViewById(R.id.button8);
        mauth=FirebaseAuth.getInstance().getCurrentUser();
        progressDialog =new ProgressDialog(this);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chng();
            }
        });
    }

    public void chng() {
        String newpass = nwpass.getText().toString();
        String cnewpass = cnwpass.getText().toString();
        if (TextUtils.isEmpty(newpass)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show();
            return;
        } else if (newpass.length() < 6) {
            Toast.makeText(this, "Password length should be more than 6 characters", Toast.LENGTH_LONG).show();
            return;
        } else if (TextUtils.isEmpty(cnewpass)) {
            Toast.makeText(this, "Please Confirm Password", Toast.LENGTH_LONG).show();
            return;
        } else {
            if (!newpass.equals(cnewpass)) {
                Toast.makeText(this, "Please Confirm Password again", Toast.LENGTH_LONG).show();
                return;
            } else {
                progressDialog.setMessage("changing...");
                progressDialog.show();
                mauth.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {   progressDialog.dismiss();
                            Intent intent1 = new Intent(changepass.this, update.class);


                            changepass.this.startActivity(intent1);
                            Toast.makeText(getApplicationContext(),"changed !",Toast.LENGTH_SHORT).show();

                        }
                        else
                        {Toast.makeText(getApplicationContext(),"failed !",Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();}
                    }
                });


            }


        }
    }
}
