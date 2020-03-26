package com.example.userOperations;

import android.app.Activity;
import android.widget.ArrayAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class PostActivity extends ArrayAdapter<String> {
    private final ArrayList<String> date; //String ---> DATE
    private final ArrayList<String> time;
    private final ArrayList<String> from;
    private final ArrayList<String> destination;
    private final ArrayList<String> capacity;
    private final ArrayList<String> price;

    private final Activity context;

    public PostActivity(ArrayList<String> date,ArrayList<String> time,ArrayList<String> from, ArrayList<String> destination , ArrayList<String> capacity,
                      ArrayList<String> price, Activity context){
//String ----> Date
        super(context,R.layout.customview_listtrips,from);
        this.date=date;
        this.time=time;
        this.from=from;
        this.destination=destination;
        this.capacity=capacity;
        this.price=price;
        this.context=context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=this.context.getLayoutInflater();
        View customView=layoutInflater.inflate(R.layout.customview_listtrips,null,true);
        TextView dateText=customView.findViewById(R.id.customView_date);
        TextView timeText=customView.findViewById(R.id.customView_Time);
        TextView fromText=customView.findViewById(R.id.customView_From);
        TextView destinationText=customView.findViewById(R.id.customView_Dest);
        TextView capacityText=customView.findViewById(R.id.customView_Capacity);
        TextView priceText=customView.findViewById(R.id.customView_Price);

        dateText.setText(date.get(position));
        timeText.setText(time.get(position));
        fromText.setText(from.get(position));
        destinationText.setText(destination.get(position));
        capacityText.setText(capacity.get(position));
        priceText.setText(price.get(position));



        return customView;
    }
}
