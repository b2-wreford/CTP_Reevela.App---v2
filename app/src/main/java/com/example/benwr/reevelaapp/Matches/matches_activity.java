package com.example.benwr.reevelaapp.Matches;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.benwr.reevelaapp.Chat.ChatMainActivity2;
import com.example.benwr.reevelaapp.Chat.StartActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
//import com.example.benwr.reevelaapp.RecylerView.RecyclerViewActivity;
//import com.example.benwr.reevelaapp.RecylerView.RecyclerViewAdapter;

import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.Utils.BottomNavigationViewHelper;

public class matches_activity extends AppCompatActivity {

    private Button chat_opn;
    private Context mContext = matches_activity.this;
    private static final int ACTIVITY_REF = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_layout_main);


        chat_opn = (Button) findViewById(R.id.example_us1);
        chat_opn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatActivity();
            }
        });


        setupBottomNavigationView();
    }

    public void openChatActivity() {
        Intent chaintent = new Intent(this, ChatMainActivity2.class);
        startActivity(chaintent);
        overridePendingTransition(0, 0);
    }

    //_______________________________
    //BottomNavigationViewSetUp
    //_______________________________

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        overridePendingTransition(0, 0);

    }
}
