package com.training.android.roomate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.training.android.roomate.R;

public class CreateProfile extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEtFname;
    private EditText mEtLname;
    private EditText mEtContactNum;
    private EditText mEtAge;
    private Button mButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference mRef = database.getReference("users").child("user_types").child("Seekers");
    private RadioGroup mRgGender, mRgType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        mAuth = FirebaseAuth.getInstance();
        mRgGender = findViewById(R.id.rgGender);
        mRgType = findViewById(R.id.rgType);
        mButton = findViewById(R.id.btnCreateProfile);
        mEtFname = findViewById(R.id.etFname);
        mEtLname = findViewById(R.id.etLname);
        mEtContactNum = findViewById(R.id.etContactNum);
        mEtAge = findViewById(R.id.etAge);
        int radioButtonID = mRgGender.getCheckedRadioButtonId();

//       initToolbar();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UID = getIntent();
                DatabaseReference newRef = mRef.child(UID.getStringExtra("UID"));
                newRef.child("Fname").setValue(mEtFname.getText());
                newRef.child("Lname").setValue(mEtLname.getText());
                newRef.child("ContactNum").setValue(mEtContactNum.getText());
                newRef.child("Age").setValue(mEtAge);
                Intent i = new Intent(CreateProfile.this, SelectPreferences.class);
                startActivity(i);
            }
        });
        Intent getUId = getIntent();
        Toast.makeText(this, "Gwapo: " + getUId.getStringExtra("UID"), Toast.LENGTH_SHORT).show();


    }

/*
    private void initToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_launcher);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mAuth.signOut();
        Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CreateProfile.this, AuthenticationActivity.class));
    }

 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settings = new Intent(CreateProfile.this, SelectPreferences.class);
                startActivity(settings);
                break;
        }


        return super.onOptionsItemSelected(item);
    }*/
}
