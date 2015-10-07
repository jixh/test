package com.jc.apps;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jc on 9/30/2015.
 */
public class MdActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

      final    EditText userET = new EditText(this);
      final   EditText snET = new EditText(this);

        Button btn = new Button(this);
        btn.setText("verfiy sn");
        layout.addView(userET, params);
        layout.addView(snET, params);
        layout.addView(btn,params);

        setContentView(layout);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if (userET.getText() == null || snET.getText() == null)
                return;
            if (snET.getText().toString().length() != 16)
                 return;
                try {
                    MessageDigest md = MessageDigest.getInstance("md5");
                    md.reset();
                    md.update(userET.getText().toString().getBytes());
                    md.update(userET.getText().toString().getBytes());
                    byte[] bytes = md.digest();

                    String result = String.valueOf(bytes).toString();
                    Log.v("MdActivity","result="+result);
                    StringBuilder strs = new StringBuilder();
                    for (int i = 0;i<result.length();i+=2){
                        strs.append(result.charAt(i));
                    }
                    if (snET.getText().toString().equalsIgnoreCase(strs.toString())){
                        Toast.makeText(MdActivity.this,"right SN"+strs.toString(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(MdActivity.this,"wrong SN"+strs.toString(),Toast.LENGTH_SHORT).show();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
