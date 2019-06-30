package com.example.memoriz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.support.v4.content.res.ResourcesCompat;
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

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

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
        ArrayList<Integer> allImages = new ArrayList<>();
        allImages.add(R.drawable.cards_club_a);
        allImages.add(R.drawable.cards_club_k);
        allImages.add(R.drawable.cards_club_q);
        allImages.add(R.drawable.cards_club_j);
        allImages.add(R.drawable.card_diamond_a);
        allImages.add(R.drawable.card_diamond_k);
        allImages.add(R.drawable.card_diamond_q);
        allImages.add(R.drawable.card_diamond_j);
        Collections.shuffle(allImages);
        ArrayDeque<Integer> selectedImages = new ArrayDeque<>();
        selectedImages.addAll(allImages.subList(0, 4));

        ArrayList<Card> cardList = new ArrayList<>();
        for (int pairNb = 0; pairNb < 4; pairNb++) {
            int imageResource = selectedImages.pop();
            cardList.add(new Card(imageResource));
            cardList.add(new Card(imageResource));
        }
        Collections.shuffle(cardList);

        cardSet = new CardAdapter(this, 0);
        cardSet.addAll(cardList);

        game = new Game();
        game.setCards(cardList);

        gameBoard = findViewById(R.id.game_board);
        gameBoard.setNumColumns(GameBoard.calculateOptimalRowNb(cardSet.getCount()));
        gameBoard.setAdapter(cardSet);
        gameBoard.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Card itemClicked = (Card) parent.getItemAtPosition(position);
        if (game.pickCard(itemClicked) > 0 ) {
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

        if (game.isWon()) {
            Intent winScreen = new Intent(this, GameWonActivity.class);
            startActivity(winScreen);
        }
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
