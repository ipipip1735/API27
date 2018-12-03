package mine.connectivity;

import android.support.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;

public class TRequest<T> extends Request<T> {
    private final Listener<T> listener;

    public TRequest(int method, String url, Listener<T> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        System.out.println("--Request Constructor--");
        this.listener = listener;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        System.out.println("~~parseNetworkResponse~~");
        System.out.println("reposne is " + response);
        return (Response<T>) Response.success("ds", HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        System.out.println("~~deliverResponse~~");
        System.out.println("reposne is " + response);
    }
}
