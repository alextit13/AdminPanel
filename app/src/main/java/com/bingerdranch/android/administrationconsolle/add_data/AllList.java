package com.bingerdranch.android.administrationconsolle.add_data;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bingerdranch.android.administrationconsolle.Data;
import com.bingerdranch.android.administrationconsolle.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class AllList extends Activity {

    private ProgressBar progress;
    public static final int PICK_IMAGE_REQUEST = 71;
    private ListView listView;
    private ArrayList<Data> main_list_objects = new ArrayList<>();
    private ArrayList<String> main_list_strings = new ArrayList<>();
    private Uri filePath;
    private Data autoservice;
    private String site;
    private EditText editText_site;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

        init();
    }

    private void init() {
        progress = (ProgressBar)findViewById(R.id.progress);
        listView = (ListView) findViewById(R.id.list);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // go to another activity
                        autoservice = main_list_objects.get(position);
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

    private void addSite(final Data autoservice) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(AllList.this);

        LayoutInflater inflater = LayoutInflater.from(AllList.this);
        View viewRoot = inflater.inflate(R.layout.dialog_view, null);
        editText_site = (EditText)viewRoot.findViewById(R.id.edit_site);

        ((Button)viewRoot.findViewById(R.id.button_save_site))
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                site = editText_site.getText().toString();
                                FirebaseDatabase.getInstance().getReference().child(autoservice.getName()).child("site")
                                        .setValue(site)
                                        .addOnCompleteListener(
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(AllList.this, "Данные сохранены", Toast.LENGTH_SHORT).show();
                                                        editText_site.setText("");
                                                    }
                                                }
                                        );
                            }
                        }
                );
        builder.setView(viewRoot);
        builder.show();

        //do something with your view

        builder.setView(viewRoot);
    }

    private void addPhoto(Data autoservice) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //pr_CIV_image_driver.setImageBitmap(bitmap);
                uploadPhotoToFirebase(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void uploadPhotoToFirebase(Bitmap bitmap) {
        FirebaseStorage storage;
        StorageReference storageReference;
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Загрузка...");
            progressDialog.show();

            StorageReference ref = storageReference.child("users_images/"+ UUID.randomUUID().toString());

            //Log.d(MainActivity.LOG_TAG,"path image TEST = " + filePath);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            //Log.d(MainActivity.LOG_TAG,"path image = " + taskSnapshot.getDownloadUrl());
                            Toast.makeText(AllList.this, "Загружено!", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference().child(autoservice.getName())
                                    .child("image_path")
                                    .setValue(taskSnapshot.getDownloadUrl()+"");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AllList.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Загружено "+(int)progress+"%");
                        }
                    });
        }
    }

    private void goTostack(ArrayList<String> MS, ArrayList<Data> MO) {
        ArrayAdapter adapter = new ArrayAdapter(AllList.this,android.R.layout.simple_list_item_1
        ,MS);
        listView.setAdapter(adapter);
        progress.setVisibility(View.INVISIBLE);
    }
}
