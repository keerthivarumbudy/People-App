package com.example.kushagra.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.kushagra.first.R.id.hello;

public class Login extends AppCompatActivity {
    private TextView hello;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            hello= (TextView) findViewById(R.id.hello);
            hello.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent1 = new Intent(Login.this, ComplaintsMenu.class);

                    Login.this.startActivity(intent1);


    }
});}
}
