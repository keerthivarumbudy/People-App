package com.example.kushagra.first;

import android.app.Activity;

import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends Activity {

    private Button Submit;
    private EditText UserName;
    private EditText Email;
    private EditText Phone;
    private EditText Password;
    private EditText ConfirmPassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Submit = (Button) findViewById(R.id.Submit);
        UserName = (EditText) findViewById(R.id.UserName);
        Email = (EditText) findViewById(R.id.Email);
        Phone = (EditText) findViewById(R.id.Phone);
        Password = (EditText) findViewById(R.id.Password);
        ConfirmPassword = (EditText) findViewById(R.id.ConfirmPassword);
        mAuth = FirebaseAuth.getInstance();

        Submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                RegisterUser();
            }
        });}


    private void RegisterUser() {
        String userName = UserName.getText().toString().trim();
        String email= Email.getText().toString().trim();
        String phone= Phone.getText().toString().trim();
        String password= Password.getText().toString().trim();
        String confirmPassword= ConfirmPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Enter User Name", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Email Address", Toast.LENGTH_LONG).show();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(this, "Enter valid Email Address", Toast.LENGTH_LONG).show();
            return;

        }
        else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_LONG).show();
            return;
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG).show();
            return;
        }
        else if(password.length()<6){
            Toast.makeText(this, "Password length should be more than 6 characters", Toast.LENGTH_LONG).show();
            return;
        }
        else if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(this, "Please Confirm Password", Toast.LENGTH_LONG).show();
            return;
        }
        else {
            if (!password.equals(confirmPassword)) {
                Toast.makeText(this, "Please Confirm Password again", Toast.LENGTH_LONG).show();
                return;
            }
            else {
                //Create User with Email and Password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    //GO TO COMPLAINT PAGE

                                    Toast.makeText(SignUp.this, "Sign up successful.", Toast.LENGTH_LONG).show();
                                }
                                    else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(SignUp.this, "You are already registered", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_LONG).show();
                                        return;
                                    }
                                }

                                // ...
                            }
                        });

            }
        }
    }
}
