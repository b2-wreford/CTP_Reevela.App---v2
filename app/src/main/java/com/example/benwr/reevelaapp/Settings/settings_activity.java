package com.example.benwr.reevelaapp.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.Utils.BottomNavigationViewHelper;

import org.florescu.android.rangeseekbar.RangeSeekBar;

public class settings_activity extends AppCompatActivity {


    private Button account_Btn;

    private Context mContext = settings_activity.this;
    private static final int ACTIVITY_REF = 3;
    private TextView radiusUPDATE, min_field, max_field;
    private SeekBar radiusSB, minage_seekbar, maxage_seekbar;
    RangeSeekBar ageSeekBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_layout_main);


        account_Btn = (Button) findViewById(R.id.logout_btn);
        account_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presentAccountSettings(v);
            }
        });

        setupBottomNavigationView();


        //Seek Bar For Radius

        radiusUPDATE = (TextView) findViewById(R.id.mile_field);
        radiusSB = (SeekBar) findViewById(R.id.radius_seekbar);

        radiusSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                radiusUPDATE.setText("" + progress + " miles");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //AGE MIN SEEK BAR

        min_field = (TextView) findViewById(R.id.min_field);
        minage_seekbar = (SeekBar) findViewById(R.id.minage_seekbar);

        minage_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                min_field.setText("" + progress + " min");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //AGE MIN SEEK BAR

        max_field = (TextView) findViewById(R.id.max_field);
        maxage_seekbar = (SeekBar) findViewById(R.id.maxage_seekbar);

        maxage_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                max_field.setText("" + progress + " max");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }


    // Launch YouTube App after clicking the button
    public void launchInstagram(View view) {
        Intent launchInstagram = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
        startActivity(launchInstagram);
    }

    public void presentAccountSettings(View v) {
        Intent openaccountset = new Intent(this, settings_activity_accountsettings.class);
        startActivity(openaccountset);
        overridePendingTransition(0, 0);
    }

    //_______________________________
    //BottomNavigationViewSetUp
    //_______________________________

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView);
        BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        overridePendingTransition(0, 0);
    }
}
