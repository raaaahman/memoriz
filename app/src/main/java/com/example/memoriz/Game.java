package com.example.memoriz;

import android.widget.ArrayAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {
    private ArrayList<Card> cardSet;
    private Card firstPickedCard;
    private int turnNb;

    public int pickCard(Card card) {

        if (firstPickedCard == null) {
            card.flip();
            firstPickedCard = card;
            return turnNb;
        } else if (!card.equals(firstPickedCard)) {
            if (firstPickedCard.matches(card)) {
                firstPickedCard.setState(Card.CardState.VALIDATED);
                card.setState(Card.CardState.VALIDATED);
                firstPickedCard = null;
                return turnNb++;
            } else {
                firstPickedCard.reset();
                firstPickedCard = null;
                card.reset();
                return turnNb++;
            }
        }
        return 0;
    }

    public int getTurnNb() {

        return turnNb;
    }

    public void setCards(ArrayList<Card> cardSet) {
        this.cardSet = cardSet;
    }

    public boolean isWon() {
        return ! cardSet.stream().anyMatch(
                card -> card.getState().equals(Card.CardState.HIDDEN)
        );
    }

    public Game() {
        turnNb = 1;
    }
}
