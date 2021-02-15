package com.example.project1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Buy_2 extends AppCompatActivity {

    TextView txt17,txt18,txt23,txt25,txt28;
    ImageView img1;
    DatabaseReference dbreff,dbreff2;
    int current_itemprice = 0,item_status,it_status;
    String bid_date, bid_time;
    EditText your_bid;
    Button submit_button, logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_2);



        Bundle bun = getIntent().getExtras();
        String item_prev = bun.getString("stuffe");
        String img_prev = bun.getString("stuffd");
        int price_prev = bun.getInt("stuffc");
        String seller_prev = bun.getString("stuffa");
        Long contact_prev = bun.getLong("stufff");
        int id_prev = bun.getInt("stuffh");
        String date_prev = bun.getString("stuffg");
        String pass_prev = bun.getString("stuffb");
        String final_date = bun.getString("stuffi");
        String final_time = bun.getString("stuffj");
        int item_position = bun.getInt("stuffl");

        String b_name = bun.getString("stuffm");
        Long b_con = bun.getLong("stuffn");

        your_bid = (EditText)findViewById(R.id.editTextNumber);
        submit_button = (Button)findViewById(R.id.button9);
        logout_button = (Button)findViewById(R.id.button6);

        dbreff = FirebaseDatabase.getInstance().getReference().child("Image");
        dbreff2=dbreff.child(Integer.toString(item_position));


        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();
        String today = df.format(dateobj);
        Date datetoday= null;
        Date datethatday = null;
        SimpleDateFormat ti = new SimpleDateFormat("HH:mm");
        Date timeobj = new Date();
        String timenow = ti.format(timeobj);
        Date timetoday = null;
        Date timethatday = null;

        try {
            timetoday = new SimpleDateFormat("HH:mm").parse(timenow);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            timethatday = new SimpleDateFormat("HH:mm").parse(final_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            datetoday = new SimpleDateFormat("dd/MM/yyyy").parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            datethatday = new SimpleDateFormat("dd/MM/yyyy").parse(final_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        int comparisond=datethatday.compareTo(datetoday);
        int comparisont=timethatday.compareTo(timetoday);

        int it_st;

        if (comparisond<0) {
            it_st = 0;
            int k = 0;
            dbreff2.child("seller_status").setValue(k);
            Toast.makeText(this, "Item Not Available", Toast.LENGTH_SHORT).show();

        }

        else if (comparisond==0) {
            if (comparisont <0) {
                it_st = 0;
                int l = 0;
                dbreff2.child("seller_status").setValue(l);
                Toast.makeText(this, "Item Not Available", Toast.LENGTH_SHORT).show();

            }
        }


        dbreff2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                current_itemprice = Integer.parseInt(snapshot.child("current_price").getValue().toString());
                item_status = Integer.parseInt(snapshot.child("seller_status").getValue().toString());
                bid_date = snapshot.child("seller_finaldate").getValue().toString();
                bid_time = snapshot.child("seller_finaltime").getValue().toString();

                txt18 = (TextView)findViewById(R.id.textView18);
                txt23 = (TextView)findViewById(R.id.textView23);
                txt25 = (TextView)findViewById(R.id.textView25);
                txt28 = (TextView)findViewById(R.id.textView28);

                txt25.setText(bid_date);
                txt28.setText(bid_time);

                txt18.setText(""+current_itemprice);
                if(item_status==0){
                    txt23.setText("Unavailable");
                    your_bid.setEnabled(false);
                    String b = snapshot.child("buyer_name").getValue().toString();
                    Long c = Long.parseLong(snapshot.child("buyer_ph").getValue().toString());

                    if(b.equals(b_name) && c.equals(b_con)){
                        openfinal(contact_prev,id_prev);
                    }
                }
                if(item_status==1){
                    txt23.setText("Available");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        txt17 = (TextView)findViewById(R.id.textView17);
        txt17.setText("Product - "+ item_prev);

        img1 = (ImageView)findViewById(R.id.imageView);

        Picasso.get().load(img_prev).fit().centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(img1);

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openstart();
            }
        });

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {








                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Date dateobj = new Date();
                String today = df.format(dateobj);
                Date datetoday= null;
                Date datethatday = null;
                SimpleDateFormat ti = new SimpleDateFormat("HH:mm");
                Date timeobj = new Date();
                String timenow = ti.format(timeobj);
                Date timetoday = null;
                Date timethatday = null;

                try {
                    timetoday = new SimpleDateFormat("HH:mm").parse(timenow);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    timethatday = new SimpleDateFormat("HH:mm").parse(final_time);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    datetoday = new SimpleDateFormat("dd/MM/yyyy").parse(today);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                try {
                    datethatday = new SimpleDateFormat("dd/MM/yyyy").parse(final_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                int comparisond=datethatday.compareTo(datetoday);
                int comparisont=timethatday.compareTo(timetoday);

                int it_st;

                if (comparisond<0) {
                    it_st = 0;
                    int k = 0;
                    dbreff2.child("seller_status").setValue(k);

                }

                else if (comparisond==0) {
                    if (comparisont <0) {
                        it_st = 0;
                        int l = 0;
                        dbreff2.child("seller_status").setValue(l);

                    }
                }








                dbreff2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        it_status = Integer.parseInt(snapshot.child("seller_status").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



                if (it_status ==1) {
                int price_new = Integer.parseInt(your_bid.getText().toString());
                int price_old = Integer.parseInt(txt18.getText().toString());


                if(price_new<=price_old){
                    Toast.makeText(Buy_2.this, "Enter a higher price to bid the item", Toast.LENGTH_SHORT).show();
                }

                else if(price_new>price_old){
                    dbreff2.child("current_price").setValue(price_new);

                    dbreff2.child("buyer_name").setValue(b_name);
                    dbreff2.child("buyer_ph").setValue(b_con);

                    openself();}
                }
            }
        });
    }

    public void openfinal(Long co_prev, int i_prev)
    {
        Intent in = new Intent(this, Final.class);

        Bundle b3 = new Bundle();
        b3.putLong("stuff11", co_prev);
        b3.putInt("stuff12", i_prev);
        in.putExtras(b3);

        startActivity(in);
        this.finish();
    }

    public void openstart()
    {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        this.finish();
    }

    public void openself() {
        Intent intb = new Intent(this, Buy_2.class);
        startActivity(intb);
        this.finish();
    }

    @Override
    public void onBackPressed()
    {

    }

}