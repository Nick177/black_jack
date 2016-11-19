package com.example.nick.black_jack;

import android.media.Image;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Deck deck;
    private int computerHand;
    private int playerHand;
    private static final int THRESHOLD = 17;
    private static final String ALERT_ACE_TITLE = "ACE";
    private static final String ALERT_ACE_MSG = "You got an ACE! Choose the value 1 or 11";
    private int numChoice;
    private int player_score = 0;
    private int computer_score = 0;
    private static final String PLAYER_WON = "You are the winner!!!";
    private static final String COMP_WON = "Sorry, you lost";
    private boolean playerWon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerWon = true;

        int id = getResources().getIdentifier("a01c", "drawable", "com.example.nick.black_jack");
        deck = new Deck(id);
        deck.shuffle();
    }



    public void hold(View view) {

    }

    public void hitT(View view) {
        if (deck.getDeckSize() == 0) {

        } else {
            ImageView cardImage = (ImageView) findViewById(R.id.card_slot1);
            Card cardToDisplay = deck.getTopCard();
            cardImage.setImageResource(cardToDisplay.getImageID());
            TextView playerScore = (TextView) findViewById(R.id.p_score);

            player_score += cardToDisplay.getValue();
            playerScore.setText(String.valueOf(player_score));

            //computerTurn();
            //pickValOfAce();
        }

    }

    public void hitB(View view) {
        if (deck.getDeckSize() == 0) {

        } else {
            ImageView cardImage = (ImageView) findViewById(R.id.card_slot1);
            Card cardToDisplay = deck.getBottomCard();
            cardImage.setImageResource(cardToDisplay.getImageID());
            TextView playerScore = (TextView) findViewById(R.id.p_score);

            player_score += cardToDisplay.getValue();
            playerScore.setText(String.valueOf(player_score));

            computerTurn();

        }

    }

    void gameOver() {
        String msg;
        if (playerWon) {
            msg = PLAYER_WON;
        } else {
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

    void computerTurn() {

        if (computerHand <= THRESHOLD) {
            Card card = deck.getTopCard();
            int cardVal = card.getValue();

            if (cardVal != 1) {

                if (computerHand + cardVal > 21) {
                    playerWon = true;
                    gameOver();
                } else if (computerHand + cardVal < 21) {
                    computerHand += cardVal;
                } else {
                    playerWon = false;
                    gameOver();
                }
            } else {
                cardVal = (computerHand + 11 > 21) ? 1 : 11;

                if (computerHand + cardVal > 21) {
                    playerWon = true;
                    gameOver();
                } else if (computerHand + cardVal < 21) {
                    computerHand += cardVal;
                } else {
                    playerWon = false;
                    gameOver();
                }

            }
        }

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


        Log.v("***************", "" + numChoice);

        return numChoice;
    }
}
