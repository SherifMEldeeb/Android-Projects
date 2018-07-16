package com.example.android.miwok;

import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
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
        Words.add(new Word("One","lutti|",R.drawable.i,R.raw.number_one));
        Words.add(new Word("Two","lutti||",R.drawable.ii,R.raw.number_two));
        Words.add(new Word("Three","lutti|||",R.drawable.iii,R.raw.number_three));
        Words.add(new Word("Four","lutti||||",R.drawable.iiii,R.raw.number_four));
        Words.add(new Word("Five","lutti|||||",R.drawable.iiiii,R.raw.number_five));
        Words.add(new Word("Six","lutti||||||",R.drawable.iiiiii,R.raw.number_six));
        Words.add(new Word("Seven","lutti|||||||",R.drawable.iiiiiiii,R.raw.number_seven));
        Words.add(new Word("Eight","lutti||||||||",R.drawable.iiiiiiii,R.raw.number_eight));
        Words.add(new Word("nine","lutti|||||||||",R.drawable.iiiiiiiii,R.raw.number_nine));
        Words.add(new Word("Ten","lutti||||||||||",R.drawable.iiiiiiiiiii,R.raw.number_ten));


        ArrayAdapter<Word> itemAdapter = new WordAdapter(this,Words,R.color.category_numbers);

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
                    mMediaplayer = MediaPlayer.create(NumbersActivity.this , wordaudio.getAudioId());
                    mMediaplayer.start();
                    mMediaplayer.setOnCompletionListener( mfinished);
                }   }          });
    }

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
