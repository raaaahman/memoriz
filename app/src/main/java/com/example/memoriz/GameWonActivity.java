package com.example.memoriz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GameWonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won_activity);

        Button replayButton = findViewById(R.id.win_replay_button);
        replayButton.setOnClickListener(view -> {
                Intent replayGame = new Intent(getBaseContext(), GameActivity.class);
                startActivity(replayGame);
        });
    }
}
