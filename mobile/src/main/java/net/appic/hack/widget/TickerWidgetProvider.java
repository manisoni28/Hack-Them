

package net.appic.hack.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class TickerWidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Intent svcIntent = new Intent(context, TickerWidgetService.class);
        context.startService(svcIntent);

        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }
}
