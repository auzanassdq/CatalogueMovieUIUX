package com.example.auzan.cataloguemovieuiux.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import com.example.auzan.cataloguemovieuiux.R;
import com.example.auzan.cataloguemovieuiux.utils.DailyReminderReceive;
import com.example.auzan.cataloguemovieuiux.utils.UpcomingReminderReceive;


/**
 * Created by auzan on 10/27/2018.
 * Github: @auzanassdq
 */
public class MyPreferenceFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener, Preference.OnPreferenceClickListener {

    String reminder_daily, reminder_upcoming, setting_locale;

    private DailyReminderReceive dailyReminderReceive = new DailyReminderReceive();
    private UpcomingReminderReceive upcomingReminderReceive = new UpcomingReminderReceive();

    @Override
    public void onCreatePreferences(Bundle bundle, String rootkey) {
        addPreferencesFromResource(R.xml.preference);

        reminder_daily = getString(R.string.key_reminder_daily);
        reminder_upcoming = getString(R.string.key_reminder_upcoming);
        setting_locale = getString(R.string.key_setting_locale);

        findPreference(reminder_daily).setOnPreferenceChangeListener(this);
        findPreference(reminder_upcoming).setOnPreferenceChangeListener(this);
        findPreference(setting_locale).setOnPreferenceClickListener(this);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        String key = preference.getKey();
        boolean isOn = (boolean) o;

        if (key.equals(reminder_daily)) {
            if (isOn) {
                dailyReminderReceive.setRepeatingAlarm(getActivity());
            } else {
                dailyReminderReceive.cancelAlarm(getActivity());
            }

            Toast.makeText(getActivity(), (isOn ? "Daily Reminder Active" : "Daily Reminder Deactive"), Toast.LENGTH_SHORT).show();
            return true;
        }

        if (key.equals(reminder_upcoming)) {
            if (isOn) {
                upcomingReminderReceive.setReleaseAlarm(getActivity());
            } else {
                upcomingReminderReceive.cancelAlarm(getActivity());
            }

            Toast.makeText(getActivity(), (isOn ? "Upcoming Reminder Active" : "Upcoming Reminder Deactive"), Toast.LENGTH_SHORT).show();
            return true;
        }

        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();

        if (key.equals(setting_locale)) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
            return true;
        }

        return false;
    }

}
