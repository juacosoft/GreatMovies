package com.jmdev.greatmovies.base;

import android.app.Application;
import android.content.Context;

public class App extends Application {


    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();


        appComponent = DaggerAppComponent.create();
    }



    public static AppComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }
}