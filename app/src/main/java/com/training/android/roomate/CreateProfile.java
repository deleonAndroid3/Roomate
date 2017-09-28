package com.training.android.roomate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.training.android.roomate.Model.TenantModel;


public class CreateProfile extends AppCompatActivity {

    Intent getUId;

    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
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
        mrgType = findViewById(R.id.rgClassification);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        getUId = getIntent();


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
        String gender = ((RadioButton) findViewById(mrgGender.getCheckedRadioButtonId())).getText().toString();


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.forward:
                if (mrgType.getCheckedRadioButtonId() == R.id.rbTenant) {
                    addTenant(getUId.getStringExtra("UID"));
                    Intent settings = new Intent(CreateProfile.this, SelectPreferences.class);
                    settings.putExtra("UID", getUId.getStringExtra("UID"));
                    startActivity(settings);
                    break;
                } else {
                    Toast.makeText(this, "Seeker", Toast.LENGTH_SHORT).show();
                }

        }


        return super.onOptionsItemSelected(item);
    }
}
