package com.jc.apps;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by jc on 10/2/2015.
 */
public class Test extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate        (savedInstanceState);
        TextView tv = new TextView(this);
        setContentView(tv);
    }
        @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){

        }else{

        }
        if (newConfig.keyboard == Configuration.KEYBOARD_12KEY){

        }
    }
}
