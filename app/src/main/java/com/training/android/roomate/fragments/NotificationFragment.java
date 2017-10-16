package com.training.android.roomate.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.training.android.roomate.Model.userModel;
import com.training.android.roomate.R;
import com.training.android.roomate.adapters.NotificationAdapter;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    boolean flag = false;
    //FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference ref;
    private FirebaseUser user;
    //FRAGMENT
    private ArrayList<userModel> umLists;
    private NotificationAdapter mAdapter;
    private RecyclerView mrvNotifs;
    private View view;

    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notification, container, false);

        mrvNotifs = view.findViewById(R.id.rvNotif);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        getNotifications();

        return view;
    }

    public void getNotifications() {

        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments");

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (final DataSnapshot parent : dataSnapshot.getChildren()) {
                    ref = mFirebaseDatabase.child(parent.getKey()).child("apartment_tenants");

                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.child(user.getUid()).exists()) {
                                getNotiflist(parent.getKey());
                                flag = true;
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

    public void getNotiflist(String ApID) {

        mFirebaseDatabase = mFirebaseInstance.getReference("Apartments").child(ApID).child("apartment_applicants");
        umLists = new ArrayList<>();

        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    for (DataSnapshot value : child.getChildren()) {

                        ref = mFirebaseInstance.getReference("users").child(value.getKey());

                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                userModel um = dataSnapshot.getValue(userModel.class);
                                umLists.add(um);
                                Toast.makeText(getContext(), um.getContactnum() , Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }

                mAdapter = new NotificationAdapter(getContext(), umLists, R.layout.card_notification);
                mrvNotifs.setAdapter(mAdapter);
                mrvNotifs.setLayoutManager(new LinearLayoutManager(getContext()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
