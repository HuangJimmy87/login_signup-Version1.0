package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Context context = this;
    EditText et1,et2;
    TextView text3;
    Button b1,b2;
    WebView webview;
    String url = "http://192.168.88.246:8877/login/login.php"; //要連線的地址abcd
    CookieManager cookieManager;    //截取的
    String cookieStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedClosableObjects()
                .detectLeakedSqlLiteObjects().penaltyDeath().penaltyLog().build());

        Wc(context);

        et1=findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        text3=findViewById(R.id.text3);
        b1=findViewById(R.id.button);
        b2=findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String r = DBphp.DBstring(et1.getText().toString(),et2.getText().toString(),cookieStr,url);
                text3.setText(r);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_signup);
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void Wc(Context context) {
        webview=new WebView(context);
        CookieSyncManager.createInstance(context);
        cookieManager = CookieManager.getInstance();

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                cookieManager.setAcceptCookie(true);
                cookieStr=cookieManager.getCookie(url);
            }
        });

        webview.loadUrl(url);
        webview.clearCache(true);
        webview.clearHistory();

        cookieManager.removeAllCookie();
        cookieManager.removeSessionCookie();
    }
}