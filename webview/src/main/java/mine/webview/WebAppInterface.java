package mine.webview;

import android.content.Context;
import android.webkit.JavascriptInterface;

/**
 * Created by Administrator on 2017/8/13.
 */

public class WebAppInterface {
    Context mContext;

    public WebAppInterface(Context mContext) {
        this.mContext = mContext;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        System.out.println("****** JS API *******");
        System.out.println(toast);
    }
}
