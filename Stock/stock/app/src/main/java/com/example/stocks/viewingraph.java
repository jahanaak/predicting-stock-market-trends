package com.example.stocks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONObject;

public class viewingraph extends AppCompatActivity {
    private WebView webView;
    SharedPreferences sh;

    private ProgressBar loadingPB;
    TextView t1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewingraph);
//        Toast.makeText(getApplicationContext(),"Haiiiiiiiiiii",Toast.LENGTH_LONG).show();
        loadingPB = findViewById(R.id.idPBLoading);
        loadingPB.setVisibility(View.VISIBLE);

        t1=findViewById(R.id.progressText);
        t1.setVisibility(View.VISIBLE);
        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // Find the WebView in the layout
        webView = findViewById(R.id.webView);


        // Configure WebView settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript

        // Load a web page
//        webView.loadUrl("https://www.example.com");
        webView.loadUrl(sh.getString("ip", "") + "/api/api_viewingraph");

//        http://192.168.29.180:5502/hodviewtimetablepost

        // Set a WebViewClient to handle events
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // Page started loading
//
                loadingPB.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                // Page finished loading
//                Toast.makeText(getApplicationContext(),"Haiiiiiiddddiiiii",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                // Handle error
            }
        });
    }

    // Handle back button press for WebView navigation



    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}