package com.example.benwr.reevelaapp.Games;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.benwr.reevelaapp.R;

/**
 * __________________________________________________________________________
 * <p>
 * New Challenge, opens up select opponent.
 * __________________________________________________________________________
 */

public class games_activity_newchall extends AppCompatActivity {

    private Button nc_button;
    private Button snake_btn;
    private Button airh_btn;
    private Button TTT_btn;
    private Button triv_btn;
    private Button bj_btn;
    private Button riddle_btn;
    private Button shoot_btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_layout_newchall);

        nc_button = (Button) findViewById(R.id.backbutton);
        nc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityMain();
            }
        });

        snake_btn = (Button) findViewById(R.id.snake_btn);
        snake_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSnake();
            }
        });

        airh_btn = (Button) findViewById(R.id.airh_btn);
        airh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAirH();
            }
        });

        TTT_btn = (Button) findViewById(R.id.TTT_btn);
        TTT_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTTT();
            }
        });

        triv_btn = (Button) findViewById(R.id.triv_btn);
        triv_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTriv();
            }
        });

        bj_btn = (Button) findViewById(R.id.bj_btn);
        bj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBJ();
            }
        });

        riddle_btn = (Button) findViewById(R.id.riddle_btn);
        riddle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRiddle();
            }
        });

        shoot_btn = (Button) findViewById(R.id.shoot_btn);
        shoot_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShoot();
            }
        });

    }

    //CHANGE SO IT IS JUST ONE PUBLIC VOID

    public void openGameActvityMain() {
        Intent ncgameintent = new Intent(this, games_activity.class);
        startActivity(ncgameintent);
        overridePendingTransition(0, 0);
    }

    public void openSnake() {
        Intent snakeintent = new Intent(this, games_activity_selectOPP.class);
        startActivity(snakeintent);
        overridePendingTransition(0, 0);
    }

    public void openAirH() {
        Intent airHintent = new Intent(this, games_activity_selectOPP.class);
        startActivity(airHintent);
        overridePendingTransition(0, 0);
    }

    public void openTTT() {
        Intent TTTintent = new Intent(this, game_activity_TTT.class);
        startActivity(TTTintent);
        overridePendingTransition(0, 0);
    }

    public void openTriv() {
        Intent trivintent = new Intent(this, games_activity_selectOPP.class);
        startActivity(trivintent);
        overridePendingTransition(0, 0);
    }

    public void openBJ() {
        Intent bjintent = new Intent(this, games_activity_selectOPP.class);
        startActivity(bjintent);
        overridePendingTransition(0, 0);
    }

    public void openRiddle() {
        Intent riddintent = new Intent(this, games_activity_selectOPP.class);
        startActivity(riddintent);
        overridePendingTransition(0, 0);
    }

    public void openShoot() {
        Intent shootintent = new Intent(this, games_activity_selectOPP.class);
        startActivity(shootintent);
        overridePendingTransition(0, 0);
    }

}