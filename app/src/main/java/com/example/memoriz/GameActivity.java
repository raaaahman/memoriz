package com.example.memoriz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
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
        gameBoard.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Clicked on : " + parent.getPositionForView(view), Toast.LENGTH_LONG).show();
    }
}
