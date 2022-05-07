package com.mirea.artemov.notesappv2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.mirea.artemov.notesappv2.Models.Notes;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    EditText editText_title,editText_notes;
    ImageView imageView_save;
    Notes notes;
    boolean isOldNote = false;
    Switch switch_mapPoint;
    String position;
    TextView textView_coordinates;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageView_save=findViewById(R.id.imageView_save);
        editText_title=findViewById(R.id.editText_title);
        editText_notes=findViewById(R.id.editText_notes);
        switch_mapPoint=findViewById(R.id.switch_mapPoint);
        textView_coordinates=findViewById(R.id.textView_coordinates);


        notes = new Notes();
        try {
            notes= (Notes) getIntent().getSerializableExtra("old_note");
            editText_title.setText(notes.getTitle());
            editText_notes.setText(notes.getNotes());
            textView_coordinates.setText(notes.getPosition());
            isOldNote=true;
        }
        catch (Exception e){
            e.printStackTrace();
        }


        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=editText_title.getText().toString();
                String description=editText_notes.getText().toString();
                //Marker marker=

                if(description.isEmpty()){
                    Toast.makeText(NotesTakerActivity.this,"Please add some text to the note", Toast.LENGTH_SHORT).show();
                    return;
                }
                SimpleDateFormat formatter =new SimpleDateFormat("EEE, d MMM yyyy HH:mm a");
                //EEE - День недели, d- дата, MMM - месяц, yyyy- год, HH - час, mm- минуты, a - am/pm
                Date date = new Date();

                //Инициализируем здесь, только если заметка старая
                if(!isOldNote){
                    notes=new Notes();
                }


                //устанавливаем аттрибуты
                notes.setTitle(title);
                notes.setNotes(description);
                notes.setDate(formatter.format(date)); // конвертируем объект date в String
                notes.setPosition(position);

                //взаимодействие между различными объектами activity
                Intent intent = new Intent();
                intent.putExtra("note", notes); // передается только сериализуемый объект
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });

        switch_mapPoint.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){
                    /*if(notes.getPosition()==null) {*/
                    /*String noteMarker = notes.getPosition();*/
                        Intent intent = new Intent(NotesTakerActivity.this, MapsActivity.class);
                        String noteMarker = (String) textView_coordinates.getText();
                        intent.putExtra("noteMarker", noteMarker);
                    /*intent.putExtra("noteMarker", noteMarker);*/
                        startActivityForResult(intent, 103);
                   /* }*/
                   /* else{*/
                        /*String noteMarker = notes.getPosition();
                        Intent intent = new Intent(NotesTakerActivity.this, MapsActivity.class);
                        intent.putExtra("noteMarker", noteMarker);
                        startActivity(intent);*/
                    /*}*/
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 103) {
            if (resultCode == Activity.RESULT_OK) {
                position = data.getStringExtra("markerPosition");
            }
        }
        }
    }
