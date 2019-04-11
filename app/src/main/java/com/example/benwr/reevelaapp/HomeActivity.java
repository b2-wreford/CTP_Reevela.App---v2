package com.example.benwr.reevelaapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
//import com.example.benwr.reevelaapp.Old_Activities.reveal_activity;
import com.example.benwr.reevelaapp.Reveal.reveal_activity_test;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.util.Log;
import android.widget.Toast;

import com.example.benwr.reevelaapp.Login.login_activity;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;

public class HomeActivity extends AppCompatActivity {
    //FireBase Authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext = HomeActivity.this;

    private static final String TAG = "HomeActivity";

    //Circle Progress Bar
    private CircularProgressButton enter_BTN;
    //private Handler mWaitHandler = new Handler();


    //FUNCTIONALITY FOR WELCOME SCREEN
    private Button enter_button;
    RelativeLayout l1, l2;
    Button btnsub;
    Animation uptodown, downtoup, downtoup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Log.d(TAG, "onCreate: starting.");


        l1 = (RelativeLayout) findViewById(R.id.big_logo2);
        l2 = (RelativeLayout) findViewById(R.id.enter_button);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        //circularProgressButton.setAnimation(downtoup);
        //enter_button.setAnimation(downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);


        setupFirebaseAuth();


        enter_button = (Button) findViewById(R.id.btnEnter);
        enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityRevealActivity();
            }
        });
    }



    private void openActivityRevealActivity() {
        Intent intent = new Intent(this, reveal_activity_test.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }


    /**
     * -----------------------------------------FireBase Start-------------------------------------
     */


    /**
     * checks to see if the @param 'user' is logged in
     *
     * @param user
     */
    private void checkCurrentUser(FirebaseUser user) {
        Log.d(TAG, "chechCurrentUser: checking if user is logged in.");
        if (user == null) {
            Intent intent = new Intent(mContext, login_activity.class);
            startActivity(intent);

        }

    }

    /**
     * Setup FireBase Authentification
     */
    private void setupFirebaseAuth() {
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //check if the user is logged in
                checkCurrentUser(user);

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
        checkCurrentUser(mAuth.getCurrentUser());
        //updateUI(currentUser);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


/**
 * -----------------------------------------FireBase Stop-------------------------------------
 */

}



