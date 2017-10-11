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

import java.util.HashMap;

public class SetupApartment extends AppCompatActivity {

    private EditText metName, metAddress, metCity, metNum, metRent;
    private Button mbtnSubmit;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    private HashMap<String,String> UID = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_apartment);

        metName = findViewById(R.id.tvApName);
        metAddress = findViewById(R.id.tvAddress);
        metCity = findViewById(R.id.tvACity);
        metNum = findViewById(R.id.etRmNeeded);
        metRent = findViewById(R.id.etRent);
        mbtnSubmit = findViewById(R.id.btnSubmit);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        Intent i = getIntent();
        UID.put(i.getStringExtra("UID"), i.getStringExtra("UID"));

        mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addApartment(UID);
                Toast.makeText(SetupApartment.this, "Apartment Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addApartment(HashMap<String,String> UID){

        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments");

        String Name = metName.getText().toString();
        String Address = metAddress.getText().toString();
        String City = metCity.getText().toString();
        String num = metNum.getText().toString();
        String rent = metRent.getText().toString();

        ApartmentModel am = new ApartmentModel(Name, Address, City, num,rent, UID);
        mFirebaseDatabase.push().setValue(am);
    }


}
