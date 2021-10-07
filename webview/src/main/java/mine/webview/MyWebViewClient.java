package mine.webview;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/8/13.
 */

public class MyWebViewClient extends WebViewClient {

    @Nullable
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        System.out.println("~~" + getClass().getSimpleName() + ".shouldInterceptRequest~~");
        System.out.println("view = " + view + ", request = " + request);

        System.out.println("request.getRequestHeaders() is " + request.getRequestHeaders());
        System.out.println("request.getMethod() is " + request.getMethod());
        System.out.println("request.getUrl() is " + request.getUrl());
        System.out.println("request.hasGesture() is " + request.hasGesture());

//        WebResourceResponse webResourceResponse = null;
//        if (request.getUrl().getEncodedPath().endsWith("mp.png")) {
//            System.out.println("go " + request.getUrl());
//
//            try {
//                InputStream inputStream = view.getContext().getAssets().open("tp.png");
//                System.out.println(inputStream.available());
//                webResourceResponse = new WebResourceResponse("image/png", "utf-8", inputStream);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } else {
//            webResourceResponse = super.shouldInterceptRequest(view, request);
//        }
//        System.out.println("webResourceResponse = " + webResourceResponse);
//        return webResourceResponse;

        return super.shouldInterceptRequest(view, request);
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        System.out.println("~~" + getClass().getSimpleName() + ".onLoadResource~~");
        System.out.println("view = " + view + ", url = " + url);
        super.onLoadResource(view, url);

    }

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
