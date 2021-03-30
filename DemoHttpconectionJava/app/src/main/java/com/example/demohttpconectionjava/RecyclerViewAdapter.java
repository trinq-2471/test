package com.example.demohttpconectionjava;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolderWarDetails>  {

    // declaring variables
    private List<ModelWarDetails> listWarDetails;

    public void setData(List<ModelWarDetails> listWarDetails) {
        this.listWarDetails = listWarDetails;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolderWarDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_war,parent,false);
        ViewHolderWarDetails viewHolderWarDetails = new ViewHolderWarDetails(view);

        return viewHolderWarDetails;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderWarDetails holder, int position) {
        //setting data on each row of list according to position.

        holder.txtName.setText(listWarDetails.get(position).getName());
        holder.txtLocation.setText(listWarDetails.get(position).getLocation());
        holder.txtAttackerKing.setText(listWarDetails.get(position).getAttacker_king());
        holder.txtDefenderKing.setText(listWarDetails.get(position).getDefender_king());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return listWarDetails.size();
    }

    class ViewHolderWarDetails extends RecyclerView.ViewHolder{

        // declaring variables
        TextView txtName, txtLocation, txtAttackerKing,txtDefenderKing;
        CardView layoutCard;

        ViewHolderWarDetails(View v) {
            super(v);

            txtName = (TextView) v.findViewById(R.id.txtName);
            txtLocation = (TextView) v.findViewById(R.id.txtLocation);
            txtAttackerKing = (TextView) v.findViewById(R.id.txtAttackerKing);
            txtDefenderKing = (TextView) v.findViewById(R.id.txtDefenderKing);
            layoutCard = (CardView) v.findViewById(R.id.layoutCard);
        }
    }
}
