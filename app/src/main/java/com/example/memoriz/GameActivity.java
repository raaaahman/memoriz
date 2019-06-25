package com.example.memoriz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class GameActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private CardAdapter cardSet;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        game = new Game();

        cardSet = new CardAdapter(this, 0);
        cardSet.add(new Card(R.drawable.cards_club_a));
        cardSet.add(new Card(R.drawable.cards_club_j));
        cardSet.add(new Card(R.drawable.cards_club_j));
        cardSet.add(new Card(R.drawable.cards_club_k));
        cardSet.add(new Card(R.drawable.cards_club_q));
        cardSet.add(new Card(R.drawable.cards_club_a));
        cardSet.add(new Card(R.drawable.cards_club_q));
        cardSet.add(new Card(R.drawable.cards_club_k));

        GridView gameBoard = findViewById(R.id.game_board);
        gameBoard.setNumColumns(GameBoard.calculateOptimalRowNb(cardSet.getCount()));
        gameBoard.setAdapter(cardSet);
        gameBoard.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Card itemClicked = (Card) parent.getItemAtPosition(position);
        game.pickCard(itemClicked);
        cardSet.notifyDataSetChanged();

    }


}
