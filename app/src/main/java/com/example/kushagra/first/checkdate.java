package com.example.kushagra.first;

import android.icu.text.RelativeDateTimeFormatter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class checkdate extends AppCompatActivity {
    private EditText e;
    private EditText e1;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdate);
        b=(Button)findViewById(R.id.button7);
        e=(EditText)findViewById(R.id.editText2);
        e1=(EditText)findViewById(R.id.editText4);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                days();
            }
        });
    }
    public  void days()
    {
        String s1=e1.getText().toString();
        String s=e.getText().toString();

    }
}
