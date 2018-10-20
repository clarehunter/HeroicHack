package com.clarebhunter.heroichack;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private FloatingActionButton fab;

    private List<Character> characterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.character_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        setListData();
        mAdapter = new CharacterAdapter(characterList);
        mRecyclerView.setAdapter(mAdapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Character> selectedChars = new ArrayList<>();
                String chars = "";
                for (Character c: characterList) {
                    if(c.isSelected()) {
                        selectedChars.add(c);
                        chars+=c.getName() + " ";
                    }
                }
                Snackbar.make(view, "" + chars, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setListData() {
        characterList = new ArrayList<>();
        characterList.add(new Character("SpiderMan", 0));
        characterList.add(new Character("Black Widow", 0));
        characterList.add(new Character("Thor", 0));
        characterList.add(new Character("Captain America", 0));
        for (int i = 5; i < 21; i++) {
            characterList.add(new Character("Character " + i, 0));
        }
    }
}
