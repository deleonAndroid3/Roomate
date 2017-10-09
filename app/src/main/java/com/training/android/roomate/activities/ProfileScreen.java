package com.training.android.roomate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.training.android.roomate.R;

public class ProfileScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageView mivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        mivBack = findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();
        getProfile();

        mivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(ProfileScreen.this, "Signed Out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ProfileScreen.this, AuthenticationActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void getProfile() {
        FirebaseUser user = mAuth.getCurrentUser();


    }
}
