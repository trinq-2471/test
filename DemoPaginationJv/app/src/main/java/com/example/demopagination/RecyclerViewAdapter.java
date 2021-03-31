package com.example.demopagination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // declaring variables
    private List<ModelWarDetails> listWarDetails;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    //constructor
    RecyclerViewAdapter( List<ModelWarDetails> listWarDetails) {
        this.listWarDetails = listWarDetails;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_war_details, parent, false);
            return new ViewHolderWarDetails(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        //setting data on each row of list according to position.
        if (holder instanceof ViewHolderWarDetails) {
            populateItemRows((ViewHolderWarDetails) holder, position);
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return listWarDetails.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    //returns list size

    @Override
    public int getItemCount() {
        return listWarDetails.size();
    }

    private class ViewHolderWarDetails extends RecyclerView.ViewHolder{

        // declaring variables
        TextView txtName, txtLocation, txtAttackerKing,txtDefenderKing,tvCountWar;
        CardView layoutCard;

        ViewHolderWarDetails(View v) {
            super(v);
            tvCountWar = (TextView) v.findViewById(R.id.tvCountWar);
            txtName = (TextView) v.findViewById(R.id.txtName);
            txtLocation = (TextView) v.findViewById(R.id.txtLocation);
            txtAttackerKing = (TextView) v.findViewById(R.id.txtAttackerKing);
            txtDefenderKing = (TextView) v.findViewById(R.id.txtDefenderKing);
            layoutCard = (CardView) v.findViewById(R.id.layoutCard);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed

    }

    private void populateItemRows(ViewHolderWarDetails viewHolder, int position) {
        viewHolder.tvCountWar.setText("War " + (position + 1));
        viewHolder.txtName.setText(listWarDetails.get(position).getName());
        viewHolder.txtLocation.setText(listWarDetails.get(position).getLocation());
        viewHolder.txtAttackerKing.setText(listWarDetails.get(position).getAttacker_king());
        viewHolder.txtDefenderKing.setText(listWarDetails.get(position).getDefender_king());
    }
}
