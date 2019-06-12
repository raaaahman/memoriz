package com.example.memoriz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CardTest {

    @ParameterizedTest
    @CsvSource({
            "HIDDEN, FLIPPED",
            "FLIPPED, HIDDEN",
            "VALIDATED, VALIDATED"
    })
    public void testFlipCard(Card.CardState startState, Card.CardState endState) {
        Card card = new Card();
        card.setState((Card.CardState) startState);

        card.flip();

        Assertions.assertEquals((Card.CardState) endState, card.getState());
    }
}
