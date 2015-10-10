package com.jc.apps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//        wv.getSettings().setAllowFileAccessFromFileURLs(true);
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

    public void getUrlFromText(String msg,TextView textview) {
        Pattern pattern = Pattern.compile("(http://|https://|www.){1}[[^\\u4e00-\\u9fa5]&&\\w\\.\\-/:\\?\\&\\%\\@\\_a-zA-Z0-9\\=\\,]+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(msg);
        int startPoint=0;

        SpannableString sps=new SpannableString(msg);
        String url;
        while (matcher.find(startPoint)) {
            int endPoint = matcher.end();
            url = matcher.group();
            ClickableSpan clickSpan = new ClickSpan(url);
            sps.setSpan(clickSpan, endPoint - url.length(), endPoint, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            startPoint = endPoint;
        }
        textview.setText(sps);
        url = null;

    }

    class ClickSpan extends ClickableSpan {
        String text;
        TextPaint paint = null;

        public ClickSpan(String text) {
            super();
            this.text = text;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.linkColor = Color.parseColor("#00648f");
        }

        public void onClick(View widget) {
            Intent intent = new Intent(WebClientActivity.this,
                    WebClientActivity.class);
            final Bundle bundle = new Bundle();
            if (!text.startsWith( "https+://")) {
                text = "https://" + text;
            }
            bundle.putString("url", text);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK  && wv.canGoBack()){
                wv.goBack();
                return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyWV();
    }

    private void destroyWV() {
        wv.removeAllViews();
        wv.destroy();
        wv = null;
    }
}
