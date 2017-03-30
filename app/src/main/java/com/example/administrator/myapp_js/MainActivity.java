package com.example.administrator.myapp_js;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btn1;
    private Button btn2;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        webView.loadUrl("file:///android_asset/web.html");
        webView.addJavascriptInterface(MainActivity.this, "android");

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:javacalljs()");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("javascript:javacalljswith(" + "'http://blog.csdn.net/Leejizhou'" + ")");
            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String tel, final String message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Uri uri = Uri.parse("smsto:"+tel);
                Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                intent.putExtra("sms_body", message);
                startActivity(intent);

            }
        });
    }

    @JavascriptInterface
    public void startFunction(final String text) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Intent phoneIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + text));
//启动
                startActivity(phoneIntent);
            }
        });
    }

    @JavascriptInterface
    public void tiaozhuan(final String text){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent  intent = new Intent();
                intent.setClass(MainActivity.this,MainB.class);
                startActivity(intent);
            }
        });
    }

}