package com.training.android.roomate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.training.android.roomate.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ApartmentActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    private TextView mtvApName, mtvApAddress, mtvApCity, mtvApMrent, mtvRMNum, mtvViewTenant;
    private Button mbtApply;
    private ListView mlvFeats;
    private List<String> ListFeats;
    private String feats;
    private Intent i;

    private HashMap<String, String> UID = new HashMap<>();

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
        mbtApply = findViewById(R.id.btnApply);
        mtvViewTenant = findViewById(R.id.tvSeeTenant);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        UID.put(user.getUid(), user.getUid());

        i = getIntent();
        checkApplication(i.getStringExtra("ID"));
        mtvApName.setText(i.getStringExtra("Name"));
        mtvApAddress.setText(i.getStringExtra("Address"));
        mtvApCity.setText(i.getStringExtra("City"));
        mtvApMrent.setText(i.getStringExtra("Rent"));
        mtvRMNum.setText(i.getStringExtra("RM"));
        getFeats(i.getStringExtra("ID"));

        mbtApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApplytoApartment(i.getStringExtra("ID"));
                Toast.makeText(ApartmentActivity.this, "Application Sent", Toast.LENGTH_SHORT).show();
                mbtApply.setText("Applied");
                mbtApply.setEnabled(false);
            }
        });

        mtvViewTenant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewTenant(i.getStringExtra("ID"));
            }
        });
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

    public void ApplytoApartment(String ID) {
        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments").child(ID);

        mFirebaseDatabase.child("apartment_applicants").push().setValue(UID);
    }

    public void ViewTenant(String ID) {
        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments").child(ID).child("apartment_tenants");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Intent i = new Intent(ApartmentActivity.this, ViewTenantProfile.class);
                    i.putExtra("ID", child.getKey());
                    startActivity(i);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void checkApplication(String ApID) {
        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments").child(ApID).child("apartment_applicants");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot parent : dataSnapshot.getChildren()) {
                    for (DataSnapshot child : parent.getChildren()) {
                        if (user.getUid().equals(child.getValue())){
                            mbtApply.setText("Applied");
                            mbtApply.setEnabled(false);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


}
