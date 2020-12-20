package to.msn.wings.testvolley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MyApp {
    public static final  String URL = "http://babymap-api.mamaro.jp/api/";
    private static MyApp mInstance;
    public static final String TAG = MyApp.class.getSimpleName();
    private RequestQueue mRequestQueue;
    Context mBase;

    public static synchronized MyApp getInstance() {
        return mInstance;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    private Context getApplicationContext() {
        return mBase.getApplicationContext();

    }
}
