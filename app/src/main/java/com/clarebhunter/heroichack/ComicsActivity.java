package com.clarebhunter.heroichack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ComicsActivity extends AppCompatActivity {

    private TextView mTextView;

    private List<MarvelCharacter> marvelCharacters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        Intent intent = getIntent();
        marvelCharacters = intent.getParcelableArrayListExtra("charIDs");
        String names = "";
        for (MarvelCharacter c: marvelCharacters) {
            names += c.getName() + "\n";
        }
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText(names);
    }
}
