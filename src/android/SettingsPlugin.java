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
        }else if(action.equals("getPreference")){
            String key = data.getString(0);
            String value = getPreferenceValue(key);
            if(value!=null){
                callbackContext.success(value);
            }else{
                callbackContext.error("No preference defined");
            }
            return true;
        }else {
            return false;
        }
    }
	public String getPreferenceValue(String name){
       String value=null;
       SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this.cordova.getActivity());
       Map<String,?> prefs = sharedPrefs.getAll();
       if(prefs.containsKey(name)) {
           Object obj = prefs.get(name);
           if(obj!=null) {
               value = obj.toString();
           }else{
               value="";
           }
       }
       return value;
    }
}
