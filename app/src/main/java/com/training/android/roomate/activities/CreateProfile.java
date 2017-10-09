package com.training.android.roomate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.training.android.roomate.Model.userModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.training.android.roomate.R;

public class CreateProfile extends AppCompatActivity {

    Intent getUId;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private Button mbtnCreate;
    private EditText metFname;
    private EditText metLname;
    private EditText metAge;
    private EditText metContactnum;
    private RadioGroup mrgGender;
    private RadioGroup mrgType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        metFname = findViewById(R.id.etFname);
        metLname = findViewById(R.id.etLname);
        metAge = findViewById(R.id.etAge);
        metContactnum = findViewById(R.id.etContactNum);
        mrgGender = findViewById(R.id.rgGender);
        mrgType = findViewById(R.id.rgType);
        mbtnCreate = findViewById(R.id.btnCreateProfile);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        getUId = getIntent();

        mbtnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUser(getUId.getStringExtra("UID"));
                Intent settings = new Intent(CreateProfile.this, MainScreen.class);
                startActivity(settings);
            }
        });

    }

    public void addUser(String UID) {

        mFirebaseDatabase = mFirebaseInstance
                .getReference("users");

        String Fname = metFname.getText().toString();
        String Lname = metLname.getText().toString();
        String contactnum = metContactnum.getText().toString();
        String age = metAge.getText().toString();
        String gender = ((RadioButton) findViewById(mrgGender.getCheckedRadioButtonId())).getText().toString();
        String desc = ((RadioButton) findViewById(mrgType.getCheckedRadioButtonId())).getText().toString();

        userModel um = new userModel(Fname, Lname, contactnum, gender, age, desc);
        mFirebaseDatabase.child(UID).setValue(um);
    }

    public boolean checkET() {

        if (metFname.getText().toString().isEmpty()) {
            metFname.setError("Input First Name");
            return false;
        }
        if (metLname.getText().toString().isEmpty()) {
            metLname.setError("Input Last Name");
            return false;
        }
        if (metAge.getText().toString().isEmpty()) {
            metAge.setError("Input Age");
            return false;
        }
        if (metContactnum.getText().toString().isEmpty()) {
            metContactnum.setError("Input Contact Number");
            return false;
        }

        return true;
    }
}

