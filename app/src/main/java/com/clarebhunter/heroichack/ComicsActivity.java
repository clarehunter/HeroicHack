package com.clarebhunter.heroichack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

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
        List<Integer> ids = new ArrayList<>();
        for (MarvelCharacter c: marvelCharacters) {
            ids.add(c.getId());
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.comic_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //comics = MarvelAPIReader.getComics(ids);
        comics = new ArrayList<>();
        comics.add(new Comic("Magician: Apprentice Riftwar Saga (2010) #17", "The electrifying conclusion to Raymond E. Feist's epic story! The only thing standing between Crydee and complete destruction is Princess Carline. Can she save Midkemia?"));
        comics.add(new Comic("Incredible Hulks: Dark Son (2010)", "Bruce Banner is the Hulk once again, and far from being left alone, he's got a whole family of gamma-charged allies at his side. Son Skaar, daughter Lyra, cousin She-Hulk, friends A-Bomb and Korg, and wife Red She-Hulk or is that EX-wife? Meanwhile, a universe away, Hiro-Kala, the mysterious DARK SON of Hulk, rises to power on the planer K'ai. He has a plan for his long lost family members, and it involves a bigger smash than his father has ever made. Collecting INCREDIBLE HULKS #612-617."));
        comics.add(new Comic("Black Panther (2016) #4 (Anacleto Mighty Men Variant)", null));

        mAdapter = new ComicAdapter(comics);
        mRecyclerView.setAdapter(mAdapter);
    }
}
