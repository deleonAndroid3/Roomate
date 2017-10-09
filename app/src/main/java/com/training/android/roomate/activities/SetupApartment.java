package com.training.android.roomate.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.training.android.roomate.Model.ApartmentModel;
import com.training.android.roomate.R;

public class SetupApartment extends AppCompatActivity {

    private EditText metName, metAddress, metCity, metNum;
    private Button mbtnSubmit;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    private String UID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_apartment);

        metName = findViewById(R.id.etName);
        metAddress = findViewById(R.id.etAddress);
        metCity = findViewById(R.id.etCity);
        metNum = findViewById(R.id.etRmNeeded);
        mbtnSubmit = findViewById(R.id.btnSubmit);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        Intent i = getIntent();
        UID = i.getStringExtra("UID");

        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addApartment(UID);
                Toast.makeText(SetupApartment.this, "Apartment Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addApartment(String UID){

        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments");

        String Name = metName.getText().toString();
        String Address = metAddress.getText().toString();
        String City = metCity.getText().toString();
        String num = metNum.getText().toString();

        ApartmentModel am = new ApartmentModel(Name, Address, City, num);
        mFirebaseDatabase.push().setValue(am);


    }


}
