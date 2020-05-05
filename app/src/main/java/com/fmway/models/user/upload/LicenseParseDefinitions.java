package com.fmway.models.user.upload;

public class LicenseParseDefinitions {
    /**
     * each string represents a key on parse database under license class
     */
    public String className = "Licences";
    public String imageKey = "image";
    public String uploadKey = "upload";
    public String fileName = "licence.png";
    public String usernameKey = "username";

    public String getClassName() {
        return className;
    }

    public String getImageKey() {
        return imageKey;
    }

    public String getUploadKey() {
        return uploadKey;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUsernameKey() {
        return usernameKey;
    }
}
