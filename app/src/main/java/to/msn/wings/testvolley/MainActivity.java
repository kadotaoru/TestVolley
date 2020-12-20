package to.msn.wings.testvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Spot> spots;
    private Spot spot;
    String url = "http://babymap-api.mamaro.jp/api/places/search?lat=35.451152&lon=139.638741";


//    public Spot parseSpot(JSONObject job) {
//        spot = new Spot();
//        spot.setPlaceid(job.optString("id"));
//        spot.setRemark(job.optString("remarks"));
//        spot.setName(job.optString("name"));
//        spot.setLat(job.optDouble("lat"));
//        spot.setLon(job.optDouble("lon"));
//        spot.setFloor(job.optString("floor"));
//        spot.setAddress(job.optString("address"));
//        spot.setTel(job.optString("tel"));
//        spot.setUrl(job.optString("url"));
//        spot.setCreated(job.optString("cre"));
//        spot.setUrl(job.optString("url"));
//        spot.setUsableWeek(job.optString("usable_week_day"));
//        spot.setUsableTime(job.optString("usable_time"));
//        spot.setStar(job.optInt("star"));
//        spot.setReviewCount(job.optInt("review_count"));
//        spot.setIsBusy(job.optInt("is_busy"));
//        spot.setCreated(job.optString("created"));
//        spot.setCategoryId(job.optInt("place_category_id"));
//        spot.setCategoryId(job.optInt("place_category_id"));
//        return spot;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonParse = findViewById(R.id.btnParse);
        final TextView textView = findViewById(R.id.textView);

        jsonParse();
//        TextView textView = findViewById(R.id.textView);
//        RequestQueue queue = Volley.newRequestQueue(this);

//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        textView.setText("Response: " + response.toString());
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        // TODO: Handle error
//                        textView.setText("Response: " + error.toString());
//                    }
//                });
//
//        // Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


    }
    private void jsonParse() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    spots = new ArrayList<>();
                    JSONArray placesArray = response.getJSONArray("places");
                    for (int i = 0; i < placesArray.length(); i++) {
                        JSONObject places = placesArray.getJSONObject(i).getJSONObject("Place");
                        String lat = places.getString("lat");
                        String lon = places.getString("lon");
                        Log.d("緯度：" +lat, "経度：" + lon);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
            queue.add(request);
        }

        public interface SpotsCallback {
            void onSuccess(ArrayList<Spot> result);
        }

}