package com.fmway.models.user;

public class User {
    private String objectId;
    private String createdAt;
    private String name;
    private String surname;
    private String username;
    private String email;
    public User(
            String objectId
            ,String createdAt
            ,String name
            ,String surname
            ,String username
            ,String email
    ){
        this.objectId = objectId;
        this.createdAt = createdAt;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
    }

    public String getObjectId() {
        return objectId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
