package com.training.android.roomate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private ImageView mivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_screen);

        mivBack = findViewById(R.id.back);

        mAuth = FirebaseAuth.getInstance();

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
}
