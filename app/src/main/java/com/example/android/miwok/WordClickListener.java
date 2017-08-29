package com.example.android.miwok;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

/**
 * Created by chong chen on 2017/8/29.
 */

public class WordClickListener implements View.OnClickListener {

    private MediaPlayer mMediaPlayer;
    private int mAudioId;
    private Context mActivity;

    WordClickListener(Context context, int audioId) {
        mActivity = context;
        mAudioId = audioId;
        mMediaPlayer = MediaPlayer.create(context, mAudioId);
    }

    @Override
    public void onClick(View view) {
        mMediaPlayer.start();
    }
}
