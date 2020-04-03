package com.fmway.libs.chat;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.fmway.R;

public class Chat extends ArrayAdapter<String> {
    private String tripId;
    private String userId;
    private String message;

    private final Activity context;

    public Chat(String tripId
                , String userId
                , String message
                , Activity context){

        super(context, R.layout.activity_trip_chat);
        this.tripId = tripId;
        this.userId = userId;
        this.message = message;

        this.context = context;
    }

    public String getTripId() { return tripId; }

    public String getUserId() { return userId; }

    public String getMessage() { return message; }
}
