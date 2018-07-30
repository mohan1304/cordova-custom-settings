package com.custom.settings;

import android.content.Context;
import android.content.Intent;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class SettingsPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, final CallbackContext callbackContext) throws JSONException {

        if (action.equals("settings")) {
            final Context context = this.cordova.getActivity().getApplicationContext();
            /*cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    Intent intent = new Intent(context, SettingsMainActivity.class);
                    context.startActivity(intent);
                    callbackContext.success("sucess");
                }
            });*/
			this.cordova.runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent(context, SettingsMainActivity.class);
                    context.startActivity(intent);
                    callbackContext.success("sucess");
                }
            });
            return true;
        } else {
            return false;
        }
    }
}
