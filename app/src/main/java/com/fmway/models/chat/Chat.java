package com.fmway.models.chat;

public class Chat{
    private String tripId;
    private String personId;
    private String message;

    /**
     * This class is for listing the chat list on parse database
     *
     * each parameter represents a key on parse database
     * @param tripId
     * @param personId
     * @param message
     */
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
