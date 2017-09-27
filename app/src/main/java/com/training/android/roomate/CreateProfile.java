package com.training.android.roomate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.training.android.roomate.Model.TenantModel;

public class CreateProfile extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    private EditText metFname;
    private EditText metLname;
    private EditText metAge;
    private EditText metContactnum;
    private RadioGroup mrgGender;
    private RadioGroup mrgType;
    private Button mbtnCreate;

    private String gender = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        metFname = findViewById(R.id.etFName);
        metLname = findViewById(R.id.etLName);
        metAge = findViewById(R.id.etAge);
        metContactnum = findViewById(R.id.etContactNum);
        mrgGender = findViewById(R.id.rgGender);
        mrgType = findViewById(R.id.rgUserType);
        mbtnCreate = findViewById(R.id.btnCreate);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();

        final Intent getUId = getIntent();


        mrgType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.rbTenant:
                        mbtnCreate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                addTenant(getUId.getStringExtra("UID"));
                            }
                        });
                        break;
                    case R.id.rbSeeker:
                        break;

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mAuth.signOut();
        Toast.makeText(this, "Signed Out", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CreateProfile.this, AuthenticationActivity.class));
    }

    public void addTenant(String UID) {

        mFirebaseDatabase = mFirebaseInstance
                .getReference("users")
                .child("user_types")
                .child("Tenants");

        String Fname = metFname.getText().toString();
        String Lname = metLname.getText().toString();
        String contactnum = metContactnum.getText().toString();
        String age = metAge.getText().toString();

        mrgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radioButton3:
                        gender = "Female";
                        break;
                    case R.id.radioButton4:
                        gender = "Male";
                        break;
                }
            }
        });

        TenantModel tenantModel = new TenantModel(Fname, Lname, contactnum, gender, age);
        mFirebaseDatabase.child(UID).setValue(tenantModel);
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
