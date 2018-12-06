package mine.connectivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2018/12/5.
 */
public class VolleyActivity extends AppCompatActivity {

    CookieManager cookieManager = null;
    ImageLoader.ImageCache cache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestart  *********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
    }

    public void start(View view) {
        System.out.println("~~button.volley~~");


//        volleyBasic();
//        volleyForJSON();
//        volleyWithCustomQuest();
//        volleyWithCookie();

        //加载图片
//        volleyForImage();
//        volleyImageLoader();
        volleyImageLoaderBasic();

        //自定义Cache接口和NetWork接口
//        volleyWithCacheAndNetwork();

    }


    private void volleyImageLoaderBasic() {

        final RequestQueue queue = Volley.newRequestQueue(this);

        if (Objects.isNull(cache)) cache = new ImageLoader.ImageCache() {
            final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                System.out.println("-->putBitmap");
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                System.out.println("<--getBitmap~~");
                return mCache.get(url);
            }
        };

        final ImageLoader imageLoader = new ImageLoader(queue, cache);

        final String url = "http://192.168.0.126:8008/a.jpg";
        ImageLoader.ImageContainer container = imageLoader.get(url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                System.out.println("~~onResponse~~");
                System.out.println("getBitmap is " + response.getBitmap());
                System.out.println("isCache is " + imageLoader.isCached(url, 0, 0));
                if (Objects.isNull(response.getBitmap())) {
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageResource(R.drawable.w2);
                } else {
                    ImageView imageView = findViewById(R.id.imageView);
                    imageView.setImageBitmap(response.getBitmap());
                    queue.stop();
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("~~onErrorResponse~~");
                System.out.println(error);
                queue.stop();
            }
        });

        container.cancelRequest();

    }

    private void volleyImageLoader() {
        System.out.println("...volleyImageLoader...");


        RequestQueue queue = Volley.newRequestQueue(this);


        ImageLoader.ImageCache cache = new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

            public void putBitmap(String url, Bitmap bitmap) {
                System.out.println("~~putBitmap~~");
                mCache.put(url, bitmap);
            }

            public Bitmap getBitmap(String url) {
                System.out.println("~~getBitmap~~");
                return mCache.get(url);
            }
        };

        ImageLoader imageLoader = new ImageLoader(queue, cache);

        NetworkImageView networkImageView = findViewById(R.id.networkImageView);
//        networkImageView.setImageUrl("http://192.168.0.126:8008/a.jpg", imageLoader);

        networkImageView.setDefaultImageResId(R.drawable.w2);
        networkImageView.setImageUrl("http://192.168.0.127/w1.jpg", imageLoader);

    }

    private void volleyForImage() {

        final RequestQueue queue = Volley.newRequestQueue(this);
        final ImageView imageView = findViewById(R.id.imageView);
        String url = "http://192.168.0.126:8008/a.jpg";
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                System.out.println("~~onResponse~~");
                imageView.setImageBitmap(response);
            }
        }, 200, 508,
                ImageView.ScaleType.CENTER,
                Bitmap.Config.ALPHA_8, null);

        queue.add(imageRequest);


    }

    private void volleyWithCookie() {

        final RequestQueue queue = Volley.newRequestQueue(this);

        if (Objects.isNull(cookieManager)) cookieManager = new CookieManager();
        CookieHandler.setDefault(cookieManager);

        String url = "http://192.168.0.126:8008/android.php";
        //实例化Request对象
        CustomRequest customRequest = new CustomRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("~~onResponse~~");
//                        System.out.println("response is " + response);
                        queue.stop();

                        int cookieIdx = 0;
                        CookieStore cookieStore = cookieManager.getCookieStore();
                        List<HttpCookie> cookies = cookieStore.getCookies();
                        for (HttpCookie ck : cookies) {
                            System.out.println("-- Cookie." + ++cookieIdx + " --");
                            System.out.println("Cookie name: " + ck.getName());
                            System.out.println("Domain: " + ck.getDomain());
                            System.out.println("Max age: " + ck.getMaxAge());
                            System.out.println("Server path: " + ck.getPath());
                            System.out.println("Is secured: " + ck.getSecure());
                            System.out.println("Cookie value: " + ck.getValue());
                            System.out.println("Cookie protocol version: " + ck.getVersion());
                            System.out.println("toString: " + ck);
                        }
                        System.out.println(cookies);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("~~onErrorResponse~~");
                        System.out.println("error is " + error);
                        queue.stop();
                    }
                });

        queue.add(customRequest);


        CookieStore cookieStore = cookieManager.getCookieStore();
        List<HttpCookie> cookies = cookieStore.getCookies();
    }

    private void volleyWithCustomQuest() {

        final RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.0.126:8008/android.php";
        //实例化Request对象
        CustomRequest customRequest = new CustomRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("~~onResponse~~");
                        System.out.println("response is " + response);
                        queue.stop();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("~~onErrorResponse~~");
                        System.out.println("error is " + error);
                        queue.stop();
                    }
                });

        queue.add(customRequest);


    }

    private void volleyForJSON() {

        final RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.0.126:8008/json.php";
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                System.out.println("response is " + response);
                                queue.stop();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println(error);
                        queue.stop();
                    }
                });

        queue.add(jsonObjectRequest);

    }


    private void volleyWithCacheAndNetwork() {

        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        Network network = new BasicNetwork(new HurlStack());


        RequestQueue queue = new RequestQueue(cache, network);
        queue.start();

//        String url = "http://192.168.0.126:8008/cookies.php";
//        String url = "http://192.168.0.126:8008/index.html";
        String url = "https://help.aliyun.com/knowledge_detail/57888.html";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("RThat didn't work!");
            }
        });

        queue.add(stringRequest);
    }

    private void volleyBasic() {
        final RequestQueue queue = Volley.newRequestQueue(this);


//        String url = "http://192.168.0.126:8008/cookies.php";
//        String url = "http://192.168.0.126:8008/index.html";
//        String url = "http://192.168.0.126:8008/index.html";
        String url = "http://192.168.0.127/index.html";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("...onResponse...");
                        System.out.println("Response is: " + response);
                        queue.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("...onErrorResponse...");
                System.out.println("didn't work!");
                queue.stop();
            }
        });

        queue.add(stringRequest);
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
}


class CustomRequest extends Request<String> {
    private final Response.Listener<String> listener;

    public CustomRequest(int method, String url,
                         Response.Listener<String> listener, @Nullable Response.ErrorListener errorListener) {

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


        long date = HttpHeaderParser.parseDateAsEpoch(response.headers.get("Date"));
        System.out.println("timestamp is " + date);


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