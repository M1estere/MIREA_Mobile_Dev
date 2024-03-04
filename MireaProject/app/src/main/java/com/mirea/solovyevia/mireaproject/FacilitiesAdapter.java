package com.mirea.solovyevia.mireaproject;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FacilitiesAdapter extends RecyclerView.Adapter<FacilitiesAdapter.ViewHolder> {

    private ArrayList<Facility> facilities = new ArrayList<>();

    private Context context;

    public FacilitiesAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FacilitiesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.facility_list_item, parent, false);

        return new FacilitiesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();

        Log.d("PROG", "Title: " + facilities.get(pos).title);
        holder.topText.setText(facilities.get(pos).title);
        holder.bottomText.setText(facilities.get(pos).address);

        holder.bg.setOnClickListener(view -> {
            Intent intent = new Intent(context, MapActivity.class);
            intent.putExtra("latitude", facilities.get(pos).point.getLatitude());
            intent.putExtra("longitude", facilities.get(pos).point.getLongitude());

            intent.putExtra("placeName", facilities.get(pos).title);
            intent.putExtra("placeAddress", facilities.get(pos).address);
            intent.putExtra("placeDesc", facilities.get(pos).description);

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return facilities.size();
    }

    public void setFacilities(ArrayList<Facility> facilities) {
        this.facilities = facilities;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView bg;
        private final TextView topText;
        private final TextView bottomText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bg = itemView.findViewById(R.id.parent);
            topText = itemView.findViewById(R.id.topText);
            bottomText = itemView.findViewById(R.id.bottomText);
        }
    }
}
