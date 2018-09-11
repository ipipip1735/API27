package mine.webview;

import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2017/8/13.
 */

public class MyWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

        System.out.println("****** MyWebViewClient.shouldOverrideUrlLoading ******");
        System.out.println(view);
        System.out.println(request.getUrl());
        System.out.println(request.getMethod());
        System.out.println(request.getRequestHeaders());

        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
        super.onReceivedError(view, request, error);
        System.out.println("****** MyWebViewClient.onReceivedError ******");
        System.out.println(view);
        System.out.println(request);
        System.out.println(error);

    }
}
