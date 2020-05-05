package com.fmway.models.user;

public class UserParseDefinitions {
    /**
     * each string represents a key on parse database under user class
     */
    public String className = "User";
    public String objectIdKey = "objectId";
    public String createdAtKey = "createdAt";
    public String nameKey = "Name";
    public String surnameKey = "Surname";
    public String usernameKey = "username";
    public String balanceKey = "balance";
    public String emailKey = "email";
    public String phoneKey = "Phone";
    public String userTypeKey = "userType";
    public String tripKey = "Trip";

    public String getClassName() {
        return className;
    }

    public String getObjectIdKey() {
        return objectIdKey;
    }

    public String getCreatedAtKey() {
        return createdAtKey;
    }

    public String getNameKey() {
        return nameKey;
    }

    public String getSurnameKey() {
        return surnameKey;
    }

    public String getUsernameKey() {
        return usernameKey;
    }

    public String getBalanceKey() {
        return balanceKey;
    }

    public String getEmailKey() {
        return emailKey;
    }

    public String getPhoneKey() {
        return phoneKey;
    }

    public String getUserTypeKey() {
        return userTypeKey;
    }

    public String getTripKey() {
        return tripKey;
    }
}
