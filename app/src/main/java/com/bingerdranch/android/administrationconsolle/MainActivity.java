package com.bingerdranch.android.administrationconsolle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bingerdranch.android.administrationconsolle.add_data.AllList;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

    private static final String LOG_TAG = "MyLogs";
    private EditText ed_text_name;
    private EditText edit_text_marka;
    private EditText edit_text_model;
    private EditText edit_text_okrug;
    private EditText edit_text_rayon;
    private EditText edit_text_metro;
    private EditText edit_text_adress;
    private EditText edit_text_number;
    private EditText edit_text_vid_rabot;
    private EditText edit_text_rating;
    private EditText edit_text_grafik_raboti;
    private EditText ed_site;

    private String name = "";
    private String marka = "";
    private String model = "";
    private String okrug = "";
    private String rayon = "";
    private String metro = "";
    private String adress = "";
    private String number = "";
    private String vid_rabot = "";
    private String ed_rating = "";
    private String num_of_otziv = "";
    private String grafik_raboti = "";
    private String site = "";

    private Button button_save;
    private Button button_clear;

    private ImageView imageView_information;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_text_name = (EditText) findViewById(R.id.ed_text_name);
        edit_text_marka = (EditText) findViewById(R.id.ed_text_marka);
        edit_text_model = (EditText) findViewById(R.id.ed_text_model);
        edit_text_okrug = (EditText) findViewById(R.id.ed_text_okrug);
        edit_text_rayon = (EditText) findViewById(R.id.ed_text_rayon);
        edit_text_metro = (EditText) findViewById(R.id.ed_text_metro);
        edit_text_adress = (EditText) findViewById(R.id.ed_text_adress);
        edit_text_number = (EditText) findViewById(R.id.ed_text_number);
        edit_text_vid_rabot = (EditText) findViewById(R.id.ed_text_vid_rabot);
        edit_text_rating = (EditText) findViewById(R.id.ed_rating);
        edit_text_grafik_raboti = (EditText) findViewById(R.id.ed_grafik_raboti);
        ed_site = (EditText) findViewById(R.id.ed_site);

        imageView_information = (ImageView) findViewById(R.id.image_view_information);

        button_save = (Button) findViewById(R.id.button_save);
        button_clear = (Button) findViewById(R.id.button_clear);

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ed_text_name.getText().toString().equals("")){
                    addDataToDataBase();
                }else{
                    Toast.makeText(MainActivity.this,"Введите название автосервиса",Toast.LENGTH_SHORT).show();
                }

            }
        });
        button_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearField();
            }
        });
        imageView_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                information();
            }
        });
        FirebaseApp.initializeApp(MainActivity.this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

    }

    private void information() {
        Intent intent = new Intent(MainActivity.this,Information.class);
        startActivity(intent);
    }

    void addDataToDataBase(){
        name = ed_text_name.getText().toString();
        marka = edit_text_marka.getText().toString();
        model = edit_text_model.getText().toString();
        okrug = edit_text_okrug.getText().toString();
        rayon = edit_text_rayon.getText().toString();
        metro = edit_text_metro.getText().toString();
        adress = edit_text_adress.getText().toString();
        number = edit_text_number.getText().toString();
        vid_rabot = edit_text_vid_rabot.getText().toString();
        ed_rating = edit_text_rating.getText().toString();
        grafik_raboti = edit_text_grafik_raboti.getText().toString();
        site = ed_site.getText().toString();
        //ed_rating = Integer.parseInt(edit_text_rating.getText().toString());

        if (site.equals("")){
            Data data = new Data(name,marka,model,okrug,rayon,metro,adress,number,vid_rabot,"",Integer.parseInt(ed_rating),num_of_otziv,grafik_raboti);
            mDatabaseReference = mFirebaseDatabase.getReference().child(ed_text_name.getText().toString());
            mDatabaseReference.setValue(data);
        }else{
            Data data = new Data(site,name,marka,model,okrug,rayon,metro,adress,number,vid_rabot,"",Integer.parseInt(ed_rating),num_of_otziv,grafik_raboti);
            mDatabaseReference = mFirebaseDatabase.getReference().child(ed_text_name.getText().toString());
            mDatabaseReference.setValue(data);
        }

        ed_site.setText("");
        ed_text_name.setText("");
        edit_text_marka.setText("");
        edit_text_model.setText("");
        edit_text_okrug.setText("");
        edit_text_rayon.setText("");
        edit_text_metro.setText("");
        edit_text_adress.setText("");
        edit_text_number.setText("");
        edit_text_vid_rabot.setText("");
        edit_text_rating.setText("");
        edit_text_grafik_raboti.setText("");

        Toast.makeText(MainActivity.this,"Данные сохранены",Toast.LENGTH_SHORT).show();
    }

    void clearField(){
        ed_site.setText("");
        ed_text_name.setText("");
        edit_text_marka.setText("");
        edit_text_model.setText("");
        edit_text_okrug.setText("");
        edit_text_rayon.setText("");
        edit_text_metro.setText("");
        edit_text_adress.setText("");
        edit_text_number.setText("");
        edit_text_vid_rabot.setText("");
        edit_text_rating.setText("");
        edit_text_grafik_raboti.setText("");
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.all_services:
                Intent intent = new Intent(MainActivity.this,AllList.class);
                startActivity(intent);
        }
    }
}