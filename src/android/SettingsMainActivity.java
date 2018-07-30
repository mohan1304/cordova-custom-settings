package com.custom.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.apache.cordova.*;

public class SettingsMainActivity extends CordovaActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        Context ctx = this.getApplicationContext();
        int menuResourceID = ctx.getResources().getIdentifier("menu_main",
                "menu", ctx.getPackageName());
        inflater.inflate(menuResourceID, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        final Context ctx = this.getApplicationContext();
        int actionSettingResourceID = ctx.getResources().getIdentifier("action_settings",
                "strings", ctx.getPackageName());
        if(item.getItemId()==actionSettingResourceID){
            this.runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent(ctx, SettingsActivity.class);
                    ctx.startActivity(intent);
                }
            });
        }
        return super.onOptionsItemSelected(item);
        /*switch (item.getItemId()) {
            case actionSettingResourceID:
                Context context = this.getApplicationContext();
                this.runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent(context, SettingsActivity.class);
                    context.startActivity(intent);
                }
            });
            default:
                return super.onOptionsItemSelected(item);
        }*/
    }
}
