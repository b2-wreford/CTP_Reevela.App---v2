package com.example.benwr.reevelaapp.Games;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.benwr.reevelaapp.R;

public class games_activity_prefgames extends AppCompatActivity {

    private Button nc_button;
    private Button snake_btn;
    private Button airh_btn;
    private Button TTT_btn;
    private Button triv_btn;
    private Button bj_btn;
    private Button riddle_btn;
    private Button shoot_btn;
    private boolean isButtonClicked = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_layout_prefgames);

        nc_button = (Button) findViewById(R.id.backbutton);
        nc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityMain();
            }
        });


        //Snake Button Preference
        snake_btn = (Button) findViewById(R.id.snake_btn);
        snake_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.snake_btn) {
                    isButtonClicked = !isButtonClicked; // toggle the boolean flag
                    v.setBackgroundResource(isButtonClicked ? R.drawable.rounded_buttonpref : R.drawable.rounded_buttonthin);
                }
            }
        });
        //Air Hockey Button Preference
        airh_btn = (Button) findViewById(R.id.airh_btn);
        airh_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.airh_btn) {
                    isButtonClicked = !isButtonClicked; // toggle the boolean flag
                    v.setBackgroundResource(isButtonClicked ? R.drawable.rounded_buttonpref : R.drawable.rounded_buttonthin);
                }
            }
        });

        //TicTacToe Button Preference
        TTT_btn = (Button) findViewById(R.id.TTT_btn);
        TTT_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.TTT_btn) {
                    isButtonClicked = !isButtonClicked; // toggle the boolean flag
                    v.setBackgroundResource(isButtonClicked ? R.drawable.rounded_buttonpref : R.drawable.rounded_buttonthin);
                }
            }
        });

        //Trivea Button Preference
        triv_btn = (Button) findViewById(R.id.triv_btn);
        triv_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.triv_btn) {
                    isButtonClicked = !isButtonClicked; // toggle the boolean flag
                    v.setBackgroundResource(isButtonClicked ? R.drawable.rounded_buttonpref : R.drawable.rounded_buttonthin);
                }
            }
        });

        //BlackJack Button Preference
        bj_btn = (Button) findViewById(R.id.bj_btn);
        bj_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.bj_btn) {
                    isButtonClicked = !isButtonClicked; // toggle the boolean flag
                    v.setBackgroundResource(isButtonClicked ? R.drawable.rounded_buttonpref : R.drawable.rounded_buttonthin);
                }
            }
        });

        //Riddle Button Preference
        riddle_btn = (Button) findViewById(R.id.riddle_btn);
        riddle_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.riddle_btn) {
                    isButtonClicked = !isButtonClicked; // toggle the boolean flag
                    v.setBackgroundResource(isButtonClicked ? R.drawable.rounded_buttonpref : R.drawable.rounded_buttonthin);
                }
            }
        });

        //Shoot Button Preference
        shoot_btn = (Button) findViewById(R.id.shoot_btn);
        shoot_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (v.getId() == R.id.shoot_btn) {
                    isButtonClicked = !isButtonClicked; // toggle the boolean flag
                    v.setBackgroundResource(isButtonClicked ? R.drawable.rounded_buttonpref : R.drawable.rounded_buttonthin);
                }
            }
        });


    }

    public void openGameActvityMain() {
        Intent ncgameintent = new Intent(this, games_activity.class);
        startActivity(ncgameintent);
        overridePendingTransition(0, 0);
    }
}