package mine.connectivity;

import android.support.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CustomRequest extends Request<String> {
    private final Listener<String> listener;

    public CustomRequest(int method, String url,
                         Listener<String> listener, @Nullable Response.ErrorListener errorListener) {

        super(method, url, errorListener);
        System.out.println("--CustomRequest Constructor--");
        this.listener = listener;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        System.out.println("~~parseNetworkResponse~~");
        System.out.println("allHeaders is " + response.statusCode);
        System.out.println("allHeaders is " + response.allHeaders);
        System.out.println("allHeaders is " + response.headers);
        System.out.println("allHeaders is " + response.notModified);
        System.out.println("allHeaders is " + response.data);

        Charset charset = Charset.forName(HttpHeaderParser.parseCharset(response.headers, "utf8"));
        System.out.println("charset is " + charset.name());

        String result = new String(response.data, charset);
        System.out.println("result is " + result);

        return Response.success(result, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        System.out.println("~~deliverResponse~~");
        System.out.println("reposne is " + response);
        listener.onResponse(response);
    }
}
