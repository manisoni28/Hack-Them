

package net.appic.hack.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.IBinder;
import android.os.SystemClock;

import net.appic.hack.Constants;
import net.appic.hack.utils.PrefUtils;

public class RefreshService extends Service {
    public static final String SIXTY_MINUTES = "3600000";
    private final OnSharedPreferenceChangeListener mListener = new OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (PrefUtils.REFRESH_INTERVAL.equals(key)) {
                restartTimer(false);
            }
        }
    };
    private AlarmManager mAlarmManager;
    private PendingIntent mTimerIntent;

    @Override
    public IBinder onBind(Intent intent) {
        onRebind(intent);
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true; // we want to use rebind
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PrefUtils.registerOnPrefChangeListener(mListener);
        restartTimer(true);
    }

    private void restartTimer(boolean created) {
        if (mTimerIntent == null) {
            mTimerIntent = PendingIntent.getBroadcast(this, 0, new Intent(this, RefreshAlarmReceiver.class), 0);
        } else {
            mAlarmManager.cancel(mTimerIntent);
        }

        int time = 3600000;
        try {
            time = Math.max(60000, Integer.parseInt(PrefUtils.getString(PrefUtils.REFRESH_INTERVAL, SIXTY_MINUTES)));
        } catch (Exception ignored) {
        }

        long elapsedRealTime = SystemClock.elapsedRealtime();
        long initialRefreshTime = elapsedRealTime + 10000;

        if (created) {
            long lastRefresh = PrefUtils.getLong(PrefUtils.LAST_SCHEDULED_REFRESH, 0);

            // If the system rebooted, we need to reset the last value
            if (elapsedRealTime < lastRefresh) {
                lastRefresh = 0;
                PrefUtils.putLong(PrefUtils.LAST_SCHEDULED_REFRESH, 0);
            }

            if (lastRefresh > 0) {
                // this indicates a service restart by the system
                initialRefreshTime = Math.max(initialRefreshTime, lastRefresh + time);
            }
        }

        mAlarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, initialRefreshTime, time, mTimerIntent);
    }

    @Override
    public void onDestroy() {
        if (mTimerIntent != null) {
            mAlarmManager.cancel(mTimerIntent);
        }
        PrefUtils.unregisterOnPrefChangeListener(mListener);
        super.onDestroy();
    }

    public static class RefreshAlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            context.startService(new Intent(context, FetcherService.class).setAction(FetcherService.ACTION_REFRESH_FEEDS).putExtra(Constants.FROM_AUTO_REFRESH, true));
        }
    }
}
