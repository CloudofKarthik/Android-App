package com.example.project1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.sql.DatabaseMetaData;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Sell_1 extends AppCompatActivity {
    TextView t11,t13,t21,t22;
    Button uploadBtn,submitbtn;
    ImageView imageView;
    EditText ph_txt, item_txt, price_txt;
    ProgressBar progressBar;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().child("Image");
    StorageReference re = FirebaseStorage.getInstance().getReference();
    Uri imageUri;
    long temp=0;
    Model model;
    int year,month,day;
    int thour,tminute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_1);

        Bundle buns = getIntent().getExtras();
        String name_a = buns.getString("stuff4");
        String pass_a = buns.getString("stuff5");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dt = formatter.format(date);

        item_txt = (EditText)findViewById(R.id.sell_txt1);
        ph_txt = (EditText)findViewById(R.id.sell_txt3);
        price_txt = (EditText)findViewById(R.id.sell_txt2);
        t21 = (TextView)findViewById(R.id.textView21);
        t22 = (TextView)findViewById(R.id.textView22);
        final Calendar calendar = Calendar.getInstance();
        t21.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Sell_1.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String dom = Integer.toString(dayOfMonth);
                        if (dayOfMonth<10){
                            dom = "0" + Integer.toString(dayOfMonth);
                        }
                        else {
                            dom = Integer.toString(dayOfMonth);
                        }

                        if (month<10){
                        t21.setText(new StringBuilder().append(dom).append("/0").append(month).append("/").append(year));
                        }
                        else {
                            t21.setText(new StringBuilder().append(dom).append("/").append(month).append("/").append(year));
                        }
                    }
                }, year,month, day);
                datePickerDialog.show();
            }
        });

        t22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        Sell_1.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                thour = hourOfDay;
                                tminute = minute;
                                String time = thour + ":" + tminute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);

                                    SimpleDateFormat f24hours = new SimpleDateFormat(
                                            "HH:mm"
                                    );
                                    t22.setText(f24hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(thour,tminute);
                timePickerDialog.show();

            }
        });

        t13 =(TextView)findViewById(R.id.textView13);
        t11 = (TextView)findViewById(R.id.textView11);
        t11.setText(name_a);

        uploadBtn = findViewById((R.id.upload_btn));
        submitbtn = findViewById((R.id.button7));
        progressBar = findViewById((R.id.progressBar));
        imageView = findViewById((R.id.imageView6));

        progressBar.setVisibility(View.INVISIBLE);

        model = new Model();

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    temp=(snapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent , 2);

            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                }
                else {
                    Toast.makeText(Sell_1.this, "Please Select Image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long phone_no = Long.parseLong(ph_txt.getText().toString().trim());
                String item_name = item_txt.getText().toString().trim();
                String finaldate = t21.getText().toString().trim();
                String finaltime = t22.getText().toString().trim();
                int item_price = Integer.parseInt(price_txt.getText().toString().trim());
                int price_current = item_price;
                int stat=1;
                int i = new Random().nextInt(900000) + 100000;
                String url_full = t13.getText().toString().trim();
                model.setSeller(name_a);
                model.setSeller_c(phone_no);
                model.setSeller_item(item_name);
                model.setSeller_pwd(pass_a);
                model.setSeller_price(item_price);
                model.setSeller_id(i);
                model.setImageUrl(url_full);
                model.setSeller_date(dt);
                model.setSeller_finaldate(finaldate);
                model.setSeller_finaltime(finaltime);
                model.setCurrent_price(price_current);
                model.setSeller_status(stat);
                model.setBuyer_name(name_a);
                model.setBuyer_ph(phone_no);

                root.child(String.valueOf(temp)).setValue(model);

                Toast.makeText(Sell_1.this, "Product successfully added",Toast.LENGTH_LONG).show();
                openbuyorsell();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==2 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }

    }
    private void uploadToFirebase(Uri uri){

        StorageReference fileRef = re.child(System.currentTimeMillis() + "." +getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        Model model = new Model(uri.toString());
                        String modelId = root.push().getKey();
                        t13.setText(uri.toString());

                        root.child(String.valueOf(temp+1)).setValue(model);
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(Sell_1.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                progressBar.setVisibility(View.VISIBLE);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(Sell_1.this, "Uploading Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }

    public void openbuyorsell() {
        Intent intent = new Intent(this, Afterlogin.class);
        startActivity(intent);
        this.finish();
    }

}