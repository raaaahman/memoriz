package com.example.memoriz;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Game {
    private ArrayList<Card> pickedCards;
    private int turnsPlayed;

    public void pickCard(Card card) {
        card.flip();
        pickedCards.add(card);

        if (pickedCards.size() % 2 == 0) {
            turnsPlayed++;
            pickedCards.forEach(flippedCard -> {{
                flippedCard.reset();
            }});
        }
    }

    public int getTurnNb() {

        return turnsPlayed + 1;
    }

    private void resetCard(Card card) {
        card.reset();
    }

    public Game() {
        pickedCards = new ArrayList<>();
        turnsPlayed = 0;
    }
}
