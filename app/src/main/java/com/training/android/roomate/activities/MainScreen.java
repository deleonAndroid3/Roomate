package com.training.android.roomate.activities;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.training.android.roomate.R;
import com.training.android.roomate.fragments.HomeFragment;

public class MainScreen extends AppCompatActivity {

    private FrameLayout mFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        mFragmentContainer = findViewById(R.id.contentContainer);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentContainer,new HomeFragment())
                .commit();

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch(tabId){
                    case R.id.tab_home:
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.contentContainer,new HomeFragment())
                                .commit();
//                        HomeFragment home = new HomeFragment();
//                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                        ft.replace(R.id.contentContainer,home);
                        break;
                    case R.id.tab_message:
                        break;
                    case R.id.tab_notification:
                        break;
                    case R.id.tab_profile:
                }
            }
        });
    }
}