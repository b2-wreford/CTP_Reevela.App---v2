package com.example.benwr.reevelaapp.Games;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.Utils.UserGameAdapter;
import com.example.benwr.reevelaapp.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * __________________________________________________________________________
 *
 * Existing games activity.
 * __________________________________________________________________________
 *
 */


public class games_activity_exgames extends AppCompatActivity {

    private static final String TAG = "games_activity_exgames";

    private Button nc_button;
    private Button chall_btn;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_layout_exgames);



        nc_button = (Button) findViewById(R.id.backbutton);
        nc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityMain();
            }
        });

        chall_btn = (Button) findViewById(R.id.chall_btn);
        chall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ncgameintent = new Intent(games_activity_exgames.this, games_activity_selectOPP.class);
                startActivity(ncgameintent);
            }
        });


    }

    

    public void openGameActvityMain(){
        Intent ncgameintent = new Intent(this, games_activity.class);
        startActivity(ncgameintent);
    }
}