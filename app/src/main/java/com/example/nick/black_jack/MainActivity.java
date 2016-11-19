package com.example.nick.black_jack;

import android.media.Image;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Deck deck;
    private int computerHand;
    private int playerHand;
    private static final int THRESHOLD = 17;
    private static final String ALERT_ACE_TITLE = "ACE";
    private static final String ALERT_ACE_MSG = "You got an ACE! Choose the value 1 or 11";
    private static final String PLAYER_WON = "Your the winner!!!";
    private static final String COMP_WON = "Sorry, you lost";
    private int numChoice;
    private boolean playerWon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //int id = getResources().getIdentifier("01c", "drawable", "com.example.nick.black_jack");
        //deck = new Deck(id);
        pickValOfAce();
        playerWon = true;
    }

    void computerTurn() {

         if(computerHand <= THRESHOLD) {
             Card card = deck.getTopCard();
             int cardVal = card.getValue();

             if(cardVal != 1) {
                 computerHand += card.getValue();
             }
             else {
                 if(computerHand + cardVal > 21) {
                     playerWon = true;
                     gameOver();
                 }
                 else if(computerHand + cardVal < 21) {
                     computerHand += cardVal;
                 }
                 else {
                     playerWon = false;
                     gameOver();
                 }
             }

         }

    }


    public void hold(View view)
    {

        computerTurn();
    }
    public void hitT(View view)
    {

        computerTurn();
    }
    public void hitB(View view)
    {

        computerTurn();
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

        while(numChoice == 0) {

        }

        Log.v("***************", "" + numChoice);

        return numChoice;
    }

    void gameOver() {
        String msg;
        if(playerWon) {
            msg = PLAYER_WON;
        }
        else {
            msg = COMP_WON;
        }

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("GAME OVER")
                .setMessage(msg)
                .setPositiveButton("OK", null)
                .setNegativeButton("CANCEL", null)
                .create();
        dialog.show();

        deck.reset();
    }
}
