package com.jc.apps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends Activity {
    Class cs[] = {
            MediaStoreActivity.class,
            MdActivity.class,
            WebClientActivity.class,
            AutoCompleteTextViewActivity.class,
            Test.class,
            MediaStoreActivity.class
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }

        LinearLayout layout = new LinearLayout(this);
//        layout.setFitsSystemWindows(true);
//        layout.setClipToPadding(true);
        Toast.makeText(this,""+getStatesBarHeight(),Toast.LENGTH_SHORT).show();
        initLayout(layout);
        setContentView(layout);
    }
    private int getStatesBarHeight(){
        try {
            Class<?>  c = Class.forName("com.android.internal.R$dimen");
            Object inst  = c.newInstance();
            Field field = c.getField("status_bar_height");
            int i = Integer.parseInt(field.get(inst).toString());
            return  getResources().getDimensionPixelSize(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    private void initLayout(LinearLayout layout) {
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(20,getStatesBarHeight(),20,20);

        params.gravity = Gravity.TOP;
        for (int i = 0;i<cs.length;i++){
            final Class ci = cs[i];
            Button btn = new Button(this);
            btn.setText(ci.getSimpleName());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,ci));
                }

            });
            layout.addView(btn, i, params);
        }
    }
}