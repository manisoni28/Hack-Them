

package net.appic.hack.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import net.appic.hack.R;
import net.appic.hack.activity.HomeActivity;
import net.appic.hack.utils.PrefUtils;

public class WidgetProvider extends AppWidgetProvider {
    public static final int STANDARD_BACKGROUND = 0x7c000000;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            Intent svcIntent = new Intent(context, WidgetService.class);

            svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            svcIntent.putExtra(AppWidgetManager.EXTRA_CUSTOM_INFO, PrefUtils.getInt(appWidgetId + ".fontsize", 0));
            svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));

            RemoteViews widget = new RemoteViews(context.getPackageName(), R.layout.widget);
            widget.setOnClickPendingIntent(R.id.feed_icon, PendingIntent.getActivity(context, 0, new Intent(context, HomeActivity.class), 0));
            widget.setPendingIntentTemplate(R.id.feedsListView, PendingIntent.getActivity(context, 0, new Intent(Intent.ACTION_VIEW), 0));

            widget.setRemoteAdapter(R.id.feedsListView, svcIntent);
            widget.setInt(R.id.feedsListView, "setBackgroundColor", PrefUtils.getInt(appWidgetId + ".background", STANDARD_BACKGROUND));

            appWidgetManager.updateAppWidget(appWidgetId, widget);
        }

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
