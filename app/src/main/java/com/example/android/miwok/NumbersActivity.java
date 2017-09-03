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

import static android.media.AudioManager.AUDIOFOCUS_GAIN_TRANSIENT;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_FAILED;
import static android.media.AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
import static android.media.AudioManager.STREAM_MUSIC;

/**
 * Created by chong chen on 2017/8/9.
 */

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;

    private AudioManager mAudioManager;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            Toast.makeText(getApplicationContext(), "I'm done! Release the resource", Toast.LENGTH_SHORT).show();
            releaseMediaPlayer();
        }
    };

    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (AudioManager.AUDIOFOCUS_GAIN == focusChange) {
                mMediaPlayer.start();
                Log.i("NumbersActivity", "onAudioFocusChange gain");
            } else if (AudioManager.AUDIOFOCUS_LOSS == focusChange) {
                releaseMediaPlayer();
                Log.i("NumbersActivity", "onAudioFocusChange release resource");
            } else if (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK == focusChange ||
                        AudioManager.AUDIOFOCUS_LOSS_TRANSIENT == focusChange && mMediaPlayer != null) {
                //mMediaPlayer.setVolume(10, 10);
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
                Log.i("NumbersActivity", "onAudioFocusChange can duck");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));

        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // release the audio resource before start if there is a audio file is playing
                releaseMediaPlayer();

                Word word = words.get(i);

                int adReq = mAudioManager.requestAudioFocus(mAudioFocusChangeListener, STREAM_MUSIC, AUDIOFOCUS_GAIN_TRANSIENT);
                if (adReq == AUDIOFOCUS_REQUEST_FAILED) {
                    Log.w("NumbersActivity", "audio manager return fail");
                } else if (adReq == AUDIOFOCUS_REQUEST_GRANTED) {
                    Log.i("NumbersActivity", "audio manager granted");
                    mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getAudioID());
                    mMediaPlayer.start();

                    // set the callback when audio play over
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    /**
     * when the audio play over, release the audio resource
     */
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}

