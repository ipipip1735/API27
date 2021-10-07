package mine.webview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/8/11.
 */

public class WebViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  WebView.onCreate  ***********");

        setContentView(R.layout.activity_webview);
//        setContentView(R.layout.activity_fullscreen);
        WebView myWebView = (WebView) findViewById(R.id.webview);
//        myWebView.loadUrl("http://192.168.1.3/ad.html");
//        myWebView.loadUrl("file:///android_asset/ad.html");
        myWebView.loadUrl("file:///android_res/raw/ad.html");

//        String sHtmlTemplate = "<html><head></head><body><img src=\"tp.png\"></body></html>";
//        myWebView.loadDataWithBaseURL("file:///android_asset/", sHtmlTemplate, "text/html", "utf-8",null);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


        myWebView.addJavascriptInterface(new WebAppInterface(this), "android");
        myWebView.setWebViewClient(new MyWebViewClient());
//        myWebView.setWebChromeClient(new MyWebChromeClient());


    }

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("**********  WebView.onNewIntent  ***********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("**********  WebView.onStart  ***********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("**********  WebView.onRestoreInstanceState  ***********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("**********  WebView.onRestart  ***********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("**********  WebView.onResume  ***********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("**********  WebView.onPause  ***********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("**********  Example***onBackPressed  ***********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("**********  WebView.onStop  ***********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("**********  WebView.onSaveInstanceState  ***********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("**********  WebView.onDestroy  ***********");
        super.onDestroy();
    }


// button event functions


    public void init(View view) {
        System.out.println("------>>  init  <<------");
        WebView myWebView = (WebView) findViewById(R.id.webview);
//        myWebView.loadUrl("http://10.0.2.2:8080/test1.asp");

    }

    public void start(View view) {
        System.out.println("------>>  start  <<------");

        WebView myWebView = (WebView) findViewById(R.id.webview);
        String javascript = "document.write('xxxx')";
        ValueCallback<String> callback = new ValueCallback<String>() {
            @Override
            public void onReceiveValue(String value) {
                System.out.println("++++ Runnable.onReceiveValue ++++");
                System.out.println(value);
            }
        };

        myWebView.evaluateJavascript(javascript, callback);

    }

    public void restart(View view) {
        System.out.println("------>>  restart  <<------");
        loadUrl();
//        loadData();
//        loadData64();
//        loadDataWithBaseURL();
    }


    public void stop(View view) {
        System.out.println("------>>  stop  <<------");
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.goForward();
    }


    public void bind(View view) {
        System.out.println("------>>  bind  <<------");
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.goBack();
    }

    public void unbind(View view) {
        System.out.println("------>>  unbind  <<------");

    }

    public void info(View view) {
        System.out.println("------>>  info  <<------");

    }

// private functions

    private void typeArrayTrail() {

    }

    private void loadUrl() {
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://10.0.2.2:8080/test1.asp");
    }


    private void loadData() {
        WebView myWebView = (WebView) findViewById(R.id.webview);

        String data = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<body>");
        stringBuilder.append("<a href='test.asp'>192中华</a>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
        data = stringBuilder.toString();

        myWebView.loadData(data, "text/html", "utf-8");

    }

    private void loadData64() {
        WebView myWebView = (WebView) findViewById(R.id.webview);

        String data = "PGh0bWw+PGJvZHk+WW91IHNjb3JlZCA8Yj4xOTIg5Lit5Zu9PC9iPiBwb2ludHMuPC9ib2R5PjwvaHRtbD4=";
        myWebView.loadData(data, "text/html;charset=GBK", "base64");

    }

    private void loadDataWithBaseURL() {
        WebView myWebView = (WebView) findViewById(R.id.webview);

        String data = "";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        stringBuilder.append("<body>");
        stringBuilder.append("<a href='test.asp'>192中国</a>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");

        String baseUrl = "example-app://example.co.uk/";
        data = stringBuilder.toString();
        String mimeType = "text/html";
        String encoding = "utf-8";
        String historyUrl = "test1.asp";

        myWebView.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, historyUrl);

    }

}