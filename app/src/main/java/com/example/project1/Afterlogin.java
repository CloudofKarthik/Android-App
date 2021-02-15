package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class Afterlogin extends AppCompatActivity {

    TextView txt7,txt8;
    Button btn2,buybtn,sellbtn, logoutbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afterlogin);
        Bundle bun = getIntent().getExtras();
        String nameprev = bun.getString("stuff1");
        String passprev = bun.getString("stuff2");
        Long conta = bun.getLong("stuff3");
        txt7 = (TextView)findViewById(R.id.textView7);
        txt7.setText("Welcome "+ nameprev);
        sellbtn=(Button)findViewById(R.id.button8);
        buybtn=(Button)findViewById(R.id.button5);
        logoutbutton = (Button)findViewById(R.id.button11);
        btn2=(Button)findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_chat(nameprev);
            }
        });

        buybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_buy(nameprev,passprev,conta);
            }
        });

        sellbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_sell(nameprev,passprev);
            }
        });

        logoutbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open_start();
            }
        });

    }

    public void open_chat(String name) {

        Intent inten = new Intent(this, Chat.class);
        Bundle b = new Bundle();
        b.putString("stuff3", name);
        inten.putExtras(b);
        startActivity(inten);
    }

    public void open_buy(String nameb, String passb, Long contac) {

        Intent intb = new Intent(this, Buy_1.class);
        Bundle b1 = new Bundle();
        b1.putString("stuff1", nameb);
        b1.putString("stuff2", passb);
        b1.putLong("stuff6",contac);
        intb.putExtras(b1);
        startActivity(intb);
    }

    public void open_sell(String names, String passs) {
        Intent ints = new Intent(this, Sell_1.class);
        Bundle b2 = new Bundle();
        b2.putString("stuff4", names);
        b2.putString("stuff5", passs);
        ints.putExtras(b2);
        startActivity(ints);
    }

    public void open_start()
    {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        this.finish();
    }

    @Override
    public void onBackPressed()
    {

    }


}