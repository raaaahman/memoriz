package com.example.memoriz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private CardAdapter cardSet;
    private GridView gameBoard;
    private Game game;
    private Animation revealCardAnimation;
    private Animation hideCardAnimation;

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

        gameBoard = findViewById(R.id.game_board);
        gameBoard.setNumColumns(GameBoard.calculateOptimalRowNb(cardSet.getCount()));
        gameBoard.setAdapter(cardSet);
        gameBoard.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Card itemClicked = (Card) parent.getItemAtPosition(position);
        game.pickCard(itemClicked);

        //Animate the picked card
        AnimatorSet currentCardAnimatorSet = new AnimatorSet();
        ImageView selectedView = view.findViewById(R.id.card_image);
        ObjectAnimator revealCardAnimator = ObjectAnimator.ofFloat(selectedView, "alpha",  1f);
        revealCardAnimator.setDuration(400);
        currentCardAnimatorSet.play(revealCardAnimator);

        //Update other cards' views
        for(int itemNb = 0; itemNb < parent.getAdapter().getCount(); itemNb++ ) {
            Card item = (Card) parent.getItemAtPosition(itemNb);
            ImageView cardImage = parent.getChildAt(itemNb).findViewById(R.id.card_image);
            if (item.getState().equals(Card.CardState.HIDDEN)) {
                ObjectAnimator hideCardAnimator = ObjectAnimator.ofFloat(cardImage, "alpha",  0f);
                hideCardAnimator.setDuration(400);
                hideCardAnimator.setStartDelay(400);
                if (item == itemClicked) {
                    currentCardAnimatorSet.play(hideCardAnimator).after(revealCardAnimator);
                } else {
                    hideCardAnimator.start();
                }
            }
        }

        currentCardAnimatorSet.start();
    }

    public void flipCardAnimation(View cardView) {
        ImageView cardImage = cardView.findViewById(R.id.card_image);

        cardImage.setAlpha(0f);
        cardImage.setVisibility(View.VISIBLE);
        cardImage.animate()
                .alpha(1f)
                .setDuration(400)
                .setListener(null);
    }

    public  void hideCardAnimation(int position) {
        View cardView = cardSet.getView(position, null, gameBoard);
        ImageView cardImage = cardView.findViewById(R.id.card_image);

        cardImage.animate()
            .alpha(0f)
            .setStartDelay(400)
            .setDuration(400)
            .setListener(null);
    }
}
