

package net.appic.hack.widget;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import net.appic.hack.R;
import net.appic.hack.provider.FeedData.FeedColumns;
import net.appic.hack.utils.PrefUtils;

public class WidgetConfigFragment extends PreferenceFragment {

    public static final String ARG_WIDGET_ID = "ARG_WIDGET_ID";
    private static final String NAME_COLUMN = "ifnull(" + FeedColumns.NAME + ',' + FeedColumns.URL + ") as title";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View prefView = super.onCreateView(inflater, container, savedInstanceState);
        final WidgetConfigActivity activity = (WidgetConfigActivity) prefView.getContext();
        final PreferenceCategory feedsPreferenceCategory = (PreferenceCategory) findPreference("widget.visiblefeeds");
        final int widgetId = getArguments().getInt(ARG_WIDGET_ID);

        Cursor cursor = activity.getContentResolver().query(FeedColumns.CONTENT_URI, new String[]{FeedColumns._ID, NAME_COLUMN}, null, null, null);

        if (cursor.moveToFirst()) {
            int[] ids = new int[cursor.getCount() + 1];

            CheckBoxPreference checkboxPreference = new CheckBoxPreference(activity);

            checkboxPreference.setTitle(R.string.all_feeds);
            feedsPreferenceCategory.addPreference(checkboxPreference);
            checkboxPreference.setKey("0");
            checkboxPreference.setDisableDependentsState(true);
            ids[0] = 0;
            for (int n = 1; !cursor.isAfterLast(); cursor.moveToNext(), n++) {
                checkboxPreference = new CheckBoxPreference(activity);
                checkboxPreference.setTitle(cursor.getString(1));
                ids[n] = cursor.getInt(0);
                checkboxPreference.setKey(Integer.toString(ids[n]));
                feedsPreferenceCategory.addPreference(checkboxPreference);
                checkboxPreference.setDependency("0");
            }
            cursor.close();

            activity.findViewById(R.id.save_button).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    StringBuilder builder = new StringBuilder();

                    for (int n = 0, i = feedsPreferenceCategory.getPreferenceCount(); n < i; n++) {
                        CheckBoxPreference preference = (CheckBoxPreference) feedsPreferenceCategory.getPreference(n);

                        if (preference.isChecked()) {
                            if (n == 0) {
                                break;
                            } else {
                                if (builder.length() > 0) {
                                    builder.append(',');
                                }
                                builder.append(preference.getKey());
                            }
                        }
                    }

                    String feedIds = builder.toString();
                    PrefUtils.putString(widgetId + ".feeds", feedIds);

                    int color = PrefUtils.getInt("widget.background", WidgetProvider.STANDARD_BACKGROUND);
                    PrefUtils.putInt(widgetId + ".background", color);

                    int fontSize = Integer.parseInt(PrefUtils.getString("widget.fontsize", "0"));
                    if (fontSize != 0) {
                        PrefUtils.putInt(widgetId + ".fontsize", fontSize);
                    } else {
                        PrefUtils.remove(widgetId + ".fontsize");
                    }

                    // Now we need to update the widget
                    Intent intent = new Intent(activity, WidgetProvider.class);
                    intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
                    intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, new int[]{widgetId});
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(activity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    try {
                        pendingIntent.send();
                    } catch (CanceledException ignored) {
                    }

                    activity.setResult(Activity.RESULT_OK, new Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId));
                    activity.finish();
                }
            });
        } else {
            // no feeds found --> use all feeds, no dialog needed
            cursor.close();
            activity.setResult(Activity.RESULT_OK, new Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId));
        }

        return prefView;
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        addPreferencesFromResource(R.xml.widget_preferences);
    }

}
