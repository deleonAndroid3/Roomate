package com.training.android.roomate.adapters;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.training.android.roomate.R;
import com.training.android.roomate.Model.Home;

import java.util.ArrayList;

/**
 * Created by Acer on 02/10/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Home> homes;

    public HomeAdapter(Context context, ArrayList<Home> homes) {
        this.context = context;
        this.homes = homes;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_card_layout,parent,false);
        return new HomeAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HomeAdapterViewHolder) {
            HomeAdapterViewHolder viewHolder = (HomeAdapterViewHolder) holder;
            //TODO: e change lang nya ni ang glide ayaw nani e parse if naa namoy data
            Glide.with(context).load(Integer.parseInt(homes.get(position).getHomePic())).into(viewHolder.homeImg);
            viewHolder.homeNameTxt.setText(homes.get(position).getHomeName());
            viewHolder.homeRateTxt.setText("Approx. Budget\nPHP " + homes.get(position).getHomeRate() + " per day");
        }
    }

    @Override
    public int getItemCount() {
        return homes.size();
    }

    class HomeAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView homeImg;
        TextView homeNameTxt, homeRateTxt, connectBtn, shareBtn;
        public HomeAdapterViewHolder(View itemView) {
            super(itemView);

            homeImg = itemView.findViewById(R.id.homeImg);
            homeNameTxt = itemView.findViewById(R.id.homeNameTxt);
            homeRateTxt = itemView.findViewById(R.id.homeRateTxt);
            connectBtn = itemView.findViewById(R.id.connectBtn);
            shareBtn = itemView.findViewById(R.id.shareBtn);

            connectBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            shareBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}