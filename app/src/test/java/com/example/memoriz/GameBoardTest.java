package com.example.memoriz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GameBoardTest {

    @ParameterizedTest
    @CsvSource({
            "4, 2",
            "5, 2",
            "7, 3",
            "12, 3",
            "13, 4"
    })
    public void testCalculateRowNb(int itemNb, int expectedRowNb) {
        int calculatedRowNb = GameBoard.calculateOptimalRowNb(itemNb);

        Assertions.assertEquals(expectedRowNb, calculatedRowNb);
    }
}
