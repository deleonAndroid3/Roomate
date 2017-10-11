package com.training.android.roomate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
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
import com.training.android.roomate.Model.userModel;
import com.training.android.roomate.R;
import com.training.android.roomate.activities.MyApartment;
import com.training.android.roomate.activities.SelectPreferences;
import com.training.android.roomate.activities.SetupApartment;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {


    int count = 0;
    boolean flag = false;
    //FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference ref;
    private FirebaseUser user;
    //FRAGMENT
    private View view;
    private ListView mlvprefs;
    private TextView mtxtName, mtxtDesc, mtxtContactNum, mtxtAddPrefs;
    private ImageButton mIbtnApart;
    private List<String> Listprefs;
    private String prefs;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        mtxtName = view.findViewById(R.id.txtPersonName);
        mtxtDesc = view.findViewById(R.id.txtDescription);
        mtxtContactNum = view.findViewById(R.id.txtContactNum);
        mtxtAddPrefs = view.findViewById(R.id.txtaddPrefs);
        mlvprefs = view.findViewById(R.id.lvpref);
        mIbtnApart = view.findViewById(R.id.iBtnApartment);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        getProfile();
        getPrefs();

        mtxtAddPrefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SelectPreferences.class);
                intent.putExtra("UID", user.getUid());
                startActivity(intent);
            }
        });

        mtxtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
            }
        });

        mIbtnApart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkApart();
            }
        });

        return view;
    }

    public void getProfile() {
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        if (user != null) {
            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        if (user.getUid().equals(child.getKey())) {
                            userModel um = child.getValue(userModel.class);
                            mtxtName.setText(um.getFname() + " " + um.getLname());
                            mtxtDesc.setText(um.getDesc());
                            mtxtContactNum.setText(um.getContactnum());

                            if (um.getDesc().equals("Seeker")) {
                                mIbtnApart.setVisibility(View.GONE);
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

    public void getPrefs() {
        mFirebaseDatabase = mFirebaseInstance.getReference("users").child(user.getUid())
                .child("user_preferences");

        Listprefs = new ArrayList<String>();

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot prefsSnap : dataSnapshot.getChildren()) {
                    GenericTypeIndicator<String> t = new GenericTypeIndicator<String>() {};
                    prefs = prefsSnap.getValue(t);
                    Listprefs.add(prefs);
                    Toast.makeText(getContext(), prefs, Toast.LENGTH_SHORT).show();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, Listprefs);
                mlvprefs.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void checkApart() {
        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot data : dataSnapshot.getChildren()) {
                    ref = mFirebaseDatabase.child(data.getKey()).child("apartment_tenants");

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            if (dataSnapshot.child(user.getUid()).exists()) {
                                flag = true;
                                Intent i = new Intent(view.getContext(), MyApartment.class);
                                i.putExtra("ApartmentID", data.getKey());
                                startActivity(i);
                            } else if (++count == data.getChildrenCount() && !flag){
                                Intent newApart = new Intent(view.getContext(), SetupApartment.class);
                                newApart.putExtra("UID", user.getUid());
                                startActivity(newApart);
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.e("TAG", databaseError.getMessage());
                        }
                    });

                    if (flag)
                        break;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", databaseError.getMessage());
            }
        });
    }

}
