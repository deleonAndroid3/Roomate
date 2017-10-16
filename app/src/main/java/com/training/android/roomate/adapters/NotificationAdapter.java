package com.training.android.roomate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.training.android.roomate.Model.userModel;
import com.training.android.roomate.R;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationAdapterViewHolder> {

    private userModel currentum;
    private Context context;
    private ArrayList<userModel> umList;
    private NotificationAdapterViewHolder holder;
    private int layout;

    public NotificationAdapter(Context context, ArrayList<userModel> umList, int layout) {
        this.context = context;
        this.umList = umList;
        this.layout = layout;
    }

    @Override
    public NotificationAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        final View view = LayoutInflater.from(context).inflate(layout, parent, false);
        holder = new NotificationAdapterViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(NotificationAdapterViewHolder holder, int position) {
        currentum = umList.get(position);

        holder.mtvNotifName.setText(currentum.getFname() + " " + currentum.getLname());
        holder.mtvNotifContacts.setText(currentum.getContactnum());
    }

    @Override
    public int getItemCount() {
        return umList.size();
    }

    class NotificationAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView mtvNotifName, mtvNotifContacts;

        public NotificationAdapterViewHolder(View itemView) {
            super(itemView);

            mtvNotifName = itemView.findViewById(R.id.tvNotifName);
            mtvNotifContacts = itemView.findViewById(R.id.tvNotifContact);
        }
    }
}
