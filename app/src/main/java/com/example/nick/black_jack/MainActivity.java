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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Deck deck;
    private int computerHand= 0;
    private static final int THRESHOLD = 17;
    private static final String ALERT_ACE_TITLE = "ACE";
    private static final String ALERT_ACE_MSG = "You got an ACE! Choose the value 1 or 11";
    private int numChoice;
    private int player_score = 0;
    private int computer_score = 0;
    private static final String PLAYER_WON = "You are the winner!!!";
    private static final String COMP_WON = "Sorry, you lost";
    private boolean playerWon;
    private boolean isHold;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerWon = true;
        isHold = false;

        int id = getResources().getIdentifier("a01c", "drawable", "com.example.nick.black_jack");
        deck = new Deck(id);
        deck.shuffle();
    }



    public void hold(View view) {
        isHold = true;
//        while(computerTurn() && computerHand <= 21) {
//        }
        computerTurn();
        //Log.v("**************", ""+pla)

        if(computerTurn())
        {

        }
        else if(player_score < 21 && computerHand > player_score) {
            playerWon = false;
            gameOver();
        }
        else {
            playerWon = true;
            gameOver();
        }

    }

    public void hitT(View view) {
        if (deck.getDeckSize() == 0) {
            gameOver();
        } else {
            ImageView cardImage = (ImageView) findViewById(R.id.card_slot1);
            Card cardToDisplay = deck.getTopCard();
            cardImage.setImageResource(cardToDisplay.getImageID());
            TextView playerScore = (TextView) findViewById(R.id.p_score);

            if (cardToDisplay.getValue() == 1) {
                cardToDisplay.setValue(pickValOfAce());
            }

            player_score += cardToDisplay.getValue();
            playerScore.setText(String.valueOf(player_score));

            if (player_score == 21) {
                playerWon = true;
                gameOver();
            }
            else if (player_score > 21) {
                playerWon = false;
                gameOver();
            }
            else {
                computerTurn();
            }
        }

    }

    public void hitB(View view) {
        if (deck.getDeckSize() == 0) {
            gameOver();
        } else {
            ImageView cardImage = (ImageView) findViewById(R.id.card_slot1);
            Card cardToDisplay = deck.getBottomCard();
            cardImage.setImageResource(cardToDisplay.getImageID());
            TextView playerScore = (TextView) findViewById(R.id.p_score);

            if (cardToDisplay.getValue() == 1) {
                cardToDisplay.setValue(pickValOfAce());
            }

            player_score += cardToDisplay.getValue();
            playerScore.setText(String.valueOf(player_score));

            if (player_score == 21) {
                playerWon = true;
                gameOver();
            }
            else if (player_score > 21) {
                playerWon = false;
                gameOver();
            }
            else {
                computerTurn();
            }
        }

    }

    void gameOver() {
        String msg;
        TextView cpScore = (TextView) findViewById(R.id.cp_score);
        cpScore.setText(String.valueOf(computerHand));

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

        Toast.makeText(this, "CPU Score: "+computerHand, Toast.LENGTH_LONG).show();
        reset();
        cpScore.setText(String.valueOf(computerHand));


    }

    private void reset() {

        deck.reset();
        deck.shuffle();

        computerHand = 0;
        player_score = 0;
        computer_score = 0;
        isHold = false;

    }

    boolean computerTurn() {


        if (computerHand <= THRESHOLD) {
            Card card = deck.getTopCard();
            int cardVal = card.getValue();
            computerHand+= cardVal;
            if (cardVal != 1) {

                if (computerHand > 21) {
                    playerWon = true;
                    gameOver();
                } else if (computerHand < 21) {
                    return true;
                } else {
                    playerWon = false;
                    gameOver();
                }
            } else {
                computerHand-=cardVal;
                cardVal = (computerHand > 21) ? 1 : 11;
                    computerHand+= cardVal;
                if (computerHand > 21) {
                    playerWon = true;
                    gameOver();
                } else if (computerHand < 21) {
                    return true;
                } else {
                    playerWon = false;
                    gameOver();
                }

            }
        }

        return false;
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
