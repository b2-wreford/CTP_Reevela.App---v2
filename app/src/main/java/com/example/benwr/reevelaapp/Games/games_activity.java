package com.example.benwr.reevelaapp.Games;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.Utils.BottomNavigationViewHelper;

public class games_activity extends AppCompatActivity {

    private Button opennc_game;
    private Button ex_game;
    private Button pref_game;

    private Context mContext = games_activity.this;
    private static final  int ACTIVITY_REF = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_layout_main);



        opennc_game = (Button) findViewById(R.id.newc_button);
        opennc_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityNewChallenge();
            }
        });

        ex_game = (Button) findViewById(R.id.ex_game_button);
        ex_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityExGames();
            }
        });

        pref_game = (Button) findViewById(R.id.preferences_button);
        pref_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityPrefGames();
            }
        });

        setupBottomNavigationView();

    }

    public void openGameActvityNewChallenge(){
        Intent ncopengameintent = new Intent(this, games_activity_selectOPP.class);
        startActivity(ncopengameintent);
        overridePendingTransition(0,0);


    }

    public void openGameActvityExGames() {
        Intent exopengameintent = new Intent(this, games_activity_exgames.class);
        startActivity(exopengameintent);
        overridePendingTransition(0,0);


    }

    public void openGameActvityPrefGames() {
        Intent prefopengameintent = new Intent(this, games_activity_prefgames.class);
        startActivity(prefopengameintent);
        overridePendingTransition(0,0);


    }


    //_______________________________
    //BottomNavigationViewSetUp
    //_______________________________

    private void setupBottomNavigationView(){
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        overridePendingTransition(0,0);

    }
}
