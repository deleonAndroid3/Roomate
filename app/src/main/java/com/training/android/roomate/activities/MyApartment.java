package com.training.android.roomate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.training.android.roomate.Model.ApartmentModel;
import com.training.android.roomate.R;

import java.util.ArrayList;
import java.util.List;

public class MyApartment extends AppCompatActivity {

    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    private TextView mtvName, mtvAddress, mtvCity, mtvAddFeats;
    private String ApID;
    private ListView mlvFeats;
    private List<String> ListFeats;
    private String feats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apartment);

        mtvName = findViewById(R.id.tvApName);
        mtvAddress = findViewById(R.id.tvAddress);
        mtvCity = findViewById(R.id.tvACity);
        mtvAddFeats = findViewById(R.id.tvAddFeats);
        mlvFeats = findViewById(R.id.lvFeats);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        Intent i = getIntent();
        getApart(i.getStringExtra("ApartmentID"));


        mtvAddFeats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyApartment.this, ApartmentPreferences.class);
                i.putExtra("ApID", ApID);
                startActivity(i);
            }
        });
    }

    public void getApart(String ID) {

        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments").child(ID);

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ApID = dataSnapshot.getKey();

                ApartmentModel am = dataSnapshot.getValue(ApartmentModel.class);
                mtvName.setText(am.getName());
                mtvAddress.setText(am.getAddress());
                mtvCity.setText(am.getCity());
                getPrefs();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", databaseError.getMessage());
            }
        });

    }

    public void getPrefs() {
        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments").child(ApID)
                .child("apartment_features");

        ListFeats = new ArrayList<String>();

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot prefsSnap : dataSnapshot.getChildren()) {
                    GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {
                    };
                    feats = prefsSnap.getValue(t);
                    ListFeats.add(feats);

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(MyApartment.this, android.R.layout.simple_list_item_1, ListFeats);
                mlvFeats.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
