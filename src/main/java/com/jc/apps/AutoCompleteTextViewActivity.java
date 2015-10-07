package com.jc.apps;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by jc on 10/3/2015.
 */
public class AutoCompleteTextViewActivity extends Activity {
    private static final String HISTORY = "com.jc.history";
    private AutoCompleteTextView actv = null;
    private Button searchBtn = null;
    private SharedPreferences sp = null;
    private ArrayAdapter<String> histroyAdapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autocompletetextview_layout);
        init();
    }
    private void init() {
       sp = getSharedPreferences(HISTORY,MODE_WORLD_WRITEABLE);//初始化context后调用
       actv = (AutoCompleteTextView) findViewById(R.id.actv);
       searchBtn = (Button)findViewById(R.id.search_btn);

        String[] histroys =  readHistroy();
        if (histroys != null){
            histroyAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_dropdown_item_1line,
                    histroys);
            actv.setAdapter(histroyAdapter);
        }

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchStr = actv.getText().toString().trim();
                if (searchStr.length() > 0) {
                    addHistroy(searchStr);
                }
            }
        });
    }

    private String[] readHistroy() {
       String histroy = sp.getString(HISTORY,"");
        if (histroy.equals(""))
            return null;
       Log.v("ACTV",histroy);
       histroy = histroy.replaceAll("\\[|\\]","");
       histroy = histroy.substring(0, histroy.length()-2);
       return histroy.split(",");
    }

    private void addHistroy(String str) {
        String histroy = sp.getString(HISTORY,"");
        histroy = histroy.replaceAll("\\["+str+"\\]\\,","");
        histroy = "["+str+"],"+histroy;
        Log.v("ACTV",histroy);
        sp.edit().putString(HISTORY,histroy).commit();
        Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
    }

    private void readOuterAppHistory(){
        Context serverContext = null;
        try {
            serverContext = createPackageContext("com.jc.apps",CONTEXT_IGNORE_SECURITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        SharedPreferences sp = serverContext.getSharedPreferences("",Context.MODE_WORLD_READABLE);

        Toast.makeText(this, "" + sp.getString("HISTROY", ""), Toast.LENGTH_SHORT).show();
    }
}
