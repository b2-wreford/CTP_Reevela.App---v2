package com.example.benwr.reevelaapp.Settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.benwr.reevelaapp.Profile.EditProfileFragment;
import com.example.benwr.reevelaapp.Profile.SignOutFragment;
import com.example.benwr.reevelaapp.R;
import com.example.benwr.reevelaapp.Utils.SectionsStatePagerAdapter;

import java.util.ArrayList;

public class settings_activity_accountsettings extends AppCompatActivity {


    private Button bck_btn;
    private Context mContext;

    private SectionsStatePagerAdapter pagerAdapter;
    private ViewPager mViewPager;
    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_settings_layout);
        mContext = settings_activity_accountsettings.this;

        mViewPager = (ViewPager) findViewById(R.id.container);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.account_layout);

        //CODE FOR BACK BUTTON ON FRAGMENTS
        ImageView backArrow = (ImageView) findViewById(R.id.backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setupSettingsList();

        setupFragments();
    }

    private void setupFragments() {
        pagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile_fragment));//fragment 0
        pagerAdapter.addFragment(new SignOutFragment(), getString(R.string.sign_out_fragment)); //fragment 1
    }

    private void setViewPager(int fragmentNumber) {
        mRelativeLayout.setVisibility(View.GONE);
//        Log.d(TAG, "setViewPager: navigation to fragment number#: " +fragmentNumber);
        mViewPager.setAdapter(pagerAdapter);
        mViewPager.setCurrentItem(fragmentNumber);
    }

    private void setupSettingsList() {
        ListView listView = (ListView) findViewById(R.id.account_listview);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile_fragment)); //Fragment 0
        options.add(getString(R.string.sign_out_fragment)); //Fragment 1

        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setViewPager(position);
            }

        });
    }


    public void openMainSettings() {
        Intent ncgameintent = new Intent(this, settings_activity.class);
        startActivity(ncgameintent);
        overridePendingTransition(0, 0);
    }
}
