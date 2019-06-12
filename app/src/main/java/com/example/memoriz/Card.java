package com.example.memoriz;

public class Card {
    public enum CardState {
        HIDDEN, FLIPPED, VALIDATED
    }

    private CardState currentState;

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
}
