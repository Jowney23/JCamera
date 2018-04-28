package com.jowney.jowney.jcamera.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import com.jowney.jowney.jcamera.R;

/**
 * Created by Jowney on 2018/4/28.
 */

public class SettingFragmen extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_scenario_1);
    }
}
