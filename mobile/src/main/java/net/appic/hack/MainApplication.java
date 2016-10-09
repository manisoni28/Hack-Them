

package net.appic.hack;

import android.app.Application;
import android.content.Context;

import net.appic.hack.utils.PrefUtils;

public class MainApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        PrefUtils.putBoolean(PrefUtils.IS_REFRESHING, false); // init
    }
}
