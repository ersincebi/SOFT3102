package com.fmway.operations.commonActivities;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {

    /**
     * activity constructor
     * initializes the parse for all app
     */
    public void onCreate(){
        super.onCreate();
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("S0TmqpzRndRa77guMfU39ayEniypzUnkq85YqXLl")
                .clientKey("Ts9Aqp3tXgXQNheiGCwy3LEVheCnTjxoUqAhW6U4")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
