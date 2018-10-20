package com.clarebhunter.heroichack;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FloatingActionButton fab;

    private List<MarvelCharacter> marvelCharacterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.character_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        marvelCharacterList = listData();
        mAdapter = new CharacterAdapter(marvelCharacterList);
        mRecyclerView.setAdapter(mAdapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<MarvelCharacter> selectedChars = new ArrayList<>();
                for (MarvelCharacter c: marvelCharacterList) {
                    if(c.isSelected()) {
                        selectedChars.add(c);
                    }
                }
                Intent intent = new Intent(getApplicationContext(), ComicsActivity.class);
                intent.putParcelableArrayListExtra("charIDs", selectedChars);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRestart() {
        super.onRestart();
        for (MarvelCharacter c: marvelCharacterList) {
            if (c.isSelected()) {
                c.setSelected(false);
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    private List<MarvelCharacter> listData() {
        List<MarvelCharacter> chars = new ArrayList<>();
        chars.add(new MarvelCharacter("SpiderMan", 0));
        chars.add(new MarvelCharacter("Black Widow", 0));
        chars.add(new MarvelCharacter("Thor", 0));
        chars.add(new MarvelCharacter("Captain America", 0));
        for (int i = 5; i < 21; i++) {
            chars.add(new MarvelCharacter("Character " + i, 0));
        }
        return chars;
    }
}
