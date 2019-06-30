package com.example.memoriz;

public class Card {
    public static final int CardBack = R.drawable.card_back_green5;

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
        }
    }

    public void reset() {
        if (currentState == CardState.FLIPPED) {
            this.currentState = CardState.HIDDEN;
        }
    }

    public boolean matches(Card card) {
        return image == card.image;
    }

    public CardState getState() {
        return currentState;
    }

    public void setState(CardState state) {
        this.currentState = state;
    }

    public int getImage() {
        return image;
    }

    Card(int image) {
        this.image = image;
        this.currentState = CardState.HIDDEN;
    }
}
