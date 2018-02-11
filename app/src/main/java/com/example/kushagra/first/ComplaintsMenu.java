package com.example.kushagra.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ComplaintsMenu extends AppCompatActivity{
    private Button button1;
    private Toolbar mToolbar;
    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complaints_menu);
        button1= (Button) findViewById(R.id.button1);
        mAuth= FirebaseAuth.getInstance();


        button1.setOnClickListener(new View.OnClickListener(){
    public void onClick(View v){
        Intent intent1 = new Intent(ComplaintsMenu.this, complaints.class);


        ComplaintsMenu.this.startActivity(intent1);



    }});

    mToolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
    setSupportActionBar(mToolbar);
    getSupportActionBar().setTitle("First");


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser= mAuth.getCurrentUser();
        if(currentUser==null){
            sendToStart();
        }
    }

    private void sendToStart() {

        Intent k= new Intent(ComplaintsMenu.this,Login.class);
        startActivity(k);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.main_logout_button){
            FirebaseAuth.getInstance().signOut();

            sendToStart();
        }

        return true;
    }

    @Override
    public void onBackPressed() {

    }
}
