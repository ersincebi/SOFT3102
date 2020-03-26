package com.example.signuplogin;

import android.app.Activity;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class PostActivity extends ArrayAdapter<String> {
    private final ArrayList<String> date; //String ---> DATE
    private final ArrayList<String> time;
    private final ArrayList<String> from;
    private final ArrayList<String> destination;
    private final ArrayList<Integer> capacity;
    private final ArrayList<Integer> price;

    private final Activity context;

    public PostActivity(ArrayList<String> date,ArrayList<String> time,ArrayList<String> from, ArrayList<String> destination , ArrayList<Integer> capacity,
                      ArrayList<Integer> price, Activity context){
//String ----> Date
        super(context,R.layout.,from);
        this.date=date;
        this.time=time;
        this.from=from;
        this.destination=destination;
        this.capacity=capacity;
        this.price=price;
        this.context=context;
    }

}
