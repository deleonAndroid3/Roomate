package com.training.android.roomate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.training.android.roomate.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ApartmentPreferences extends AppCompatActivity {

    private String[] cbPrefs = {"Shower", "Kitchen", "Double Deck", "Laundry"
            , "Air-Conditioned Room", "Separate Bedroom"};
    private String ApID;

    private ListView mlvApartPrefs;
    private SparseBooleanArray checked;
    private ArrayList<String> AprefsList;
    private Button mbtnAConfirm;

    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apartment_preferences);

        mlvApartPrefs = findViewById(R.id.lvApartPrefs);
        AprefsList = new ArrayList<>(Arrays.asList(cbPrefs));
        mbtnAConfirm = findViewById(R.id.btnAConfirm);

        Intent i = getIntent();
        ApID = i.getStringExtra("ApID");

        mFirebaseInstance = FirebaseDatabase.getInstance();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, cbPrefs);
        mlvApartPrefs.setAdapter(adapter);
        mlvApartPrefs.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        mbtnAConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFeats();
            }
        });

    }

    public void addFeats() {

        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments").child(ApID);
        checked = mlvApartPrefs.getCheckedItemPositions();

        for (int i = 0; i < mlvApartPrefs.getCount(); i++) {
            if (checked.get(i)) {
                mFirebaseDatabase.child("apartment_features").push().setValue(AprefsList.get(i));
            }
        }
    }
}
