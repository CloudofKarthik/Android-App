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

public class MainActivity extends AppCompatActivity {
    EditText name,pwd;
    Button btn2,btn3;
    Table_login table_login;
    DatabaseReference refer,reff2;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.nametxt);
        pwd=(EditText)findViewById(R.id.passtxt);
        btn2=(Button) findViewById(R.id.createbtn);
        btn3=(Button) findViewById(R.id.submitbtn);
        table_login=new Table_login();

        refer = FirebaseDatabase.getInstance().getReference().child("Table_login");
        refer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxid=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup();
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name_log = name.getText().toString().trim();
                String pass_log = pwd.getText().toString().trim();
                int i=1;


                int k=1;
                for(i=1;i<=maxid;i++)
                {
                    reff2=refer.child(Integer.toString(i));
                    int finalI = i;
                    reff2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String name1 = snapshot.child("name").getValue().toString();
                            String pwd1 = snapshot.child("password").getValue().toString();
                            Long con = Long.parseLong(snapshot.child("phone").getValue().toString());
                            if (name_log.equals(name1) && pass_log.equals(pwd1))
                            {
                                Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_LONG).show();
                                openafterlogin(name_log,pass_log,con);

                            }
                            else if(finalI ==maxid)
                            {

                            }
                            else {

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }
            }
        });
    }
    public void openSignup(){
        Intent intent = new Intent(this, Sign_up.class);
        startActivity(intent);
    }

    public void openafterlogin(String namel, String passl,Long cont){

        Intent inten = new Intent(this, Afterlogin.class);
        Bundle b = new Bundle();
        b.putString("stuff1", namel);
        b.putString("stuff2", passl);
        b.putLong("stuff3", cont);
        inten.putExtras(b);
        startActivity(inten);
        this.finish();
    }

    @Override
    public void onBackPressed()
    {

    }

}