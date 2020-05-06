package com.fmway.models.user.passenger;

public class RatingParseDefinitions {
    /**
     * each string represents a key on parse database under user class
     */
    public String className = "Ratings";
    public String objectIdKey = "objectId";
    public String createdAtKey = "createdAt";
    public String userIdKey = "userId";
    public String tripIdKey = "tripId";
    public String ratingValueKey = "ratingValue";
    public String commentKey = "comment";

    public String getClassName() {
        return className;
    }

    public String getObjectIdKey() {
        return objectIdKey;
    }

    public String getCreatedAtKey() {
        return createdAtKey;
    }

    public String getUserIdKey() {
        return userIdKey;
    }

    public String getTripIdKey() {
        return tripIdKey;
    }

    public String getRatingValueKey() {
        return ratingValueKey;
    }

    public String getCommentKey() {
        return commentKey;
    }
}
