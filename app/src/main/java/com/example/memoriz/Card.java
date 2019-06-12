package com.example.memoriz;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class Card {
    private static final int CardBack = R.drawable.ic_launcher_background;

    public enum CardState {
        HIDDEN, FLIPPED, VALIDATED
    }

    private CardState currentState;

    private int image;

    public void flip() {
        switch (currentState) {
            case HIDDEN:
                this.currentState = CardState.FLIPPED;
                break;
            case FLIPPED:
                this.currentState = CardState.HIDDEN;
                break;
        }
    }

    public CardState getState() {
        return currentState;
    }

    public void setState(CardState state) {
        this.currentState = state;
    }

    public int getImage() {
        if (currentState == CardState.HIDDEN) {
            return Card.CardBack;
        } else {
            return image;
        }
    }

    Card(int image) {
        this.image = image;
        this.currentState = CardState.HIDDEN;
    }
}
