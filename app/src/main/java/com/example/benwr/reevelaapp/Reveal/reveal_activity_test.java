package com.example.benwr.reevelaapp.Reveal;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.benwr.reevelaapp.Games.games_activity_newchall;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.Utils.BottomNavigationViewHelper;

import java.util.Random;


/**
 * __________________________________________________________________________
 * <p>
 * Main activity for revealing user profiles. Not able to fully implement
 * live users to dummy profiles were used to demonstrate functionality.
 * __________________________________________________________________________
 */
public class reveal_activity_test extends AppCompatActivity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {


    private static final String TAG = "reveal_activity_test";


    private Button btnCreate1;
    private Button btnCreate2;
    private Button btnCreate3;

    private TextView user_name;

    private String fakeUserName;
    private String fakeUserName2;
    private Integer fakeUserImg1;
    private Integer fakeUserImg2;

    private String matchingImg;
    private Integer selectedImg;


    private boolean isButtonClick;


    //Random Image Picker
    Random r;
    int pickedImage = 0, lastPicked, selfie1Pair1 = 0, selfie2Pair1 = 1;
    int selfie1Pair2 = 2, selfie2Pair2 = 3, selfie1Pair3 = 4, selfie2Pair3 = 5;
    int selfie1Pair4 = 6, selfie2Pair4 = 7, finalImage = 0, finalImageTwo = 0;

    Integer[] fake_user_images = {

            //Pair
            R.drawable.user_selfie1,
            R.drawable.user_selfie2,

            //Pair
            R.drawable.user_selfie6,
            R.drawable.user_selfie8,

            //Pair
            R.drawable.user_selfie7,
            R.drawable.user_selfie11,

            //Pair
            R.drawable.selfie,
            R.drawable.selfie2


    };


    //Random Name Picker
    Random nameR;
    int FirstnameR = 0, LastnameR = 0;

    String[] fake_user_names = new String[]{

            "Maya",
            "Steph",
            "Emma",
            "Olivia",
            "Ava",
            "Isabella",
            "Sophia",
            "Mia",
            "Charlotte",
            "Amelia"

    };


    //Section 1
    private RelativeLayout reveal_1S, reveal_1US;
    private Button enlarge_1;
    private Button reveal_btn1, reveal_btn1big;
    private ImageView circ1_img, circ1_imgRandom;


    //Section 2
    private RelativeLayout reveal_2S, reveal_2US;
    private Button enlarge_2, reveal_btn2big;
    private ImageView circ2_img, circ2_imgRandom;

    //Section 3
    private RelativeLayout reveal_3S, reveal_3US;
    private Button enlarge_3, reveal_btn3big;
    private TextView location_field;

    private Context mContext = reveal_activity_test.this;
    private static final int ACTIVITY_REF = 0;

    //Constance for Saving Data
    public static final String SHARED_PREF = "sharedPrefs";
    public static final String SHARED_IMAGE = "sharedImage";
    public static final String SWITCH = "switch1";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reveal_layout_newmain);

        setupBottomNavigationView();


        //Random Image/Name Picker

        r = new Random();
        nameR = new Random();

        user_name = (TextView) findViewById(R.id.user_name);


        //--------------------------------Image 1 Settings------------------------------------------
        //Reveal One

        //Double Tap
        reveal_btn1 = (Button) findViewById(R.id.reveal_btn1);
        reveal_btn1big = (Button) findViewById(R.id.reveal_btn1big);


        reveal_1S = (RelativeLayout) findViewById(R.id.reveal_1_seen); // can see details
        reveal_1US = (RelativeLayout) findViewById(R.id.reveal_1_unseen); // can not see details

        final ImageView circ1_img = findViewById(R.id.circ1_img);


        reveal_1S.setVisibility(View.INVISIBLE);
        //reveal_1US.setVisibility(View.VISIBLE);

        enlarge_1 = (Button) findViewById(R.id.reveal_btn1big);
        //enlarge_1.setVisibility(View.INVISIBLE);

        reveal_btn1big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img1intent = new Intent(reveal_activity_test.this, reveal_activity_large_image.class);

                img1intent.putExtra("fakeUserImg1", fakeUserImg1);
                Log.d(TAG, "fakeUserImg1: " + fakeUserImg1);

                img1intent.putExtra("fakeUserName", fakeUserName);
                Log.d(TAG, "fakeUserName: " + fakeUserName);

                ActivityOptionsCompat img1options = ActivityOptionsCompat.makeSceneTransitionAnimation(reveal_activity_test.this, circ1_img, getString(R.string.userTransImg1));
                getWindow().setExitTransition(null);
                startActivity(img1intent, img1options.toBundle());
            }
        });

        btnCreate1 = (Button) findViewById(R.id.reveal_btn1);

        btnCreate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_One(v);
            }
        });

        //To generate random Image

        circ1_imgRandom = (ImageView) findViewById(R.id.circ1_img);
        circ2_imgRandom = (ImageView) findViewById(R.id.circ2_img);

//


        //--------------------------------Image 2 Settings------------------------------------------
        //Reveal Two
        reveal_2S = (RelativeLayout) findViewById(R.id.reveal_2_seen); // can see details
        reveal_2US = (RelativeLayout) findViewById(R.id.reveal_2_unseen); // can not see details

        reveal_2S.setVisibility(View.INVISIBLE);
        //reveal_2US.setVisibility(View.VISIBLE);

        final ImageView circ2_img = findViewById(R.id.circ2_img);


        btnCreate2 = (Button) findViewById(R.id.reveal_btn2);

        btnCreate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_Two(v);
            }
        });

        enlarge_2 = (Button) findViewById(R.id.reveal_btn2big);
        //enlarge_1.setVisibility(View.INVISIBLE);

        enlarge_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img1intent = new Intent(reveal_activity_test.this, reveal_activity_large_imagetwo.class);

                img1intent.putExtra("fakeUserImg2", fakeUserImg2);
                Log.d(TAG, "fakeUserImg2: " + fakeUserImg2);


                ActivityOptionsCompat img1options = ActivityOptionsCompat.makeSceneTransitionAnimation(reveal_activity_test.this, circ2_img, getString(R.string.userTransImg2));
                getWindow().setExitTransition(null);
                startActivity(img1intent, img1options.toBundle());
            }
        });


        //--------------------------------Image 3 Settings------------------------------------------
        //Reveal Three

        reveal_3S = (RelativeLayout) findViewById(R.id.reveal_3_seen); // can see details
        reveal_3US = (RelativeLayout) findViewById(R.id.reveal_3_unseen); // can not see details

        reveal_3S.setVisibility(View.INVISIBLE);
        //reveal_3US.setVisibility(View.VISIBLE);

        btnCreate3 = (Button) findViewById(R.id.reveal_btn3);

        btnCreate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reveal_Three(v);
            }
        });


        final TextView user_name = findViewById(R.id.user_name);


        enlarge_3 = (Button) findViewById(R.id.reveal_btn3big);
        //enlarge_1.setVisibility(View.INVISIBLE);


        enlarge_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent img1intent = new Intent(reveal_activity_test.this, reveal_activity_details_three_large.class);

                img1intent.putExtra("fakeUserName", fakeUserName);
                Log.d(TAG, "fakeUserName: " + fakeUserName);

                ActivityOptionsCompat img1options = ActivityOptionsCompat.makeSceneTransitionAnimation(reveal_activity_test.this, user_name, getString(R.string.userTrans));
                //ActivityOptionsCompat img1options2 = ActivityOptionsCompat.makeSceneTransitionAnimation(reveal_activity_test.this, location_field, ViewCompat.getTransitionName(location_field));
                getWindow().setExitTransition(null);
                startActivity(img1intent, img1options.toBundle());


                //overridePendingTransition(R.anim.downtoup, R.anim.downtoup);
            }
        });


    }


    public void reveal_One(View view) {

        //Display Random User Image
        do {
            pickedImage = r.nextInt(fake_user_images.length);


        } while (pickedImage == finalImageTwo);

        //lastPicked = pickedImage;

        if (lastPicked == 0) finalImageTwo = selfie2Pair1;

        else if (lastPicked == 1) finalImageTwo = selfie1Pair1;

        else if (lastPicked == 2) finalImageTwo = selfie2Pair2;

        else if (lastPicked == 3) finalImageTwo = selfie1Pair2;

        else if (lastPicked == 4) finalImageTwo = selfie2Pair3;

        else if (lastPicked == 5) finalImageTwo = selfie1Pair3;

        else if (lastPicked == 6) finalImageTwo = selfie2Pair4;

        else if (lastPicked == 7) finalImageTwo = selfie1Pair4;

        lastPicked = finalImageTwo;

        circ1_imgRandom.setImageResource(fake_user_images[pickedImage]);

        Log.d(TAG, "finalImg: " + pickedImage);

        fakeUserImg1 = fake_user_images[pickedImage];
        Log.d(TAG, "Image: " + fakeUserImg1);
        Log.d(TAG, "reveal_One: " + pickedImage);

        fakeUserName = fake_user_names[FirstnameR];


        if (isButtonClick = true) {
            reveal_1S.setVisibility(View.VISIBLE);
            reveal_1US.setVisibility(View.INVISIBLE);
        } else {

        }


    }


    public void reveal_Two(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);


        do {
            lastPicked = r.nextInt(fake_user_images.length);

        } while (lastPicked == finalImage);

        if (pickedImage == 0) finalImage = selfie2Pair1;

        else if (pickedImage == 1) finalImage = selfie1Pair1;

        else if (pickedImage == 2) finalImage = selfie2Pair2;

        else if (pickedImage == 3) finalImage = selfie1Pair2;

        else if (pickedImage == 4) finalImage = selfie2Pair3;

        else if (pickedImage == 5) finalImage = selfie1Pair3;

        else if (pickedImage == 6) finalImage = selfie2Pair4;

        else if (pickedImage == 7) finalImage = selfie1Pair4;


        pickedImage = finalImage;
        Log.d(TAG, "finalImg: " + finalImage);
        circ2_imgRandom.setImageResource(fake_user_images[pickedImage]);

        fakeUserImg2 = fake_user_images[pickedImage];
        Log.d(TAG, "Image: " + fakeUserImg2);
        Log.d(TAG, "reveal_One: " + pickedImage);

        fakeUserName = fake_user_names[FirstnameR];
        Log.d(TAG, "reveal_Two: " + fakeUserName);


        if (isButtonClick = true) {
            reveal_2S.setVisibility(View.VISIBLE);
            reveal_2US.setVisibility(View.INVISIBLE);
        } else {

        }
    }


    public void reveal_Three(View view) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, view, "transition");
        int revealX = (int) (view.getX() + view.getWidth() / 2);
        int revealY = (int) (view.getY() + view.getHeight() / 2);


        //Display Random User Name
        do {
            FirstnameR = nameR.nextInt(fake_user_names.length);

        } while (FirstnameR == LastnameR);

        LastnameR = FirstnameR;
        user_name.setText(fake_user_names[FirstnameR]);

        //fake_user_names[FirstnameR] = fakeUserName;
        fakeUserName = fake_user_names[FirstnameR];
        Log.d(TAG, "revealed_name: " + fakeUserName);


        if (isButtonClick = true) {
            reveal_3S.setVisibility(View.VISIBLE);
            reveal_3US.setVisibility(View.INVISIBLE);
        } else {

        }
    }

    //_______________________________
    //BottomNavigationViewSetUp
    //_______________________________

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        overridePendingTransition(0, 0);
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        Intent challintent = new Intent(reveal_activity_test.this, reveal_activity_large_image.class);
        startActivity(challintent);
        overridePendingTransition(0, 0);


        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}

