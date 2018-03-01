package com.example.kushagra.first;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private Button cp;
    private EditText email;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        cp=(Button)findViewById(R.id.changepassword);
        email=(EditText)findViewById(R.id.cpemail);
        firebaseAuth=FirebaseAuth.getInstance();
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change();
            }
        });
        p=new ProgressDialog(this);
    }
    public void change()
    {
        String uemail=email.getText().toString().trim();
        p.setMessage("verifying....");
        p.show();
        firebaseAuth.sendPasswordResetEmail(uemail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful())
                {p.dismiss();
                    Toast.makeText(getApplicationContext(),"Password change email send!",Toast.LENGTH_LONG).show();
                    FirebaseAuth.getInstance().signOut();

                    sendToStart();
                }
                else
                {
                    p.dismiss();
                    Toast.makeText(getApplicationContext(),"Error sending email!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void sendToStart() {

        Intent k= new Intent(ForgotPassword.this,Login.class);
        startActivity(k);
        finish();
    }
}
