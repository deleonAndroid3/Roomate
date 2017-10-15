package com.training.android.roomate.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.training.android.roomate.Model.userModel;
import com.training.android.roomate.R;

import java.util.ArrayList;
import java.util.List;

public class ViewTenantProfile extends AppCompatActivity {

    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    private TextView mtvTenantName, mtvTenantDesc, mtvTenantContact;
    private ListView mlvTenantPrefs;
    private List<String> Listprefs;
    private String prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tenant_profile);

        mtvTenantName = findViewById(R.id.tvTenantName);
        mtvTenantDesc = findViewById(R.id.TvTenantDesc);
        mtvTenantContact = findViewById(R.id.tvTenantContact);
        mlvTenantPrefs = findViewById(R.id.lvTenantPref);

        mFirebaseInstance = FirebaseDatabase.getInstance();

        Intent i = getIntent();


        getProfile(i.getStringExtra("ID"));
        getPrefs(i.getStringExtra("ID"));
    }

    public void getProfile(String ID){
        mFirebaseDatabase = mFirebaseInstance.getReference("users").child(ID);

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userModel um = dataSnapshot.getValue(userModel.class);
                mtvTenantName.setText(um.getFname()+""+ um.getLname());
                mtvTenantDesc.setText(um.getDesc());
                mtvTenantContact.setText(um.getContactnum());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getPrefs(String ID){
        Listprefs = new ArrayList<>();

        mFirebaseDatabase = mFirebaseInstance.getReference("users").child(ID).child("user_preferences");
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot prefsSnap : dataSnapshot.getChildren()) {
                    GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {};
                    prefs = prefsSnap.getValue(t);
                    Listprefs.add(prefs);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(ViewTenantProfile.this, android.R.layout.simple_list_item_1, Listprefs);
                mlvTenantPrefs.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
