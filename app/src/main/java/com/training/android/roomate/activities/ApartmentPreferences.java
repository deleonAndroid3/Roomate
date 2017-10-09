package com.training.android.roomate.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
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

        mFirebaseInstance = FirebaseDatabase.getInstance();
    }
}
