package com.custom.settings;

import android.content.Context;
import android.content.Intent;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class SettingsPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("settings")) {

            String name = data.getString(0);
            //String message = "Hello, " + name;
            //callbackContext.success(message);
            Context context = this.cordova.getContext();
            cordova.getThreadPool().execute(new Runnable() {
                public void run() {
                    Intent intent = new Intent(context, SettingsMainActivity.class);
                    context.startActivity(intent);
                    callbackContext.success(message);
                }
            });


            return true;

        } else {
            
            return false;

        }
    }
}
