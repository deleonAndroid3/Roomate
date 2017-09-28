package com.training.android.roomate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectPreferences extends AppCompatActivity {

    String[] userPrefList = {"Student", "Non-Smoker", "Non-Drinker"};
    ArrayList<String> checkedPref;
    String selected = "";
    SparseBooleanArray checked;
    private ListView mlvUserPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_preferences);

        mlvUserPrefs = (ListView) findViewById(R.id.lvUserPref);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, userPrefList);
        mlvUserPrefs.setAdapter(adapter);
        mlvUserPrefs.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        checked = mlvUserPrefs.getCheckedItemPositions();
        for (int i = 0; i < mlvUserPrefs.getCount(); i++) {
            if (checked.get(i)) {
                selected = checked.toString();
                Toast.makeText(this, selected, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
