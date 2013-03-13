package com.onearmedbandit;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

public class PrefsActivity extends PreferenceActivity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		addPreferencesFromResource(R.xml.prefs);
		this.addPreferencesFromResource(R.xml.prefs);
		 // Get the custom preference
        Preference customPref = (Preference) findPreference("CustomPrefs");
        customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {

                                public boolean onPreferenceClick(Preference preference) {
                                        Log.v("PrefsActivity", "pref click");
                                        SharedPreferences customSharedPreference = getSharedPreferences(
                                                        "myCustomSharedPrefs", Activity.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = customSharedPreference
                                                        .edit();
                                        editor.putString("myCustomPref",
                                                        "The preference has been clicked");
                                        editor.commit();
                                        return true;
                                }

                        });
}
	}
