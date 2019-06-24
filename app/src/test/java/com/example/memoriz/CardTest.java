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

    @ParameterizedTest
    @CsvSource({
            "0, FLIPPED, 0, FLIPPED, true, Two cards with the same image should match.",
            "1, FLIPPED, 1, HIDDEN, true, Two cards with the same image should match, even if one is hidden.",
            "0, FLIPPED, 1, FLIPPED, false, Two cards with different images shouldn't match.",
            "1, HIDDEN, 2, HIDDEN, false, Two cards with different images shouldn't match, even if they are both hidden."
    })
    public void tryMatchingCards(int card1Image, Card.CardState card1State, int card2Image, Card.CardState card2CardState, boolean expected, String message) {
        Card card1 = new Card(card1Image);
        card1.setState(card1State);
        Card card2 = new Card(card2Image);
        card2.setState(card2CardState);

        boolean match = card1.matches(card2);

        Assertions.assertEquals(expected, match, message);
    }

}
