package com.example.memoriz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource({
            "HIDDEN, FLIPPED",
            "FLIPPED, FLIPPED",
            "VALIDATED, VALIDATED"
    })
    public void testFlipCard(Card.CardState startState, Card.CardState endState) {
        Card card = new Card(0);
        card.setState(startState);

        card.flip();

        Assertions.assertEquals(endState, card.getState());
    }

    @ParameterizedTest
    @CsvSource({
        "HIDDEN, HIDDEN",
        "FLIPPED, HIDDEN",
        "VALIDATED, VALIDATED"
    })
    public void testResetCard(Card.CardState startState, Card.CardState endState) {
        Card card = new Card(0);
        card.setState(startState);

        card.reset();

        Assertions.assertEquals(endState, card.getState());
    }
}
