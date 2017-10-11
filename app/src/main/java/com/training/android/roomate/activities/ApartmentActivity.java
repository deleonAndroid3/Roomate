package com.training.android.roomate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.training.android.roomate.R;

import java.util.ArrayList;
import java.util.List;

public class ApartmentActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    private TextView mtvApName, mtvApAddress, mtvApCity, mtvApMrent, mtvRMNum;
    private ListView mlvFeats;
    private List<String> ListFeats;
    private String feats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment);

        mtvApName = findViewById(R.id.tvApName);
        mtvApAddress = findViewById(R.id.tvApAddress);
        mtvApCity = findViewById(R.id.tvApCity);
        mtvApMrent = findViewById(R.id.tvApMrent);
        mtvRMNum = findViewById(R.id.tvRMNum);
        mlvFeats = findViewById(R.id.lvAFeats);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        Intent i = getIntent();
        mtvApName.setText(i.getStringExtra("Name"));
        mtvApAddress.setText(i.getStringExtra("Address"));
        mtvApCity.setText(i.getStringExtra("City"));
        mtvApMrent.setText(i.getStringExtra("Rent"));
        mtvRMNum.setText(i.getStringExtra("RM"));
        getFeats(i.getStringExtra("ID"));
    }

    public void getFeats(String ApID) {

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

                ArrayAdapter<String> adapter = new ArrayAdapter<>(ApartmentActivity.this, android.R.layout.simple_list_item_1, ListFeats);
                mlvFeats.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
