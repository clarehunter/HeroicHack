package com.clarebhunter.heroichack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ComicsActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private List<MarvelCharacter> marvelCharacters;
    private List<Comic> comics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comics);

        Intent intent = getIntent();
        marvelCharacters = intent.getParcelableArrayListExtra("charIDs");

        mRecyclerView = (RecyclerView) findViewById(R.id.comic_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // TODO: fill with correct comics based on characters
        comics = new ArrayList<>();
        comics.add(new Comic("SpiderMan", ""));
        comics.add(new Comic("Captain America", ""));

        mAdapter = new ComicAdapter(comics);
        mRecyclerView.setAdapter(mAdapter);
    }
}
