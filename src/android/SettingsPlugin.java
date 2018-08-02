package com.custom.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Map;

public class SettingsPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) throws JSONException {

        if (action.equals("settings")) {
            final Activity activity = this.cordova.getActivity();
            final Context context = this.cordova.getActivity().getApplicationContext();
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent(context, SettingsActivity.class);
                    context.startActivity(intent);
                    callbackContext.success("sucess");
                }
            });
            return true;
        }else if(action.equals("getPreferences")){
            JSONArray prefArray = getPreferencesArray(data);
            callbackContext.success(prefArray);
            return true;
        }else {
            return false;
        }
    }
	private JSONArray getPreferencesArray(JSONArray data) throws JSONException {
        int count = data.length();
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.cordova.getActivity());
        Map<String, ?> prefs = sharedPrefs.getAll();
        String value = null;
        JSONArray prefArray = new JSONArray();
        for (int i = 0; i < count; i++) {
            String key = data.getString(i);
            JSONObject jsonObj = new JSONObject();
            if (prefs.containsKey(key)) {
                Object obj = prefs.get(key);
                if (obj != null) {
                    jsonObj.put(key, obj);
                } else {
                    jsonObj.put(key, "");
                }
                prefArray.put(jsonObj);
            }
        }
        return prefArray;
    }
}
