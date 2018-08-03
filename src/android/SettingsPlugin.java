package com.custom.settings;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.Menu;

import android.view.MenuItem;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class SettingsPlugin extends CordovaPlugin {
    private MenuDefinition menuDef = null;
    @Override
    public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) throws JSONException {

        if (action.equals("settings")) {
            final Activity activity = this.cordova.getActivity();
            final Context context = this.cordova.getActivity().getApplicationContext();
            menuDef = new MenuDefinition(callbackContext);
            activity.invalidateOptionsMenu();
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

    @Override
    public Object onMessage(String id, Object data) {
       if(id.equals("onCreateOptionsMenu")){
           if (menuDef != null) {
               menuDef.createMenu((Menu) data, this.cordova.getActivity());
           }
       }else if(id.equals("onOptionsItemSelected")) {
           final Context ctx = this.cordova.getActivity().getApplicationContext();
           int actionSettingResourceID = ctx.getResources().getIdentifier("action_settings",
                   "id", ctx.getPackageName());
           MenuItem item = (MenuItem) data;
           if (item.getItemId() == actionSettingResourceID) {
               this.cordova.getActivity().runOnUiThread(new Runnable() {
                   public void run() {
                       Intent intent = new Intent(ctx, SettingsActivity.class);
                       ctx.startActivity(intent);
                   }
               });
           }
       }
       return super.onMessage(id, data);
    }
}
