package com.example.benwr.reevelaapp.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import com.example.benwr.reevelaapp.BoardingScreenActivity;
import com.example.benwr.reevelaapp.HomeActivity;
import com.example.benwr.reevelaapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * __________________________________________________________________________
 *
 * Login Activity for existing users. Implements a handful of fire
 * base methods
 * __________________________________________________________________________
 *
 */

public class login_activity extends AppCompatActivity {

    private static String TAG = "LoginActivity";

    //FireBase Authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private Context mContext;
    private ProgressBar mProgressBar;
    private EditText mEmail, mPassword;
    private TextView mPleaseWait;
    private ImageView mLogoApp;
    private Button btnLogin;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout_activity);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mPleaseWait = (TextView) findViewById(R.id.pls_wait);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPassword = (EditText) findViewById(R.id.input_password);
        mLogoApp = (ImageView) findViewById(R.id.loadinglogo);
        btnLogin = (Button) findViewById(R.id.btn_login);
        mContext = login_activity.this;
        Log.d(TAG, "OnCreate: started");

        mLogoApp.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mPleaseWait.setVisibility(View.GONE);


        //Hide Keyboard On Click

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPassword.onEditorAction(EditorInfo.IME_ACTION_DONE);
                mEmail.onEditorAction(EditorInfo.IME_ACTION_DONE);
            }
        });


        setupFirebaseAuth();
        init();


    }

    private boolean isStringNull(String string) {
        if (string.equals("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * -----------------------------------------FireBase Start-------------------------------------
     */


    private void init() {

        //initialize button for logging in
        Button btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onclick: attempting to login");

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if (isStringNull(email) && isStringNull(password)) {
                    Toast.makeText(mContext, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    mLogoApp.setVisibility(View.INVISIBLE);
                    mProgressBar.setVisibility(View.VISIBLE);
                    mPleaseWait.setVisibility(View.VISIBLE);


                    //Sign in exisitng Users
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "signInWithEmail:onComplete" + task.isSuccessful());

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //If user email verification is verified.


                                    if (!task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.w(TAG, "signInWithEmail:failed" + task.getException());
//                                      FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(login_activity.this, R.string.auth_failed,
                                                Toast.LENGTH_SHORT).show();

                                    } else {
                                        try {
                                            if (user.isEmailVerified()) {
                                                Log.d(TAG, "onComplete: success");
                                                Intent intent = new Intent(login_activity.this, BoardingScreenActivity.class);
                                                startActivity(intent);
                                            } else {
                                                Toast.makeText(mContext, "Email is not verified", Toast.LENGTH_SHORT).show();
                                                mProgressBar.setVisibility(View.GONE);
                                                mPleaseWait.setVisibility(View.GONE);
                                                mAuth.signOut();
                                            }
                                        } catch (NullPointerException e) {
                                            Log.e(TAG, "onComplete: NullPointerException:" + e.getMessage());
                                        }

                                    }

                                    // ...
                                }
                            });
                }
            }
        });

        TextView linkSignUp = (TextView) findViewById(R.id.link_signup);
        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login_activity.this, register_activity.class);
                startActivity(intent);
            }
        });


        /**
         * if user is logged in then navigate to home activity and call finish.
         */
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(login_activity.this, HomeActivity.class);
            startActivity(intent);
            finish();
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
