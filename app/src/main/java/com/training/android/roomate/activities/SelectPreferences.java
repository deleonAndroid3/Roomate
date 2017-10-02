package com.training.android.roomate.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.training.android.roomate.R;

public class SelectPreferences extends AppCompatActivity {
    Button mBtnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_preferences);

        mBtnConfirm = findViewById(R.id.btnConfirm);

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SelectPreferences.this, ApartmentPreferences.class);
                startActivity(i);
            }
        });
    }
}
