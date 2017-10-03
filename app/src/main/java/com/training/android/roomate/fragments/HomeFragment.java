package com.training.android.roomate.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.training.android.roomate.R;
import com.training.android.roomate.adapters.HomeAdapter;
import com.training.android.roomate.models.Home;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View rootView;
    private RecyclerView matchesRecycler, preferenceRecycler;
    private LinearLayoutManager matchesManager, preferencesManager;
    private RecyclerView.Adapter matchesAdapter, preferencesAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home2, container, false);

        matchesRecycler = rootView.findViewById(R.id.recycler1);
        matchesManager = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        matchesRecycler.setLayoutManager(matchesManager);
        matchesAdapter = new HomeAdapter(rootView.getContext(),getHomes());
        matchesRecycler.setAdapter(matchesAdapter);

        preferenceRecycler = rootView.findViewById(R.id.recycler2);
        preferencesManager = new LinearLayoutManager(rootView.getContext(),LinearLayoutManager.HORIZONTAL,false);
        preferenceRecycler.setLayoutManager(preferencesManager);
        preferencesAdapter = new HomeAdapter(rootView.getContext(),getHomes());
        preferenceRecycler.setAdapter(preferencesAdapter);


        return rootView;
    }

    private ArrayList<Home> getHomes(){
        ArrayList<Home> homes = new ArrayList<>();

//        public Home(String homeId, String homeName, String homeRate, String homePic)
        homes.add(new Home("1","Regs Isabelo","100",String.valueOf(R.drawable.arrowmain)));
        homes.add(new Home("1","Hillary Briones","150",String.valueOf(R.drawable.image1)));
        homes.add(new Home("1","Adzhlee Formentera","90",String.valueOf(R.drawable.image2)));
        homes.add(new Home("1","Sept Lozada","140",String.valueOf(R.drawable.image3)));
        homes.add(new Home("1","Regs Isabelo","100",String.valueOf(R.drawable.arrowmain)));
        homes.add(new Home("1","Hillary Briones","150",String.valueOf(R.drawable.image1)));
        homes.add(new Home("1","Adzhlee Formentera","90",String.valueOf(R.drawable.image2)));
        homes.add(new Home("1","Sept Lozada","140",String.valueOf(R.drawable.image3)));
        homes.add(new Home("1","Regs Isabelo","100",String.valueOf(R.drawable.arrowmain)));
        homes.add(new Home("1","Hillary Briones","150",String.valueOf(R.drawable.image1)));
        homes.add(new Home("1","Adzhlee Formentera","90",String.valueOf(R.drawable.image2)));
        homes.add(new Home("1","Sept Lozada","140",String.valueOf(R.drawable.image3)));
        homes.add(new Home("1","Regs Isabelo","100",String.valueOf(R.drawable.arrowmain)));
        homes.add(new Home("1","Hillary Briones","150",String.valueOf(R.drawable.image1)));
        homes.add(new Home("1","Adzhlee Formentera","90",String.valueOf(R.drawable.image2)));
        homes.add(new Home("1","Sept Lozada","140",String.valueOf(R.drawable.image3)));

        return homes;
    }

}