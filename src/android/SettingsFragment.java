package com.custom.settings;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link android.app.Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Context ctx = this.getActivity().getApplicationContext();
        int preferencesResourceID = ctx.getResources().getIdentifier("preferences",
                "xml", ctx.getPackageName());
        // Load the preferences from an XML resource
        addPreferencesFromResource(preferencesResourceID);
    }

}
