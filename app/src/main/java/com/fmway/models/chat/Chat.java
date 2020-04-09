package com.fmway.models.chat;

public class Chat{
    private String tripId;
    private String personId;
    private String message;


    public Chat(String tripId
                , String personId
                , String message){

        this.tripId = tripId;
        this.personId = personId;
        this.message = message;

    }

    public String getTripId() { return tripId; }

    public String getPersonId() { return personId; }

    public String getMessage() { return message; }

    public String getBody() { return personId + ": " + message; }

}
