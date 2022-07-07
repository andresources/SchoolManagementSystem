package com.pharma;

import android.app.Application;

import com.pharma.di.DaggerMyComponent;
import com.pharma.di.DatabaseModule;
import com.pharma.di.MyComponent;
import com.pharma.di.SharedPrefModule;
import com.pharma.di.UserModule;


public class MyApp extends Application {
    private static MyApp app;
    public static String jwt_token="";
    private MyComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appComponent = DaggerMyComponent.builder()
                .sharedPrefModule(new SharedPrefModule(this))
                .userModule(new UserModule("Hariiii"))
                .databaseModule(new DatabaseModule(this))
                .build();
    }

    public static MyApp app(){
        return app;
    }

    public MyComponent appComponent(){
        return appComponent;
    }
}
