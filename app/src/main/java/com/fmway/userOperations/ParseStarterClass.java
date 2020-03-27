package com.fmway.userOperations;

import android.app.Application;

import com.parse.Parse;

public class ParseStarterClass extends Application {

    public void onCreate(){    //register bağlantı
        super.onCreate();

     Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG); // debug haber ver



        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("S0TmqpzRndRa77guMfU39ayEniypzUnkq85YqXLl")
                .clientKey("Ts9Aqp3tXgXQNheiGCwy3LEVheCnTjxoUqAhW6U4")
                .server("https://parseapi.back4app.com/")  //FMWay Işık - 3.9.0 parseV
                .build()
        );


    }


}
