package com.example.kushagra.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class ComplaintsMenu extends Activity{
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaints_menu);
        button1= (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v){
        Intent intent1 = new Intent(ComplaintsMenu.this, complaints.class);


        ComplaintsMenu.this.startActivity(intent1);
    }});

    }}
