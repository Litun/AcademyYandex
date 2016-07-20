package ru.ya.litun.academyyandex;

import android.app.Application;
import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import ru.ya.litun.academyyandex.model.DatabaseHelper;

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

    private static DatabaseHelper databaseHelper;

    public static DatabaseHelper getHelper() {
        return databaseHelper;
    }

    public static void setHelper(Context context) {
        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }

    public static void releaseHelper() {
        OpenHelperManager.releaseHelper();
        databaseHelper = null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        StringFormatUtils.initResources(this);
        setHelper(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        dataManager = null;
    }
}
