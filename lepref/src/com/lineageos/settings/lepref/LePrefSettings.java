/*
 *  LeEco Extras Settings Module
 *  Made by @andr68rus 2017
 *  Tweaked and updated by @Gabr0 & @mosimchah
 */

package com.lineageos.settings.lepref;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.view.MenuItem;
import android.os.SystemProperties;

public class LePrefSettings extends PreferenceActivity implements OnPreferenceChangeListener {
	private static final String ENABLE_BATTERY_MODE = "battery_mode";
	private static final String BATTERY_SYSTEM_PROPERTY = "persist.battery.save";

	private SwitchPreference mEnableQC;
	private SwitchPreference mEnableFocusFix;
	private SwitchPreference mBatterySave;

	private SharedPreferences mPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.le_settings);
		mBatterySave = (SwitchPreference) findPreference(ENABLE_BATTERY_MODE);
		mBatterySave.setChecked(SystemProperties.getBoolean(BATTERY_SYSTEM_PROPERTY, true));
		mBatterySave.setOnPreferenceChangeListener(this);
			}
		// Control Batery save
		private void setBatterySave(boolean value) {
		if(value) {
			SystemProperties.set(BATTERY_SYSTEM_PROPERTY, "1");
		} else {
			SystemProperties.set(BATTERY_SYSTEM_PROPERTY, "0");
		}
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        final String key = preference.getKey();
        boolean value;
		if (ENABLE_BATTERY_MODE.equals(key)) {
			value = (Boolean) newValue;
			mBatterySave.setChecked(value);
			setBatterySave(value);
			return true;
	}
      	return false;
    }

}
