

package net.appic.hack.widget;

import android.app.PendingIntent;
import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.RemoteViews;

import net.appic.hack.R;
import net.appic.hack.activity.HomeActivity;
import net.appic.hack.provider.FeedData;
import net.appic.hack.provider.FeedData.EntryColumns;
import net.appic.hack.utils.ThrottledContentObserver;

public class TickerWidgetService extends Service {
    private ThrottledContentObserver mContentObserver;

    @Override
    public void onCreate() {
        super.onCreate();

        mContentObserver = new ThrottledContentObserver(new Handler(), 3000) {
            @Override
            public void onChangeThrottled() {
                updateWidgets();
            }
        };
        getContentResolver().registerContentObserver(EntryColumns.ALL_ENTRIES_CONTENT_URI, true, mContentObserver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        updateWidgets();

        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWidgets() {
        RemoteViews widget = new RemoteViews(getPackageName(), R.layout.ticker_widget);
        widget.setOnClickPendingIntent(R.id.feed_ticker_tap_area, PendingIntent.getActivity(this, 0, new Intent(this, HomeActivity.class), 0));

        Cursor unread = getContentResolver().query(FeedData.EntryColumns.CONTENT_URI, new String[]{FeedData.ALL_UNREAD_NUMBER}, null, null, null);
        if (unread != null) {
            if (unread.moveToFirst()) {
                int unread_count = unread.getInt(0);
                if (unread_count > 0) {
                    widget.setTextViewText(R.id.feed_ticker, String.valueOf(unread_count));
                    widget.setViewVisibility(R.id.feed_ticker, View.VISIBLE);
                    widget.setViewVisibility(R.id.feed_ticker_circle, View.VISIBLE);
                } else {
                    widget.setViewVisibility(R.id.feed_ticker, View.INVISIBLE);
                    widget.setViewVisibility(R.id.feed_ticker_circle, View.INVISIBLE);
                }
            }
            unread.close();
        }

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        appWidgetManager.updateAppWidget(new ComponentName(getPackageName(), TickerWidgetProvider.class.getName()), widget);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getContentResolver().unregisterContentObserver(mContentObserver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
