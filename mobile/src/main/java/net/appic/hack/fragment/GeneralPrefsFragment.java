

package net.appic.hack.fragment;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import net.appic.hack.MainApplication;
import net.appic.hack.R;
import net.appic.hack.service.RefreshService;
import net.appic.hack.utils.PrefUtils;

public class GeneralPrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.general_preferences);

        setRingtoneSummary();

        Preference preference = findPreference(PrefUtils.REFRESH_ENABLED);
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Activity activity = getActivity();
                if (activity != null) {
                    if (Boolean.TRUE.equals(newValue)) {
                        activity.startService(new Intent(activity, RefreshService.class));
                    } else {
                        PrefUtils.putLong(PrefUtils.LAST_SCHEDULED_REFRESH, 0);
                        activity.stopService(new Intent(activity, RefreshService.class));
                    }
                }
                return true;
            }
        });

        preference = findPreference(PrefUtils.LIGHT_THEME);
        preference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                PrefUtils.putBoolean(PrefUtils.LIGHT_THEME, Boolean.TRUE.equals(newValue));

                PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext()).edit().commit(); // to be sure all prefs are written

                android.os.Process.killProcess(android.os.Process.myPid()); // Restart the app

                // this return statement will never be reached
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        // The ringtone summary text should be updated using
        // OnSharedPreferenceChangeListener(), but I can't get it to work.
        // Updating in onResume is a very simple hack that seems to work, but is inefficient.
        setRingtoneSummary();

        super.onResume();

    }

    private void setRingtoneSummary() {
        Preference ringtone_preference = findPreference(PrefUtils.NOTIFICATIONS_RINGTONE);
        Uri ringtoneUri = Uri.parse(PrefUtils.getString(PrefUtils.NOTIFICATIONS_RINGTONE, ""));
        if (TextUtils.isEmpty(ringtoneUri.toString())) {
            ringtone_preference.setSummary(R.string.settings_notifications_ringtone_none);
        } else {
            Ringtone ringtone = RingtoneManager.getRingtone(MainApplication.getContext(), ringtoneUri);
            if (ringtone == null) {
                ringtone_preference.setSummary(R.string.settings_notifications_ringtone_none);
            } else {
                ringtone_preference.setSummary(ringtone.getTitle(MainApplication.getContext()));
            }
        }
    }
}
