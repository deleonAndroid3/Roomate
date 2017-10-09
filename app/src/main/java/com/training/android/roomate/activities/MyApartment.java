package com.training.android.roomate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.training.android.roomate.Model.ApartmentModel;
import com.training.android.roomate.R;

public class MyApartment extends AppCompatActivity {

    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    private TextView mtvName, mtvAddress, mtvCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_apartment);

        mtvName = findViewById(R.id.tvName);
        mtvAddress = findViewById(R.id.tvAddress);
        mtvCity = findViewById(R.id.tvCity);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        Intent i = getIntent();
        getApart(i.getStringExtra("ApartmentID"));
    }

    public void getApart(String ID) {

        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments").child(ID);

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(MyApartment.this, dataSnapshot.getChildrenCount() + "", Toast.LENGTH_SHORT).show();
                ApartmentModel am = dataSnapshot.getValue(ApartmentModel.class);
                mtvName.setText(am.getName());
                mtvAddress.setText(am.getAddress());
                mtvCity.setText(am.getCity());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", databaseError.getMessage());
            }
        });

    }
}
