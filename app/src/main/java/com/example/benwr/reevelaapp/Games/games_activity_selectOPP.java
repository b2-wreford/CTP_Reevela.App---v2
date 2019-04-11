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

import com.example.benwr.reevelaapp.Games.games_activity;
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
 * Loads in Firebase users and allows the current user to decide who they
 * want to challenge. Uses same methods as chat activity.
 * __________________________________________________________________________
 *
 */

public class games_activity_selectOPP extends AppCompatActivity {

    private static final String TAG = "games_activity_exgames";

    private Button nc_button;
    private Button chall_btn;

    private RecyclerView recyclerView;

    private UserGameAdapter userGameAdapter;
    private List<User> mUsers;

    //Search Users
    EditText search_users;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.games_layout_newchall_selopp);

        recyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUsers = new ArrayList<>();

        nc_button = (Button) findViewById(R.id.backbutton);
        nc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameActvityMain();
            }
        });

        chall_btn = (Button) findViewById(R.id.backbutton);
        chall_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ncgameintent = new Intent(games_activity_selectOPP.this, games_activity.class);
                startActivity(ncgameintent);
            }
        });


        readUsers();

        search_users = (EditText) findViewById(R.id.search_users);
        search_users.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                seachUsers(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void seachUsers(String s) {

        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("users").orderByChild("username")
                .startAt(s)
                .endAt(s + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    assert user != null;
                    assert fuser != null;
                    if (!user.getUser_id().equals(fuser.getUid())) {
                        mUsers.add(user);
                    }
                }

                userGameAdapter = new UserGameAdapter(games_activity_selectOPP.this, mUsers, false);   //False added in video
                recyclerView.setAdapter(userGameAdapter);
                overridePendingTransition(0, 0);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void readUsers() {

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Log.d(TAG, "userFragment Users: " + mUsers);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (search_users.getText().toString().equals("")) {


                    mUsers.clear();

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);


                        assert user != null;
                        assert firebaseUser != null;
                        if (!user.getUser_id().equals(firebaseUser.getUid())) {
                            mUsers.add(user);

                            Log.d(TAG, "userFragment Users loaded: " + mUsers);
                        }
                    }

                    userGameAdapter = new UserGameAdapter(games_activity_selectOPP.this, mUsers, false);  //false false
                    recyclerView.setAdapter(userGameAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void openGameActvityMain() {
        Intent ncgameintent = new Intent(this, games_activity.class);
        startActivity(ncgameintent);
    }
}