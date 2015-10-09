package com.jc.apps;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by jc on 10/2/2015.
 */
public class WebClientActivity extends Activity {

    EditText searchET = null;
    TextView title = null;
    WebView wv = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        title = new TextView(this);
        LinearLayout.LayoutParams titleParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.addView(title, titleParam);

        initHeader(layout);
        initWV(layout);
        setContentView(layout);
    }

    private void initWV(View v) {
        wv = new WebView(this);
        LinearLayout.LayoutParams wvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        wvParams.weight = 1;
        ((LinearLayout)v).addView(wv, wvParams);

        setWV();

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                title.setText("" + view.getTitle());
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    private void setWV() {
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.getSettings().setDefaultTextEncodingName("utf-8");
        wv.getSettings().setAllowFileAccess(true);
        wv.getSettings().setAllowFileAccessFromFileURLs(true);
    }

    private void initHeader(View v) {
        LinearLayout headerLayout = new LinearLayout(this);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams headerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        ((LinearLayout)v).addView(headerLayout, headerLayoutParams);

        searchET = new EditText(this);
        LinearLayout.LayoutParams headerETParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        headerETParams.weight = 1;
        searchET.setHint("pl,input something");
        headerLayout.addView(searchET, 0, headerETParams);

        LinearLayout.LayoutParams headerBtnParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        Button searchBtn = new Button(this);
        searchBtn.setText("search");
        headerLayout.addView(searchBtn, 1, headerBtnParams);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlString = searchET.getText().toString().trim();
                wv.loadUrl(urlString);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK  && wv.canGoBack()){
                wv.goBack();
                return true;
        }
        return false;
    }
}
