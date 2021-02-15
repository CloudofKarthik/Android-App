package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Final extends AppCompatActivity {

    TextView t31,t32;
    Button b10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        t31 = (TextView)findViewById(R.id.textView31);
        t32 = (TextView)findViewById(R.id.textView32);
        b10 = (Button)findViewById(R.id.button10);

        Bundle bun1 = getIntent().getExtras();
        Long conprev = bun1.getLong("stuff11");
        int idprev = bun1.getInt("stuff12");

        t31.setText("The ID of the item you have purchased is - "+idprev+". Please dont share it.");
        t32.setText("Please contact - "+conprev+" for availing the item");


        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openstart();
            }
        });

    }

    @Override
    public void onBackPressed()
    {

    }

    public void openstart()
    {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        this.finish();
    }

}