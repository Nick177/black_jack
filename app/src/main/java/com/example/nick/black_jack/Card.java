package com.example.nick.black_jack;

/**
 * Created by Kristine on 11/19/16.
 */

public class Card {
    private int value;
    private int imageID;

    public Card(int entry, int image) {
        if (value <= 9) {
            this.value = entry;
        } else {
            this.value = 10;
        }
        this.imageID = image;
    }

    public int getValue() {
        return value;
    }

    public int getImageID() {
        return imageID;
    }

    public void setValue(int entry) {
        this.value = entry;
    }

    public void setImageID(int image) {
        this.imageID = image;
    }
}
