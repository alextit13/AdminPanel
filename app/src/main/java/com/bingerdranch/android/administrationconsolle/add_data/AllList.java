package com.bingerdranch.android.administrationconsolle.add_data;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.bingerdranch.android.administrationconsolle.Data;
import com.bingerdranch.android.administrationconsolle.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AllList extends Activity {

    private ListView listView;
    private ArrayList<Data> main_list_objects = new ArrayList<>();
    private ArrayList<String> main_list_strings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

        init();
    }

    private void init() {
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // go to another activity
                        final Data autoservice = main_list_objects.get(position);
                        new AlertDialog.Builder(AllList.this)
                                .setMessage("Что нужно изменить")
                                .setPositiveButton("Добавить фото",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                addPhoto(autoservice);
                                            }
                                        })
                                .setNegativeButton("Добавить адрес сайта",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                addSite(autoservice);
                                            }
                                        })
                                .show();
                    }
                }
        );
        FirebaseDatabase.getInstance().getReference().addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot data : dataSnapshot.getChildren()){
                            main_list_objects.add(data.getValue(Data.class));
                            main_list_strings.add(data.getValue(Data.class).getName());
                        }
                        goTostack(main_list_strings,main_list_objects);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }

    private void addSite(Data autoservice) {
        AlertDialog.Builder builder = new AlertDialog.Builder(AllList.this);

        LayoutInflater inflater = LayoutInflater.from(AllList.this);
        View viewRoot = inflater.inflate(R.layout.dialog_view, null);

        //do something with your view

        builder.setView(viewRoot);
    }

    private void addPhoto(Data autoservice) {

    }

    private void goTostack(ArrayList<String> MS, ArrayList<Data> MO) {
        ArrayAdapter adapter = new ArrayAdapter(AllList.this,android.R.layout.simple_list_item_1
        ,MS);
        listView.setAdapter(adapter);
    }
}
