package com.example.android.miwok;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by chong chen on 2017/8/21.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Activity context, ArrayList<Word> wordList) {
        super(context, 0, wordList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);
        TextView miwokText = (TextView) listItemView.findViewById(R.id.list_item_text_miwork);
        miwokText.setText(currentWord.getMiwokText());

        TextView defaultText = (TextView) listItemView.findViewById(R.id.list_item_text_default);
        defaultText.setText(currentWord.getDefaultText());

        return listItemView;
    }
}