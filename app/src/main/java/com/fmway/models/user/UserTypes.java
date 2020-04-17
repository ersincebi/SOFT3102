package com.fmway.models.user;

public enum UserTypes {
    ADMIN ("admin")
    ,PASSENGER ("passenger")
    ,DRIVER ("driver")
    ,BLOCKED ("blocked");

    private final String shortCode;

    UserTypes(String role) {
        this.shortCode = role;
    }

    public String getUserType() {
        return this.shortCode;
    }
}
