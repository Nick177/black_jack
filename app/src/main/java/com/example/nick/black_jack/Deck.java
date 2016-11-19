package com.example.nick.black_jack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Kristine on 11/19/16.
 */

public class Deck {
    private Deque<Integer> cards;
    private int size;
    private int initId;


    public Deck(int initId) {
        this.size = size;
        this.initId = initId;
        int imgId = initId;

        cards = new LinkedList<Integer>();

        for(int ix = 1; ix <= 13; ix++) {
            for(int jx = 0; jx < 4; jx++) {
               // Card card = new Card(ix, imgId);
            }
        }
    }
}
