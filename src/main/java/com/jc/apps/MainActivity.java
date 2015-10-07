package com.jc.apps;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
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
       setContentView(layout);
    }
}