package com.example.benwr.reevelaapp.Utils;

import android.content.Context;

import androidx.annotation.NonNull;

import android.util.Log;
import android.widget.Toast;

import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.models.User;
import com.example.benwr.reevelaapp.models.UserAccountSettings;
import com.example.benwr.reevelaapp.models.UserSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * __________________________________________________________________________
 * <p>
 * Vital methods for implementing firebase logic across the application.
 * Guidance from: https://codingwithmitch.com/
 * __________________________________________________________________________
 */

public class FirebaseMethods {
    private static final String TAG = "FirebaseMethods";

    //FireBase Authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private String userID;


    private Context mContext;

    public FirebaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //added.child
        myRef = mFirebaseDatabase.getReference();
        mContext = context;


        if (mAuth.getCurrentUser() != null) {
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    /**
     * Update User Account Settings
     */

    public void updateUserAccountSettings(String displayName, String website, String description, long location, long phoneNumber) {
        Log.d(TAG, "updateUserAccountSettings: updating user account settings");

        if (displayName != null) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userID)
                    .child(mContext.getString(R.string.field_display_name))
                    .setValue(displayName);
        }

        if (website != null) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userID)
                    .child(mContext.getString(R.string.field_website))
                    .setValue(website);
        }
        if (description != null) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userID)
                    .child(mContext.getString(R.string.field_description))
                    .setValue(description);
        }
        if (location != 0) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userID)
                    .child(mContext.getString(R.string.field_location))
                    .setValue(location);
        }
        if (phoneNumber != 0) {
            myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                    .child(userID)
                    .child(mContext.getString(R.string.field_phone_number))
                    .setValue(phoneNumber);
        }

    }


    /**
     * Update username in  the User node and UserAccountserttings Node
     *
     * @param username
     */

    public void updateUsername(String username) {
        Log.d(TAG, "updateUsername: updating username to:" + username);

        myRef.child(mContext.getString(R.string.dbname_users))
                .child(userID)
                .child(mContext.getString(R.string.field_username))
                .setValue(username);

        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .child(mContext.getString(R.string.field_username))
                .setValue(username);
    }


    /**
     * Rgister new email and password to firebase Auth.
     *
     * @param email
     * @param password
     * @param username
     */

    public void registerNewEmail(final String email, String password, final String username) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(mContext, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();


                        } else if (task.isSuccessful()) {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:success", task.getException());

                            //send Verification Email
                            sendVerificationEmail();

                            //FirebaseUser user = mAuth.getCurrentUser();
                            userID = mAuth.getCurrentUser().getUid();

                            //FirebaseUser userID = mAuth.getCurrentUser();
                            Log.d(TAG, "onComplete: Authstate changed: " + userID);

                            // ...
                        }
                    }
                });
    }


    /*
        SendVerificationEmail to new users
     */

    public void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        //if (user != null){
        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: email sent");
                            Toast.makeText(mContext, "Sent verification email.", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(mContext, "Couldn't send verification email.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    //Will create new node on database under "users" node
    public void addNewUser(String email, String username, String description, String website, String profile_photo, String image_URL, String status) {  //String status
        Log.d(TAG, "addNewUser: user " + username);
        User user = new User(userID, 1, email, StringManipulations.condenseUsername(username), image_URL, status);

        myRef.child(mContext.getString(R.string.dbname_users))
                .child(userID)
                .setValue(user);

        UserAccountSettings settings = new UserAccountSettings(
                description,
                username,
                1,
                profile_photo,
                StringManipulations.condenseUsername(username),
                website
        );

        myRef.child(mContext.getString(R.string.dbname_user_account_settings))
                .child(userID)
                .setValue(settings);

    }

    /**
     * Retrieve account settings for user logged in
     * Database; UserAccountSettings Node
     *
     * @param dataSnapshot
     * @return
     */
    public UserSettings getUserSettings(DataSnapshot dataSnapshot) {
        Log.d(TAG, "getUserAccountSettings: retrieve user account settings from FireBase");

        UserAccountSettings settings = new UserAccountSettings();
        User user = new User();

        for (DataSnapshot ds : dataSnapshot.getChildren()) {

            //UserAccountSettingsNode
            if (ds.getKey().equals(mContext.getString(R.string.dbname_user_account_settings))) {

                Log.d(TAG, "getUserAccountSettings: datasnapshot" + ds);  //add .child(userID) to see more

                try {

                    settings.setDescription(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getDescription()
                    );

                    settings.setDisplay_name(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getDisplay_name()
                    );

                    settings.setLocation(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getLocation()
                    );

                    settings.setProfile_photo(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getProfile_photo()
                    );

                    settings.setUsername(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getUsername()
                    );

                    settings.setWebsite(
                            ds.child(userID)
                                    .getValue(UserAccountSettings.class)
                                    .getWebsite()
                    );

                    Log.d(TAG, "getUserAccountSettings: retrieved user account settings" + settings.toString());
                } catch (NullPointerException e) {
                    Log.e(TAG, "getUserAccountSettings: NullPointerException" + e.getMessage());
                }
            }

            //User Node
            if (ds.getKey().equals(mContext.getString(R.string.dbname_users))) {

                Log.d(TAG, "getUserAccountSettings: datasnapshot" + ds);  //add .child(userID) to see more

                user.setUsername(
                        ds.child(userID)
                                .getValue(User.class)
                                .getUsername()
                );

                user.setEmail(
                        ds.child(userID)
                                .getValue(User.class)
                                .getEmail()
                );

                user.setPhone_number(
                        ds.child(userID)
                                .getValue(User.class)
                                .getPhone_number()
                );

                user.setUser_id(
                        ds.child(userID)
                                .getValue(User.class)
                                .getUser_id()
                );

                user.setImageURL(
                        ds.child(userID)
                                .getValue(User.class)
                                .getImageURL()
                );


                Log.d(TAG, "getUserAccountSettings: retrieved user info" + user.toString());


            }
        }

        return new UserSettings(user, settings);
    }

}













