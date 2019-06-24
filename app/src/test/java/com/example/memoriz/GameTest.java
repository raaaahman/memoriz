package com.example.memoriz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;

public class GameTest {

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "3, 2",
            "4, 3"
    })
    public void pickingTwoCardsMovesTheTurnUp(int repeat, int turnNb) {
        Game game = new Game();

        for (int i = 0; i < repeat; i++) {
            game.pickCard(new Card(0));
        }

        Assertions.assertEquals(turnNb, game.getTurnNb());
    }

    @Test
    public void flipCardsWhenPicked() {
        Game game = new Game();
        Card card = Mockito.mock(Card.class);

        game.pickCard(card);

        verify(card).flip();
    }

    @Test
    public void resetCardsAfterTwoPicks() {
        Game game = new Game();
        Card card1 = Mockito.mock(Card.class);
        Card card2 = Mockito.mock(Card.class);

        game.pickCard(card1);
        game.pickCard(card2);

        verify(card1, times(1)).reset();
        verify(card2, times(1)).reset();
    }

    @Test
    public void pairedCardsAreNotReset() {
        Game game = new Game();
        Card card1 = Mockito.mock(Card.class);
        Card card2 = Mockito.mock(Card.class);

        when(card1.matches(card2)).thenReturn(true);
        game.pickCard(card1);

        verify(card1, never()).reset();
    }
}
