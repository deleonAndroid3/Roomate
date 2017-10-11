package com.training.android.roomate.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.training.android.roomate.Model.ApartmentModel;
import com.training.android.roomate.R;
import com.training.android.roomate.adapters.SearchAdapter;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private EditText metSearch;
    private RecyclerView mrvSearchlist;
    private ArrayList<ApartmentModel> amLists;
    private ArrayList<String> IDLists;
    private SearchAdapter mAdapter;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        metSearch = findViewById(R.id.etSearch);
        mrvSearchlist = findViewById(R.id.rvSearch);
        mFirebaseInstance = FirebaseDatabase.getInstance();
        amLists = new ArrayList<>();
        IDLists = new ArrayList<>();


        metSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    SearchApart(metSearch.getText().toString());
                    return true;
                }
                return false;
            }
        });

        metSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                metSearch.setText("");

                if (metSearch.getText().toString().equals("")) {
                    amLists.clear();
                    mAdapter = new SearchAdapter(SearchActivity.this, amLists,IDLists, R.layout.card_search);
                    mrvSearchlist.setAdapter(mAdapter);
                    mrvSearchlist.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                }
            }
        });


    }

    public void SearchApart(String search) {

        mFirebaseDatabaseReference = mFirebaseInstance.getReference("Apartments");
        Query query = mFirebaseDatabaseReference.orderByChild("city")
                .startAt(search).endAt(search + "\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    ApartmentModel am = data.getValue(ApartmentModel.class);
                    amLists.add(am);
                    IDLists.add(data.getKey());
                }

                if (!amLists.isEmpty()) {
                    mAdapter = new SearchAdapter(SearchActivity.this, amLists,IDLists, R.layout.card_search);
                    mrvSearchlist.setAdapter(mAdapter);
                    mrvSearchlist.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", databaseError.getMessage());
            }
        });


    }
}
