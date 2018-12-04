package mine.connectivity;

import android.support.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class TRequest extends Request<String> {
    private final Listener<String> listener;

    public TRequest(int method, String url, Listener<String> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        System.out.println("--Request Constructor--");
        this.listener = listener;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        System.out.println("~~parseNetworkResponse~~");
        System.out.println("reposne is " + response);




        return Response.success("ok", HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        System.out.println("~~deliverResponse~~");
        System.out.println("reposne is " + response);
//        listener.onResponse(response);
    }
}
