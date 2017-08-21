package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by chong chen on 2017/8/9.
 */

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> words = new ArrayList<String>();
        words.add("one");
        words.add("two");
        words.add("three");
        words.add("four");
        words.add("five");
        words.add("six");
        words.add("seven");
        words.add("eight");
        words.add("nine");
        words.add("ten");
/*
        LinearLayout rootView = (LinearLayout)findViewById(R.id.rootView);

        for (int index = 0; index < words.size(); index++){
            TextView textView = new TextView(this);
            textView.setText(words.get(index));
            rootView.addView(textView);
        }*/

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, R.layout.list_item, words);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }
}

