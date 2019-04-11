package com.example.benwr.reevelaapp.Games;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.benwr.reevelaapp.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * __________________________________________________________________________
 *
 * Allow the user to chose what game they want to play.
 * Currently only one available option - TIC TAC TOE
 * __________________________________________________________________________
 *
 */

public class games_activity_selectGAME extends AppCompatActivity {

    private static final String TAG = "games_activity_selectGA";


    private Button TTT_btn;
    private Button back_button;

    private Intent intent;

    private String userid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_layout_prefgames);

        back_button = (Button) findViewById(R.id.backbutton);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityMain();
            }
        });


        //TicTacToe Button Preference
        TTT_btn = (Button) findViewById(R.id.TTT_btn);
        TTT_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openTTT();

        }
    });


       intent = getIntent();
        //Pass Username
        userid = intent.getStringExtra("user_id");
        Log.d(TAG, "User_ID: " + userid);
    }

    public void openTTT(){
        Intent TTTintent = new Intent(this, game_activity_TTT.class);
        TTTintent.putExtra("user_id", userid);
        startActivity(TTTintent);
        overridePendingTransition(0,0);
    }


    public void openGameActvityMain(){
        Intent ncgameintent = new Intent(this, games_activity_selectOPP.class);
        startActivity(ncgameintent);
        overridePendingTransition(0,0);
    }
}