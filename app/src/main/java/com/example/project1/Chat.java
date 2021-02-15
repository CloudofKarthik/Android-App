package com.example.project1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Chat extends AppCompatActivity {

    TextView txt9;
    EditText txtmsg;
    Button btn3,refb;
    DatabaseReference reffc,reffc2;
    Table_chat table_chat;
    long maxc=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle bunn = getIntent().getExtras();
        String namepre = bunn.getString("stuff3");
        txt9=(TextView)findViewById(R.id.textView9);
        txtmsg=(EditText)findViewById(R.id.txtchat);
        btn3=(Button)findViewById(R.id.button3);
        refb=(Button)findViewById(R.id.button4);
        table_chat = new Table_chat();
        reffc= FirebaseDatabase.getInstance().getReference().child("Table_chat");

        reffc.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxc=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cha=txtmsg.getText().toString().trim();
                table_chat.setName_chat(namepre);
                table_chat.setChat_details(cha);
                reffc.child(String.valueOf(maxc+1)).setValue(table_chat);
                txtmsg.setText("");
                Toast.makeText(Chat.this, "Sent",Toast.LENGTH_LONG).show();

            }
        });
        refb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int t=1;
                txt9.setText("");
                for(t=1;t<=maxc;t++)
                {
                    reffc2=reffc.child(Integer.toString(t));
                    reffc2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String namech = snapshot.child("name_chat").getValue().toString();
                            String chats = snapshot.child("chat_details").getValue().toString();
                            txt9.append(namech + " : " + chats + "\n");
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
    }
}