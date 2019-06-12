package com.example.memoriz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TableLayout;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {
    private GridView gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        CardAdapter cardAdapter = new CardAdapter(this, 0);
        cardAdapter.add(R.drawable.ic_launcher_background);
        cardAdapter.add(R.drawable.ic_launcher_background);
        cardAdapter.add(R.drawable.ic_launcher_background);
        cardAdapter.add(R.drawable.ic_launcher_background);
        cardAdapter.add(R.drawable.ic_launcher_background);
        cardAdapter.add(R.drawable.ic_launcher_background);

        gameBoard = findViewById(R.id.game_board);
        gameBoard.setNumColumns(GameBoard.calculateOptimalRowNb(cardAdapter.getCount()));
        gameBoard.setAdapter(cardAdapter);
    }
}
