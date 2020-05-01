package com.fmway.models.chat;

public class ChatParseDefinitions {

    /**
     * each string represents a key on parse database under chat class
     */
    public String className = "Chat";
    public String objectIdKey = "objectId";
    public String createdAtKey = "createdAt";
    public String tripIdKey = "tripId";
    public String personIdKey = "personId";
    public String messageKey = "message";

    public String getClassName() {
        return className;
    }

    public String getObjectIdKey() {
        return objectIdKey;
    }

    public String getCreatedAtKey() {
        return createdAtKey;
    }

    public String getTripIdKey() {
        return tripIdKey;
    }

    public String getPersonIdKey() {
        return personIdKey;
    }

    public String getMessageKey() {
        return messageKey;
    }
}
