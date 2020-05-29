package com.fmway.models.user.upload;

import android.media.Image;

public class Licence {
    private String objectId;
    private Image file;
    private String username;
    private String upload;
    private String userID;


    public Licence(
            String objectId
            ,Image file
            ,String username
            ,String upload
            ,String userID
    ){
        this.objectId=objectId;
        this.file=file;
        this.username=username;
        this.upload=upload;
        this.userID=userID;
    }

    public String getObjectId(){ return objectId;}
    public Image getFile(){ return file;}
    public String getUsername(){ return username;}
    public String getUpload(){ return upload;}
    public String getUserID(){ return userID;}


}
