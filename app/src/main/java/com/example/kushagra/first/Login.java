package com.example.kushagra.first;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Login extends AppCompatActivity {
    private Button LoginButton;
    private Button New;
    private FirebaseAuth mAuth;
    private EditText Email;
    private EditText Password;
    private ProgressBar ProgressBar;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton = (Button) findViewById(R.id.Login);
        New = (Button) findViewById(R.id.newSignUp);
        mAuth = FirebaseAuth.getInstance(); //Authentication getting instance
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        ProgressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              // login();
                Intent intent1 = new Intent(Login.this, ComplaintsMenu.class);

                Login.this.startActivity(intent1);
            }
        });
        New.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent1 = new Intent(Login.this, SignUp.class);

                Login.this.startActivity(intent1);


            }
        });}


    private void login() {
        email = Email.getText().toString().trim();
        password = Password.getText().toString().trim();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            Intent intent1 = new Intent(Login.this, ComplaintsMenu.class);

                            Login.this.startActivity(intent1);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Login failed.",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // ...
                    }

                });


    }
}

