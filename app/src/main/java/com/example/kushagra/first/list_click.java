package com.example.kushagra.first;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class list_click extends AppCompatActivity {

    public String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_click);
        EditText t= (EditText)findViewById(R.id.editText7);

        Intent intent=getIntent();
        x=intent.getStringExtra("x");
        t.setText(x);


    }
}
