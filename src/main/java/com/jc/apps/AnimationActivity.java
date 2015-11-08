package com.jc.apps;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by jc on 11/8/2015.
 */
public class AnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void animate(View v){
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.tv));
    }
}
