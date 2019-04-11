package com.example.benwr.reevelaapp.Utils;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.example.benwr.reevelaapp.Chat.ChatMainActivity2;
import com.example.benwr.reevelaapp.Chat.MessageActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import com.example.benwr.reevelaapp.Reveal.reveal_activity_test;
import com.example.benwr.reevelaapp.Settings.settings_activity;
import com.example.benwr.reevelaapp.Matches.matches_activity;
import com.example.benwr.reevelaapp.Games.games_activity;

import com.example.benwr.reevelaapp.R;

/**
 * __________________________________________________________________________
 *
 * Bottom navigation method used throughout the application.
 * __________________________________________________________________________
 *
 */

public class BottomNavigationViewHelper {

    public static void setupBottomNaviagtionView(BottomNavigationView bottomNavigationView){

    }

    public static void enableNavigation (final Context context, BottomNavigationView view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_eye:
                        Intent intentReveal = new Intent(context, reveal_activity_test.class);//ACTIVITY_0
                        context.startActivity(intentReveal);
                        break;

                    case R.id.nav_users:
                        Intent intentMatches = new Intent(context, ChatMainActivity2.class);//ACTIVITY_1
                        context.startActivity(intentMatches);
                        break;

                    case R.id.nav_games:
                        Intent intentGames = new Intent(context, games_activity.class);//ACTIVITY_2
                        context.startActivity(intentGames);
                        break;

                    case R.id.nav_settings:
                        Intent intentSettings = new Intent(context, settings_activity.class);//ACTIVITY_3
                        context.startActivity(intentSettings);
                        break;
                }


                return false;
            }
        });
    }
}
