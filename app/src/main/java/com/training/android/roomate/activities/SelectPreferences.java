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

public class SelectPreferences extends AppCompatActivity {

    private String[] cbPrefs = {"Non-Smoker", "Non-Alcoholic Drinker", "Student", "Plays Loud Music"
            , "Cook", "Book Lover"};

    private Button mbtnConfirm;
    private ListView mlvPrefs;
    private SparseBooleanArray checked;
    private String UID;
    private ArrayList<String> prefsList;

    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_preferences);

        mlvPrefs = findViewById(R.id.lvPrefs);
        mbtnConfirm = findViewById(R.id.btnConfirm);
        prefsList = new ArrayList<>(Arrays.asList(cbPrefs));

        Intent i = getIntent();
        UID = i.getStringExtra("UID");

        mFirebaseInstance = FirebaseDatabase.getInstance();


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, cbPrefs);
        mlvPrefs.setAdapter(adapter);
        mlvPrefs.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        mbtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addPrefs();
            }
        });
    }

    public void addPrefs() {

        mFirebaseDatabase = mFirebaseInstance.getReference("users").child(UID);
        checked = mlvPrefs.getCheckedItemPositions();

        for (int i = 0; i < mlvPrefs.getCount(); i++) {
            if (checked.get(i)) {
                mFirebaseDatabase.child("user_preferences").push().setValue(prefsList.get(i));
            }
        }
    }
}
