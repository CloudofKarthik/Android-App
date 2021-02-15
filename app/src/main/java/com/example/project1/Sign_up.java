package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sign_up extends AppCompatActivity {

    EditText f1,f2,f3,f4;
    Button b1;
    DatabaseReference reff;
    Table_login table_login;
    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        f1=(EditText)findViewById(R.id.txtnm);
        f2=(EditText)findViewById(R.id.txtpw);
        f3=(EditText)findViewById(R.id.txtphn);
        f4=(EditText)findViewById(R.id.txte);
        b1=(Button)findViewById(R.id.button);
        table_login=new Table_login();
        reff= FirebaseDatabase.getInstance().getReference().child("Table_login");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxid=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=f1.getText().toString().trim();
                String pwd=f2.getText().toString().trim();
                Long phno=Long.parseLong(f3.getText().toString().trim());
                String emailid=f4.getText().toString().trim();

                table_login.setName(name);
                table_login.setPassword(pwd);
                table_login.setPhone(phno);
                table_login.setEmail(emailid);

                reff.child(String.valueOf(maxid+1)).setValue(table_login);
                Toast.makeText(Sign_up.this, "Successfully Registered",Toast.LENGTH_LONG).show();

                    openlogin();

            }
        });


    }
    public void openlogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}