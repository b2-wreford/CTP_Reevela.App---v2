package com.example.benwr.reevelaapp.Matches;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.example.benwr.reevelaapp.R;

public class matches_chat_activity extends AppCompatActivity {

    private Button user_prof_open;
    private Button nc_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_layout_userchat);


        nc_button = (Button) findViewById(R.id.backbutton);
        nc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMatchesActivity();
            }
        });

        user_prof_open = (Button) findViewById(R.id.user_profile_link_btn);
        user_prof_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserProfActivity();
            }
        });

    }


    public void openUserProfActivity() {
        Intent userprofintent = new Intent(this, matches_userprof_activity.class);
        startActivity(userprofintent);
        overridePendingTransition(0, 0);
    }

    public void openMatchesActivity() {
        Intent ncmatchesintent = new Intent(this, matches_activity.class);
        startActivity(ncmatchesintent);
        overridePendingTransition(0, 0);
    }
}