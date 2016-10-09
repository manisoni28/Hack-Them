

package net.appic.hack.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import net.appic.hack.R;
import net.appic.hack.provider.FeedDataContentProvider;
import net.appic.hack.utils.UiUtils;

import java.util.Locale;

public class AddGoogleNewsActivity extends BaseActivity {

    private static final int[] TOPIC_NAME = new int[]{R.string.google_news_technology,R.string.tech_crunch,R.string.nextweb,R.string.firstpost,R.string.mashable};

    private static final String[] TOPIC_CODES = new String[]{ "t" };

    private static final int[] CB_IDS = new int[]{ R.id.cb_technology,R.id.tech_crunch,R.id.thenextweb,R.id.firstpost,R.id.mashable};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        UiUtils.setPreferenceTheme(this);
        super.onCreate(savedInstanceState);

        setResult(RESULT_CANCELED);

        setContentView(R.layout.activity_add_google_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return false;
    }

    public void onClickOk(View view) {
            if (((CheckBox) findViewById(CB_IDS[0])).isChecked()) {
                String url = "http://news.google.com/news?hl=" + Locale.getDefault().getLanguage() + "&output=rss";
                if (TOPIC_CODES[0] != null) {
                    url += "&topic=" + TOPIC_CODES[0];
                }
                FeedDataContentProvider.addFeed(this, url, getString(TOPIC_NAME[0]), true);
        }
        if (((CheckBox) findViewById(CB_IDS[1])).isChecked()) {
            String url = "http://techcrunch.com/feed/";
            FeedDataContentProvider.addFeed(this, url, getString(TOPIC_NAME[1]), true);
        }
        if (((CheckBox) findViewById(CB_IDS[2])).isChecked()) {
            String y1 = "http://feeds2.feedburner.com/thenextweb";
            FeedDataContentProvider.addFeed(this, y1, getString(TOPIC_NAME[2]), true);
        }
        if (((CheckBox) findViewById(CB_IDS[3])).isChecked()) {
            String y2 = "http://tech.firstpost.com/feed";
            FeedDataContentProvider.addFeed(this, y2, getString(TOPIC_NAME[3]), true);
        }
        if (((CheckBox) findViewById(CB_IDS[4])).isChecked()) {
            String y3 = "http://feeds.mashable.com/Mashable";
            FeedDataContentProvider.addFeed(this, y3, getString(TOPIC_NAME[4]), true);
        }
        setResult(RESULT_OK);
        finish();
    }

    public void onClickCancel(View view) {
        finish();
    }
}

