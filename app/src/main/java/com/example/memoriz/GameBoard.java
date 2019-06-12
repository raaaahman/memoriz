package com.example.memoriz;

public class GameBoard {
    private static final int MAX_ROWS = 6;
    public static int calculateOptimalRowNb(int itemNb) {
        int rowNb;

        if (itemNb < 6) {
            rowNb = 2;
        } else if (itemNb < 13) {
            rowNb = 3;
        } else {
            rowNb = 4;
        }
        return rowNb;
    }
}
