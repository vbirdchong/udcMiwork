package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.media.AudioManager.AUDIOFOCUS_REQUEST_FAILED;

/**
 * Created by chong chen on 2017/8/9.
 */

public class ColorsActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(getApplicationContext(), "I'm done! Release the resource", Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (AudioManager.AUDIOFOCUS_GAIN == focusChange) {
                mMediaPlayer.start();
            } else if (AudioManager.AUDIOFOCUS_LOSS == focusChange) {
                releaseMediaPlayer();
            } else if (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK == focusChange ||
                    AudioManager.AUDIOFOCUS_LOSS_TRANSIENT == focusChange && mMediaPlayer != null) {
                //mMediaPlayer.setVolume(10, 10);
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red", "weṭeṭṭi", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("green", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("brown", "ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("gray", "ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("black", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("white", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();

                Word word = words.get(i);

                int returnVal = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                                                                AudioManager.STREAM_MUSIC,
                                                                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (returnVal == AudioManager.AUDIOFOCUS_REQUEST_FAILED) {
                    Log.w("ColorsActivity", "audio manager return fail");
                } else if (returnVal == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    Log.i("ColorsActivity", "audio manager granted");
                    mMediaPlayer = MediaPlayer.create(ColorsActivity.this, word.getAudioID());
                    mMediaPlayer.start();

                    // set the callback when audio play over
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
