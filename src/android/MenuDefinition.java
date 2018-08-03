package com.custom.settings;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;

import org.apache.cordova.CallbackContext;

public class MenuDefinition {
    private CallbackContext callbackContext = null;
    MenuDefinition(CallbackContext callback){
        this.callbackContext = callback;
    }
    public void createMenu(final Menu menu, final Activity activity) {
        activity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                MenuInflater inflater = activity.getMenuInflater();
                Context ctx = activity.getApplicationContext();
                int menuResourceID = ctx.getResources().getIdentifier("menu_main",
                        "menu", ctx.getPackageName());
                inflater.inflate(menuResourceID, menu);
                callbackContext.success("sucess");
            }
        });
    }
}
