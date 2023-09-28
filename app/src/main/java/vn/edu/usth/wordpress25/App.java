package vn.edu.usth.wordpress25;


import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import vn.edu.usth.wordpress25.eventbus.PreferenceUtils;

public class App extends MultiDexApplication {
    private static App instance = null;
    public static App getInstance(){
        return instance;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        getInstance(this);
        MultiDex.install(this);
        PreferenceUtils.init();
    }
    public static synchronized void getInstance(App deworksApp){
        if (instance == null) instance = deworksApp;
    }
}
