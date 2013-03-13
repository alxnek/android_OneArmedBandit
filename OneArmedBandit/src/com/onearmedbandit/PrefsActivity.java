package com.onearmedbandit;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PrefsActivity extends PreferenceActivity
{

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		addPreferencesFromResource(R.xml.prefs);
		this.addPreferencesFromResource(R.xml.prefs);
	}
}
