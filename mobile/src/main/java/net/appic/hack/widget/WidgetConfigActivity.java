

package net.appic.hack.widget;

import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.appic.hack.R;

public class WidgetConfigActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setResult(RESULT_CANCELED);

        Bundle extras = getIntent().getExtras();

        int widgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
        if (extras != null) {
            widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        if (widgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        setContentView(R.layout.widget_config);

        // Display the fragment as the main content.
        if (savedInstanceState == null) {
            WidgetConfigFragment fragment = new WidgetConfigFragment();
            Bundle args = new Bundle();
            args.putInt(WidgetConfigFragment.ARG_WIDGET_ID, widgetId);
            fragment.setArguments(args);
            getFragmentManager().beginTransaction().replace(R.id.fragment, fragment).commit();
        }
    }

}
