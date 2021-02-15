package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Buy_1 extends AppCompatActivity implements ImageAdapter.OnItemClickListener {
    private RecyclerView mRecyclerView;
    private  ImageAdapter mAdapter;

    private ProgressBar mProgressCircle;

    private DatabaseReference mDatabaseRef,dbref,dbref2;
    private List<Model> mUploads;
    TextView t27,t30;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_1);

        t27 = (TextView)findViewById(R.id.textView27);
        t30 = (TextView)findViewById(R.id.textView30);



        if(t27.getText().toString().equals(""))
        {



        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mProgressCircle = findViewById(R.id.progress_circle);


        Bundle buns = getIntent().getExtras();
        String name_b = buns.getString("stuff1");
        String pass_b = buns.getString("stuff2");
        Long contact = buns.getLong("stuff6");


        t27.setText(name_b);
        t30.setText(""+contact);

        t27.setVisibility(View.INVISIBLE);
        t30.setVisibility(View.INVISIBLE);


        mUploads = new ArrayList<>();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Image");

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Model model = postSnapshot.getValue(Model.class);
                    mUploads.add(model);
                }

                mAdapter = new ImageAdapter(Buy_1.this, mUploads);

                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(Buy_1.this);

                mProgressCircle.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Buy_1.this, "Error detected", Toast.LENGTH_SHORT).show();
                mProgressCircle.setVisibility(View.INVISIBLE);
            }
        });
    }}

    @Override
    public void onItemClick(int position) {


        int pos2 = position + 1;
        dbref = mDatabaseRef.child(Integer.toString(pos2));
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String s_name = snapshot.child("seller").getValue().toString();
                String s_pass = snapshot.child("seller_pwd").getValue().toString();
                int s_price = Integer.parseInt(snapshot.child("seller_price").getValue().toString());
                String s_url = snapshot.child("imageUrl").getValue().toString();
                String s_item = snapshot.child("seller_item").getValue().toString();
                Long s_contact =Long.parseLong(snapshot.child("seller_c").getValue().toString());
                String s_date = snapshot.child("seller_date").getValue().toString();
                int s_id = Integer.parseInt(snapshot.child("seller_id").getValue().toString());
                String s_fdate = snapshot.child("seller_finaldate").getValue().toString();
                String s_ftime = snapshot.child("seller_finaltime").getValue().toString();
                int pr_current = Integer.parseInt(snapshot.child("current_price").getValue().toString());

                open_buy2(s_name,s_pass,s_price,s_url,s_item,s_contact,s_date,s_id,s_fdate,s_ftime,pr_current,pos2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void open_buy2(String sname, String spass,int sprice, String surl, String sitem, Long scontact, String sdate, int sid,String sfdate,String sftime, int price_cur, int posit) {

        Intent inten = new Intent(this, Buy_2.class);
        Bundle b = new Bundle();
        String name1 = t27.getText().toString().trim();
        Long contact1 = Long.parseLong(t30.getText().toString().trim());

        b.putString("stuffa", sname);
        b.putString("stuffb", spass);
        b.putInt("stuffc", sprice);
        b.putString("stuffd", surl);
        b.putString("stuffe", sitem);
        b.putLong("stufff", scontact);
        b.putString("stuffg", sdate);
        b.putInt("stuffh", sid);
        b.putString("stuffi", sfdate);
        b.putString("stuffj", sftime);
        b.putInt("stuffk",price_cur);
        b.putInt("stuffl", posit);
        b.putString("stuffm", name1);
        b.putLong("stuffn", contact1);
        inten.putExtras(b);
        startActivity(inten);

    }

}