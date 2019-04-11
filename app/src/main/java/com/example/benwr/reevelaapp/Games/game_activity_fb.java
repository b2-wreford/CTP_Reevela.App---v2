package com.example.benwr.reevelaapp.Games;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.Reveal.reveal_activity_test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class game_activity_fb extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "game_activity_fb";

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;
    private int player2Points;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    private Button nc_button;
    private Button strt_btn;
    private RelativeLayout instructions;

    private Intent intent;

    private String UserName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_layout_newchall_fb);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);

        nc_button = (Button) findViewById(R.id.backbutton);
        nc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityMain();
            }
        });

        instructions = (RelativeLayout) findViewById(R.id.instructions);

        strt_btn = (Button) findViewById(R.id.strt_btn);
        strt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                instructions.setVisibility(View.GONE);

            }
        });


        intent = getIntent();
        //Pass Username
        final String fakeUserName = intent.getStringExtra("fakeUserName");
        Log.d(TAG, "fakeUserName: " + fakeUserName);

        textViewPlayer2.setText(fakeUserName + ": ");

        UserName = fakeUserName;
        Log.d(TAG, "UserName: " + UserName + fakeUserName);


        /**
         * __________________________________________________________________________
         *
         * TIC TAC TOE LOGIC
         * __________________________________________________________________________
         *
         */


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

    }

    /**
     * Check Turn and set Text on click
     */

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
            ((Button) v).setTextColor(this.getResources().getColor(R.color.darkpink));
            //textViewPlayer1.setTextColor(this.getResources().getColor(R.color.darkpink));

        } else {
            ((Button) v).setText("O");
            //textViewPlayer2.setTextColor(this.getResources().getColor(R.color.appblue));
            ((Button) v).setTextColor(this.getResources().getColor(R.color.appblue));
        }

        //Increment Round Count
        roundCount++;

        //Implement Boolean, check for player win
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins();
            } else {
                player2Wins();
            }

            //If 9 rounds are over we will know it is a draw
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }

    }

    private boolean checkForWin() {
        String[][] field = new String[3][3];

        //Check for 3 matches across
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        //Compare 3 field and check if empty
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }

        //Check for matches downwards
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }


        //Check for diagonal wins - left to right
        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        //Check for diagonal wins - right ot left
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }


    private void player1Wins() {
        player1Points++;
        Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void player2Wins() {

        player2Points++;
        Toast.makeText(this, UserName + " wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void draw() {

        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }


    private void updatePointsText() {
        textViewPlayer1.setText("You: " + player1Points);
        textViewPlayer2.setText(UserName + ": " + player2Points);
        winner();
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        updatePointsText();
        resetBoard();
    }

    private void winner() {

        if (player1Points == 2) {
            Toast.makeText(this, "Overall Winner is you", Toast.LENGTH_SHORT).show();
            resetGame();
            doubleRevealWin();
        } else if (player2Points == 2) {
            Toast.makeText(this, "Overall Winner is " + UserName, Toast.LENGTH_SHORT).show();
            resetGame();
            doubleRevealLose();
        }


    }

    /**
     * __________________________________________________________________________
     *
     * Use Builder to implement pop up alert on screen.
     * __________________________________________________________________________
     *
     */

    public void doubleRevealWin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(game_activity_fb.this);

        builder.setCancelable(false);
        builder.setTitle("You Won!");
        builder.setMessage("You can now double reveal " + UserName + "!" +
                " " +
                "Do you want to double reveal?");


        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }
        });

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent challintent = new Intent(game_activity_fb.this, reveal_activity_test.class);
                startActivity(challintent);
                overridePendingTransition(0, 0);
            }
        });
        builder.show();

    }

    /**
     * __________________________________________________________________________
     *
     * Allow the user to double reveal once game has finished.
     * __________________________________________________________________________
     *
     */

    public void doubleRevealLose() {
        AlertDialog.Builder builder = new AlertDialog.Builder(game_activity_fb.this);

        builder.setCancelable(true);
        builder.setTitle("You Lost...");
        builder.setMessage(UserName + " can now double reveal you.. ");


        builder.setPositiveButton("Leave Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent challintent = new Intent(game_activity_fb.this, reveal_activity_test.class);
                startActivity(challintent);
                overridePendingTransition(0, 0);
            }
        });
        builder.show();

    }

    /**
     * __________________________________________________________________________
     *
     * Save changes to score if screen is rotated
     * __________________________________________________________________________
     *
     */


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1Points", player1Points);
        outState.putInt("player2Points", player2Points);
        outState.putBoolean("player1Turn", player1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount = savedInstanceState.getInt("roundCount");
        player1Points = savedInstanceState.getInt("player1Points");
        player2Points = savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");
    }


    public void openGameActvityMain() {
        Intent ncgameintent = new Intent(this, reveal_activity_test.class);
        startActivity(ncgameintent);
        overridePendingTransition(0, 0);
    }
}