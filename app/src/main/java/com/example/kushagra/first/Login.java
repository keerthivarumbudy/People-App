package com.example.kushagra.first;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
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
    private FirebaseUser user;
    private EditText Email;
    private EditText Password;
    private ProgressDialog pro;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton = (Button) findViewById(R.id.Login);
        New = (Button) findViewById(R.id.newSignUp);
        mAuth = FirebaseAuth.getInstance(); //Authentication getting instance
        Email = (EditText) findViewById(R.id.loginEmail);
        Password = (EditText) findViewById(R.id.loginPassword);
        pro=new ProgressDialog(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() ,"Enter Email",Toast.LENGTH_SHORT);


               login();
              /*Intent intent1 = new Intent(Login.this, ComplaintsMenu.class);

                Login.this.startActivity(intent1);*/

        }});
        New.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent1 = new Intent(Login.this, SignUp.class);

            Login.this.startActivity(intent1);


        }
    });
}


    private void login() {

        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),"Enter Email",Toast.LENGTH_SHORT).show();
            return;

        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Toast.makeText(getApplicationContext(),"Enter valid email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(this,"Enter the password",Toast.LENGTH_SHORT).show();
            return;
        }
        pro.setMessage("Logging In...");
        pro.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information

                            Intent intent1 = new Intent(Login.this, ComplaintsMenu.class);

                            Login.this.startActivity(intent1);
                            pro.dismiss();

                        } else {
                            // If sign in fails, display a message to the user.


                            Toast.makeText(getApplicationContext(), "Check Email and Password !!",
                                    Toast.LENGTH_SHORT).show();
                            Email.setText("");
                            Password.setText("");
                            pro.dismiss();
                            return;
                        }

                        // ...
                    }

                });

    }
   @Override
    protected void onStart()
    {
        super.onStart();
        user=mAuth.getCurrentUser();
        if (user!=null)
        {
            startActivity(new Intent(Login.this,ComplaintsMenu.class));
        }

    }





}

