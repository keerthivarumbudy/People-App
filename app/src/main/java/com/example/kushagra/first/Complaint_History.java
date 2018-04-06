package com.example.kushagra.first;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Complaint_History extends AppCompatActivity {

    databaseHelper myDb;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint__history);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        final String email= user.getEmail();


        ListView listView= (ListView)findViewById(R.id.listView);
        myDb = new databaseHelper(this);

        ArrayList<String> list = new ArrayList<>();

        final ArrayList<String> list2= new ArrayList<>();

        final Cursor data= myDb.getListContents(email);

        if(data.getCount()==0){
            Toast.makeText(Complaint_History.this,"the database is empty",Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                list.add(data.getString(1));
                list2.add(data.getString(2));
                //final String ke= data.getString(2);
                ListAdapter listAdapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
                listView.setAdapter(listAdapter);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent= new Intent(Complaint_History.this,list_click.class);

                        intent.putExtra("x",list2.get(position));
                        startActivity(intent);


                    }
                });
            }
        }




    }
}
