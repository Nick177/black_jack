package com.example.nick.black_jack;

import android.media.Image;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Deck deck;
    private int computerHand;
    private int playerHand;
    private static final int THRESHOLD = 17;
    private static final String ALERT_ACE_TITLE = "ACE";
    private static final String ALERT_ACE_MSG = "You got an ACE! Choose the value 1 or 11";
    private int numChoice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int id = getResources().getIdentifier("01c", "drawable", "com.example.nick.black_jack");
        deck = new Deck(id);
    }

    void computerTurn() {

         if(computerHand <= THRESHOLD) {
             Card card = deck.getTopCard();

             if(card.getValue() != 1) {
                 computerHand += card.getValue();
             }
             else {
                 pickValOfAce();
             }


         }

    }


    public void hold(View view)
    {

    }
    public void hitT(View view)
    {

    }
    public void hitB(View view)
    {

    }

    private int pickValOfAce() {

        numChoice = 0;

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle(ALERT_ACE_TITLE)
                .setMessage(ALERT_ACE_MSG)
                .setPositiveButton("11", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        numChoice = 11;
                    }
                })
                .setNegativeButton("1", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        numChoice = 1;
                    }
                })
                .create();
        dialog.show();

        return numChoice;
    }
}
