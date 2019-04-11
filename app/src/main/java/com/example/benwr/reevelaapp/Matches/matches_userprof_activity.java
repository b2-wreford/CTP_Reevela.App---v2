package com.example.benwr.reevelaapp.Matches;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.benwr.reevelaapp.Chat.MessageActivity;
import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.Utils.MessageAdapter;
import com.example.benwr.reevelaapp.models.Chat;
import com.example.benwr.reevelaapp.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class matches_userprof_activity extends AppCompatActivity {

    private static final String TAG = "matches_userprof_act";


    FirebaseUser fuser;
    DatabaseReference reference;

    Intent intent;

    private TextView user_name;


    private Button user_prof_open;
    private Button nc_button;

    private ImageView fb_imageview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matches_layout_user_profile);

        fb_imageview = (ImageView) findViewById(R.id.fb_imageview);
        user_name = (TextView) findViewById(R.id.user_name);


        nc_button = (Button) findViewById(R.id.backbutton);
        nc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openChatMatchesActivity();
            }
        });

//        ViewPager viewPager = findViewById(R.id.user_img);
//        matches_ImageSlider adapter = new matches_ImageSlider(this);
//        viewPager.setAdapter(adapter);

        intent = getIntent();

        //Pass User Image URL
        final String UserImageURL = intent.getStringExtra("UserImageURL");
        Log.d(TAG, "UserImageURL: " + UserImageURL);

        //Pass Username
        final String UsernamePass = intent.getStringExtra("UsernamePass");
        Log.d(TAG, "UsernamePass: " + UsernamePass);

        final String user_id = intent.getStringExtra("user_id");
        Log.d(TAG, "GetUserId" + user_id);

        final String imageURL = intent.getStringExtra("imageURL");
        Log.d(TAG, "GetImageURL: " + imageURL);

        fuser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users").child(fuser.getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);


                if (user.getImageURL().equals("default")) {
                    fb_imageview.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Glide.with(getApplicationContext()).load(UserImageURL).into(fb_imageview);
                    //Glide.with(MessageActivity.this).load(user.getImageURL()).into(profile_image);
                    user_name.setText(UsernamePass);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void openChatMatchesActivity() {
        Intent ncmatchesintent = new Intent(this, MessageActivity.class);
        finish();
        startActivity(ncmatchesintent);
        overridePendingTransition(0, 0);
    }

}
