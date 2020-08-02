package com.example.yeshasprabhakar.todo.ucacue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.yeshasprabhakar.todo.R;

public class MapaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.clearCache(true);
        myWebView.clearHistory();
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.loadUrl("https://www.google.com.ec/maps/place/UNIVERSIDAD+CATOLICA+DE+CUENCA+SEDE+CA%C3%91AR/@-2.5521653,-78.9428025,17z/data=!3m1!4b1!4m5!3m4!1s0x91cd661298933781:0xd7ed5dc4a161d4b8!8m2!3d-2.5521707!4d-78.9406085");
    }
}