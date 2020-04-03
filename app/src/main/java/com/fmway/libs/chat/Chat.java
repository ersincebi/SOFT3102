package com.fmway.libs.chat;

public class Chat{
    private String tripId;
    private String userId;
    private String message;


    public Chat(String tripId
                , String userId
                , String message){

        this.tripId = tripId;
        this.userId = userId;
        this.message = message;

    }

    public String getTripId() { return tripId; }

    public String getUserId() { return userId; }

    public String getMessage() { return message; }

    public String getBody() { return userId + ": " + message; }

}
