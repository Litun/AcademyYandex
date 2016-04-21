package ru.ya.litun.academyyandex;

import android.app.Application;

/**
 * Created by Litun on 21.04.2016.
 */
public class App extends Application {
    private static DataManager dataManager;

    public static DataManager getDataManager() {
        if (dataManager == null)
            synchronized (DataManager.class) {
                if (dataManager == null)
                    dataManager = new DataManager();
            }
        return dataManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        StringFormatUtils.initResources(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        dataManager.clearCache();
        dataManager = null;
    }
}
