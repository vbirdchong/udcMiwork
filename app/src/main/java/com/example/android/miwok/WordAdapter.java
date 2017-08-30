package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chong chen on 2017/8/21.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mBackgroundColorResId;

    public WordAdapter(Activity context, ArrayList<Word> wordList, int backgroundColorResId) {
        super(context, 0, wordList);
        mBackgroundColorResId = backgroundColorResId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);
        // method 1: change the background resource
//        LinearLayout content = (LinearLayout) listItemView.findViewById(R.id.list_item_content);
//        content.setBackgroundResource(mBackgroundColorResId);

        // method 2: get current content's color by color resource id, then set the view color
        View content = listItemView.findViewById(R.id.list_item_content);
        int color = ContextCompat.getColor(getContext(), mBackgroundColorResId);
        content.setBackgroundColor(color);

        TextView miwokText = (TextView) listItemView.findViewById(R.id.list_item_text_miwork);
        miwokText.setText(currentWord.getMiwokText());

        TextView defaultText = (TextView) listItemView.findViewById(R.id.list_item_text_default);
        defaultText.setText(currentWord.getDefaultText());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.list_item_image);
        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageID());
            imageView.setVisibility(View.VISIBLE);
        }
        else {
            // if we have no image to show, set the image view to GONE.
            // if set the view to INVISIBLE, we will have a blank which size as image size before the TextView
            imageView.setVisibility(View.GONE);
        }

        // one method to implement the audio play
//        if (currentWord.hasAudio()) {
//            WordClickListener listener = new WordClickListener(this.getContext(), currentWord.getAudioID());
//            content.setOnClickListener(listener);
//        }

        return listItemView;
    }
}
