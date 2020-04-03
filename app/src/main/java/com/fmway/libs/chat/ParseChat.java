package com.fmway.libs.chat;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

@ParseClassName("Chat")

public class ParseChat extends ParseObject {
    public static final String tripId = "tripId";
    public static final String userId = "userId";
    public static final String message = "message";

    private ParseQuery<ParseObject> parseQuery;

    public String getTripId(){ return getString(this.tripId); }

    public String getUserId(){ return getString(this.userId); }

    public String getMessage(){ return getString(this.message); }

    public void setTripId(String tripId){ put(this.tripId, tripId); }

    public void setUserId(String userId){ put(this.userId, userId); }

    public void setMessage(String text){ put(this.message, text); }

    public List<ParseObject> getTripMessages(String tripId) throws ParseException {
        parseQuery = ParseQuery.getQuery("Chat");
        parseQuery.whereEqualTo(this.tripId,tripId);
        List<ParseObject> messageList = parseQuery.find();

        return messageList;
    }
}
