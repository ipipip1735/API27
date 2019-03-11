package mine.webview;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by Administrator on 2017/8/13.
 */

public class MyWebChromeClient extends WebChromeClient {

    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        System.out.println("******* MyWebChromeClient.onConsoleMessage *******");

        System.out.println(consoleMessage.lineNumber());
        System.out.println(consoleMessage.sourceId());
        System.out.println(consoleMessage.message());

        return true;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
//        System.out.println("******* MyWebChromeClient.onProgressChanged *******");
//        ToolClass.showThread();
//        System.out.println(tween);
//        System.out.println(newProgress);

    }

}
