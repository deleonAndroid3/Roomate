package com.training.android.roomate.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.training.android.roomate.Model.ApartmentModel;
import com.training.android.roomate.R;
import com.training.android.roomate.activities.ApartmentActivity;

import java.util.ArrayList;

/**
 * Created by Dyste on 10/10/2017.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchAdapterViewHolder> {

    private ApartmentModel currentam;
    private Context context;
    private ArrayList<ApartmentModel> amList;
    private ArrayList<String> IDLists;
    private SearchAdapterViewHolder holder;
    private int layout;

    public SearchAdapter(Context context, ArrayList<ApartmentModel> amList,ArrayList<String> IDLists, int layout) {
        this.context = context;
        this.amList = amList;
        this.IDLists = IDLists;
        this.layout = layout;
    }

    @Override
    public SearchAdapter.SearchAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(layout, parent, false);
        holder = new SearchAdapterViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final SearchAdapterViewHolder holder, final int position) {
        currentam = amList.get(position);

        holder.mtvAName.setText(currentam.getName());
        holder.mtvAAddress.setText(currentam.getAddress());
        holder.mtvACity.setText(currentam.getCity());
        holder.mtvRM.setText(currentam.getRMNeeded());
        holder.mtvARent.setText("Php " + currentam.getMonthlyRent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ApartmentActivity.class);
                i.putExtra("ID", IDLists.get(position));
                i.putExtra("Name", currentam.getName());
                i.putExtra("Address", currentam.getAddress());
                i.putExtra("City", currentam.getCity());
                i.putExtra("RM", currentam.getRMNeeded());
                i.putExtra("Rent", currentam.getMonthlyRent());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return amList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class SearchAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mtvAName, mtvAAddress, mtvACity, mtvRM, mtvARent;

        public SearchAdapterViewHolder(View itemView) {
            super(itemView);

            mtvAName = itemView.findViewById(R.id.tvAName);
            mtvAAddress = itemView.findViewById(R.id.tvAAddress);
            mtvACity = itemView.findViewById(R.id.tvACity);
            mtvRM = itemView.findViewById(R.id.tvRM);
            mtvARent = itemView.findViewById(R.id.tvRent);
        }
    }
}
