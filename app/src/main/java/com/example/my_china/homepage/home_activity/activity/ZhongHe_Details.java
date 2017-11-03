package com.example.my_china.homepage.home_activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.my_china.R;

public class ZhongHe_Details extends AppCompatActivity {


    private ImageView back_ZhongHe;
    private WebView web_Views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhong_he__details);
        initView();
        Intent intent = getIntent();
        final String webView = intent.getStringExtra("webView");

        back_ZhongHe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        final WebSettings settings = web_Views.getSettings();
        settings.setJavaScriptEnabled(true);
        web_Views.loadUrl(webView);
        web_Views.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                web_Views.loadUrl(webView);
            }
        });
    }

    private void initView() {
        back_ZhongHe = (ImageView) findViewById(R.id.back_ZhongHe);
        web_Views = (WebView) findViewById(R.id.web_View);
    }
}
