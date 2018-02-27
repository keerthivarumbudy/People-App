package com.example.kushagra.first;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    FirebaseAuth auth;

    FirebaseUser user;
    EditText oldpass;
    EditText newpass;
    EditText newpass2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        auth=FirebaseAuth.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        oldpass=(EditText)findViewById(R.id.editText6);
        button=(Button)findViewById(R.id.button9);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();

            }
        });


    }
    public void change()
    {
        final String email="saurovsarkar4@gmail.com";
        AuthCredential credential= EmailAuthProvider.getCredential(email,oldpass.getText().toString());

     user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
         @Override
         public void onComplete(@NonNull Task<Void> task) {
             if (task.isSuccessful()) {


                 if (!newpass.equals(newpass2)) {
                     Toast.makeText(getApplicationContext(), "Please Confirm Password again!!", Toast.LENGTH_SHORT).show();
                     newpass2.setText("");

                 } else {
                     user.updatePassword(newpass.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if (task.isSuccessful()) {
                                 Toast.makeText(getApplicationContext(), "Password Changed!!", Toast.LENGTH_SHORT).show();
                                 Intent intent1 = new Intent(ChangePassword.this, ComplaintsMenu.class);


                                 ChangePassword.this.startActivity(intent1);
                             } else
                             {Toast.makeText(getApplicationContext(), "Updation Failed!!", Toast.LENGTH_SHORT).show();
                             }
                         }
                     });
                 }
             } else {
                 Toast.makeText(getApplicationContext(), "Authentication Failed!!", Toast.LENGTH_SHORT).show();

                 newpass.setText("");
                 oldpass.setText("");
                 newpass2.setText("");


             }
         }
     });


}

    }
