package com.example.nick.black_jack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Kristine on 11/19/16.
 */

public class Deck {
    private Deque<Card> cards;
    private int size;
    private int initId;


    public Deck(int initId) {
        this.initId = initId;
        int imgId = initId;

        cards = new LinkedList<Card>();

        for(int ix = 1; ix <= 13; ix++) {
            for(int jx = 0; jx < 4; jx++) {
                int value = ix;
                if(value > 10)
                    value = 10;
                Card card = new Card(value, imgId);
                cards.addFirst(card);
                imgId++;
            }
        }
    }

    public void reset() {
        int imgId = initId;

        cards.clear();

        for(int ix = 1; ix <= 13; ix++) {
            for(int jx = 0; jx < 4; jx++) {
                int value = ix;
                if(value > 10)
                    value = 10;
                Card card = new Card(value, imgId);
                cards.addFirst(card);
                imgId++;
            }
        }
    }
}
