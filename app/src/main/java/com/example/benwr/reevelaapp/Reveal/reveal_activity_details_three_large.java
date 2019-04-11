package com.example.benwr.reevelaapp.Reveal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.benwr.reevelaapp.Games.game_activity_fb;
import com.example.benwr.reevelaapp.Games.games_activity_newchall;
import com.example.benwr.reevelaapp.R;


/**
 * __________________________________________________________________________
 * <p>
 * Enlargement of third section.
 * __________________________________________________________________________
 */
public class reveal_activity_details_three_large extends AppCompatActivity {

    private static final String TAG = "reveal_activity_details";

    private Button stp_btn;
    private Button revmore_btn;
    private Button chall_Btn;

    private TextView randomUsername;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reveal_layout_section3_large);

        randomUsername = (TextView) findViewById(R.id.user_name);


        intent = getIntent();
        //Pass Username
        final String fakeUserName = intent.getStringExtra("fakeUserName");
        Log.d(TAG, "fakeUserName: " + fakeUserName);

        randomUsername.setText(fakeUserName);


        stp_btn = (Button) findViewById(R.id.stp_btn);
        stp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRevealActivity();
            }
        });

        revmore_btn = (Button) findViewById(R.id.revmore_btn);
        revmore_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRevealSavedActivity();
            }
        });

        //Challenge Button

        //Challenge Button

        chall_Btn = (Button) findViewById(R.id.challenge_Btn);

        chall_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(reveal_activity_details_three_large.this);

                builder.setCancelable(true);
                builder.setTitle("Challenge Time");
                builder.setMessage("Are you sure you want to challenge " + fakeUserName + "? " +
                        "You have 3 challenges remaining.");


                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Challenge", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent challintent = new Intent(reveal_activity_details_three_large.this, game_activity_fb.class);
                        challintent.putExtra("fakeUserName", fakeUserName);
                        Log.d(TAG, "Reveal3_Username: " + fakeUserName);
                        startActivity(challintent);
                        overridePendingTransition(0, 0);
                    }
                });
                builder.show();
            }
        });


        //Challenge Button
    }

    //Go Back to Reveal More Main Activity
    public void openRevealActivity() {
        Intent ncrevealintent = new Intent(this, reveal_activity_test.class);
        startActivity(ncrevealintent);
        overridePendingTransition(0, 0);
    }

    public void openRevealSavedActivity() {
        //Intent ncrevealmoreintent = new Intent(this, reveal_activity_detailsthree.class);
        //startActivity(ncrevealmoreintent);
        finish();
        overridePendingTransition(0, 0);
    }

}
