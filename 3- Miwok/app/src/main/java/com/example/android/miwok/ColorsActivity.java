package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mMediaplayer;
    private MediaPlayer.OnCompletionListener mfinished = new MediaPlayer.OnCompletionListener() {@Override  public void onCompletion(MediaPlayer mp) {   mRelease();   }};
    private AudioManager mAudio ;
    private AudioManager.OnAudioFocusChangeListener mListenerChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaplayer.pause();
                mMediaplayer.seekTo(0);
            }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaplayer.start();
            }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                mRelease();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listwords);

        final ArrayList<Word> Words = new ArrayList<Word>();
        Words.add(new Word("White","lutti",R.drawable.ii,R.raw.color_white));
        Words.add(new Word("Black","lutti",R.drawable.ii,R.raw.color_black));
        Words.add(new Word("Yellow","lutti",R.drawable.iii,R.raw.color_dusty_yellow));
        Words.add(new Word("Rose","lutti",R.drawable.ii,R.raw.color_gray));
        Words.add(new Word("Blue","lutti",R.drawable.iii,R.raw.color_brown));
        Words.add(new Word("Green","lutti",R.drawable.i,R.raw.color_green));
        Words.add(new Word("BrightRED","lutti",R.drawable.ii,R.raw.color_mustard_yellow));
        Words.add(new Word("Red","lutti",R.drawable.iii,R.raw.color_red));
        Words.add(new Word("Brown","lutti",R.drawable.ii,R.raw.color_brown));



        ArrayAdapter<Word> itemAdapter = new WordAdapter(this, Words ,R.color.category_colors);

        ListView NumbersList = findViewById(R.id.rootList);
        NumbersList.setAdapter(itemAdapter);
        NumbersList.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordaudio = Words.get(position);
                mRelease();
                mAudio = (AudioManager) getSystemService(AUDIO_SERVICE);
                int result = mAudio.requestAudioFocus(mListenerChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mMediaplayer = MediaPlayer.create(ColorsActivity.this , wordaudio.getAudioId());
                    mMediaplayer.start();
                    mMediaplayer.setOnCompletionListener( mfinished);
                }   }          });}

    @Override
    protected void onStop() {
        super.onStop();
        mRelease();
    }

    private void mRelease(){
        if (mMediaplayer != null){
            mMediaplayer.release();
            mMediaplayer = null;

            mAudio.abandonAudioFocus(mListenerChange);
        }}
}
