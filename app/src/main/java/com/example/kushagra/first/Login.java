package com.example.kushagra.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Login extends AppCompatActivity {
    private Button LoginButton;
    private Button New;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoginButton= (Button) findViewById(R.id.Login);
        New= (Button) findViewById(R.id.newSignUp);


            LoginButton.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent1 = new Intent(Login.this, ComplaintsMenu.class);

                    Login.this.startActivity(intent1);


    }
});
        New.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent1 = new Intent(Login.this, SignUp.class);

                Login.this.startActivity(intent1);


            }
        });
            }
}
