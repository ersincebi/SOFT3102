package com.fmway.models.user;

public enum UserTypes {

    /**
     * each enum definition is a role on system
     */
    ADMIN ("admin")
    ,PASSENGER ("passenger")
    ,DRIVER ("driver")
    ,BLOCKED ("blocked");


    private final String shortCode;

    /**
     * when the class called
     * this constructor initializes the enum definition
     * @param role
     */
    UserTypes(String role) {
        this.shortCode = role;
    }

    /**
     * need to be called by caller
     * for returning the string of
     * the definition
     * @return
     */
    public String getUserType() {
        return this.shortCode;
    }
}
