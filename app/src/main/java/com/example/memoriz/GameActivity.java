package com.example.memoriz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private GridView gameBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);

        CardAdapter cardAdapter = new CardAdapter(this, 0);
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));
        cardAdapter.add(new Card(R.drawable.ic_launcher_foreground));

        gameBoard = findViewById(R.id.game_board);
        gameBoard.setNumColumns(GameBoard.calculateOptimalRowNb(cardAdapter.getCount()));
        gameBoard.setAdapter(cardAdapter);
        gameBoard.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Card itemClicked = (Card) parent.getItemAtPosition(position);
        itemClicked.flip();
        ImageView image = view.findViewById(R.id.card_image);
        image.setImageResource(itemClicked.getImage());
    }


}
