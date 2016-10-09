

package net.appic.hack.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.SystemClock;

import net.appic.hack.Constants;
import net.appic.hack.service.FetcherService;
import net.appic.hack.service.RefreshService;
import net.appic.hack.utils.PrefUtils;

public class ConnectionChangeReceiver extends BroadcastReceiver {

    private boolean mConnection = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (mConnection && intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
            mConnection = false;
        } else if (!mConnection && !intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)) {
            mConnection = true;

            if (!PrefUtils.getBoolean(PrefUtils.IS_REFRESHING, false) && PrefUtils.getBoolean(PrefUtils.REFRESH_ENABLED, true)) {
                int time = 3600000;
                try {
                    time = Math.max(60000, Integer.parseInt(PrefUtils.getString(PrefUtils.REFRESH_INTERVAL, RefreshService.SIXTY_MINUTES)));
                } catch (Exception ignored) {
                }

                long lastRefresh = PrefUtils.getLong(PrefUtils.LAST_SCHEDULED_REFRESH, 0);
                long elapsedRealTime = SystemClock.elapsedRealtime();

                // If the system rebooted, we need to reset the last value
                if (elapsedRealTime < lastRefresh) {
                    lastRefresh = 0;
                    PrefUtils.putLong(PrefUtils.LAST_SCHEDULED_REFRESH, 0);
                }

                if (lastRefresh == 0 || elapsedRealTime - lastRefresh > time) {
                    context.startService(new Intent(context, FetcherService.class).setAction(FetcherService.ACTION_REFRESH_FEEDS).putExtra(Constants.FROM_AUTO_REFRESH, true));
                }
            }
        }
    }
}