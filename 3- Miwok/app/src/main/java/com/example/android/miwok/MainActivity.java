package com.example.android.miwok;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.Reference;
import java.util.EventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView num = (TextView)findViewById(R.id.numbers);
        TextView family = (TextView)findViewById(R.id.family);
        TextView colors = (TextView)findViewById(R.id.colors);
        TextView phrases = (TextView)findViewById(R.id.phrases);
        num.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view){
               startActivity(new Intent(MainActivity.this,NumbersActivity.class));
           }
        });
        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,FamilyActivity.class));
            }
        });
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, ColorsActivity.class));
            }
        });
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,PhrasesActivity.class));
            }
        });


    }
}
