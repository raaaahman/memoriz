package com.example.memoriz;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {
    private Card firstPickedCard;
    private int turnsPlayed;

    public void pickCard(Card card) {
        card.flip();

        if (firstPickedCard == null) {
            firstPickedCard = card;
        } else if (firstPickedCard.matches(card)) {
            firstPickedCard.setState(Card.CardState.VALIDATED);
            card.setState(Card.CardState.VALIDATED);
            firstPickedCard = null;
            turnsPlayed++;
        } else {
            firstPickedCard.reset();
            firstPickedCard = null;
            card.reset();
            turnsPlayed++;
        }
    }

    public int getTurnNb() {

        return turnsPlayed + 1;
    }

    private void resetCard(Card card) {
        card.reset();
    }

    public Game() {
        turnsPlayed = 0;
    }
}
