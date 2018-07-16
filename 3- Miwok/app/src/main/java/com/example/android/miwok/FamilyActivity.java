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

public class FamilyActivity extends AppCompatActivity {
    private  MediaPlayer mMediaplayer;
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
        Words.add(new Word("Dad","lutti",R.drawable.i,R.raw.family_father));
        Words.add(new Word("Mom","lutti",R.drawable.ii,R.raw.family_mother));
        Words.add(new Word("Brother","lutti",R.drawable.iii,R.raw.family_son));
        Words.add(new Word("Sister","lutti",R.drawable.ii,R.raw.family_older_sister));
        Words.add(new Word("Grandma","lutti",R.drawable.iii,R.raw.family_grandmother));
        Words.add(new Word("GrandDad","lutti",R.drawable.i,R.raw.family_grandfather));
        Words.add(new Word("nefew","lutti",R.drawable.ii,R.raw.family_younger_sister));
        Words.add(new Word("mm","lutti",R.drawable.iii,R.raw.family_older_brother));
        Words.add(new Word("nn","lutti",R.drawable.ii,R.raw.family_daughter));

        ArrayAdapter<Word> itemAdapter = new WordAdapter(this,Words,R.color.category_family);

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
                    mMediaplayer = MediaPlayer.create(FamilyActivity.this , wordaudio.getAudioId());
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
