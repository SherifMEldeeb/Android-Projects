package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private  MediaPlayer mMediaplayer;
    private MediaPlayer.OnCompletionListener mfinished = new MediaPlayer.OnCompletionListener() {@Override  public void onCompletion(MediaPlayer mp) {
        mRelease(); }};

    private AudioManager mAudio;
    private AudioManager.OnAudioFocusChangeListener mListenerChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mMediaplayer.pause();
                mMediaplayer.seekTo(0);
            }else if (focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mMediaplayer.start();
            }else{
                mRelease();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listwords);

        final ArrayList<Word> Words = new ArrayList<Word>();
        Words.add(new Word("vgyujm","lutti",R.raw.phrase_are_you_coming));
        Words.add(new Word("mjhgf","lutti",R.raw.phrase_come_here));
        Words.add(new Word("bhu","lutti",R.raw.phrase_how_are_you_feeling));
        Words.add(new Word("mnbv","lutti",R.raw.phrase_im_coming));
        Words.add(new Word("vghj","lutti",R.raw.phrase_im_feeling_good));
        Words.add(new Word("Green","lutti",R.raw.phrase_lets_go));
        Words.add(new Word("BrightRED","lutti",R.raw.phrase_my_name_is));
        Words.add(new Word("Red","lutti",R.raw.phrase_yes_im_coming));
        Words.add(new Word("Brown","lutti",R.raw.phrase_what_is_your_name));



        ArrayAdapter<Word> itemAdapter = new WordAdapter(this,Words,R.color.category_phrases);

        ListView NumbersList = findViewById(R.id.rootList);
        NumbersList.setAdapter(itemAdapter);

        NumbersList.setOnItemClickListener(new AdapterView.OnItemClickListener( ) {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word wordaudio = Words.get(position);
                mRelease();
                mAudio = (AudioManager) getSystemService(AUDIO_SERVICE);
                int result = mAudio.requestAudioFocus(mListenerChange,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED ){
                    mMediaplayer = MediaPlayer.create(PhrasesActivity.this , wordaudio.getAudioId());
                    mMediaplayer.start();
                    mMediaplayer.setOnCompletionListener(mfinished);
                }
            }
        });     }

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
