package com.fmway.userOperations;

import android.app.Activity;
import android.widget.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fmway.R;
import com.fmway.models.trip.Trip;

public class PostActivity extends ArrayAdapter<Trip> {
    private final ArrayList<Trip> trip;

    private final Activity context;

    public PostActivity(ArrayList<Trip> trip, Activity context){
        super(context,R.layout.customview_listtrips,trip);
        this.trip=trip;
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.customview_listtrips,null,true);
        TextView objectIdText=customView.findViewById(R.id.customView_objectId);
        TextView dateText=customView.findViewById(R.id.customView_date);
        TextView timeText=customView.findViewById(R.id.customView_Time);
        TextView fromText=customView.findViewById(R.id.customView_From);
        TextView destinationText=customView.findViewById(R.id.customView_Dest);
        TextView capacityText=customView.findViewById(R.id.customView_Capacity);
        TextView priceText=customView.findViewById(R.id.customView_Price);

        objectIdText.setText(trip.get(position).getObjectId());
        dateText.setText(trip.get(position).getDate());
        timeText.setText(trip.get(position).getTime());
        fromText.setText(trip.get(position).getFrom());
        destinationText.setText(trip.get(position).getDestination());
        capacityText.setText(trip.get(position).getCapacity());
        priceText.setText(trip.get(position).getPrice());

        return customView;
    }
}
