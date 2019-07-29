package com.example.instagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("57UBDmUqMusog85neI2wEKvqPgVi6DBQMeSeLFNv")
                // if defined
                .clientKey("QnhFRXOdShSJGQSpmwVthOhQrXBTs5qe13uHU1S6")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
