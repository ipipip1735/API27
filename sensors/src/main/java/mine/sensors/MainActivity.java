package mine.sensors;

import android.content.IntentSender;
import android.location.Location;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.google.android.gms.common.ConnectionResult.SERVICE_DISABLED;
import static com.google.android.gms.common.ConnectionResult.SERVICE_INVALID;
import static com.google.android.gms.common.ConnectionResult.SERVICE_MISSING;
import static com.google.android.gms.common.ConnectionResult.SERVICE_UPDATING;
import static com.google.android.gms.common.ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED;
import static com.google.android.gms.common.ConnectionResult.SUCCESS;

/**
 * Created by Administrator on 2018/12/24.
 */
public class MainActivity extends AppCompatActivity {
    TextView infoTV, statusTV;
    FusedLocationProviderClient mFusedLocationClient;
    LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);
        ViewGroup group = findViewById(R.id.ll);
        infoTV = new TextView(this);
        group.addView(infoTV);
        statusTV = new TextView(this);
        group.addView(statusTV);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
                System.out.println("~onLocationAvailability~");
                System.out.println(locationAvailability.isLocationAvailable());
            }

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                System.out.println("~onLocationResult~");
                if (locationResult == null) {
                    System.out.println("null");
                    statusTV.setText("null");
                    return;
                }
                System.out.println("getLastLocation is " + locationResult.getLastLocation());
                for (Location location : locationResult.getLocations()) {
                    System.out.println("location is " + location);
                    infoTV.setText("location is " + location);

                    System.out.println(interval(SystemClock.elapsedRealtimeNanos() - location.getElapsedRealtimeNanos()));
                    statusTV.setText(interval(SystemClock.elapsedRealtimeNanos() - location.getElapsedRealtimeNanos()));

                }

            }
        };

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

        if (Objects.isNull(mFusedLocationClient)) return;
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
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


    public void check(View view) {
        System.out.println("~~button.check~~");

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        statusTV.setText("");
        switch (googleApiAvailability.isGooglePlayServicesAvailable(this)) {
            case SUCCESS:
                System.out.println("SUCCESS");
                infoTV.setText("SUCCESS");
                break;
            case SERVICE_MISSING:
                System.out.println("SERVICE_MISSING");
                infoTV.setText("SERVICE_MISSING");
                break;
            case SERVICE_UPDATING:
                System.out.println("SERVICE_UPDATING");
                infoTV.setText("SERVICE_UPDATING");
                break;
            case SERVICE_VERSION_UPDATE_REQUIRED:
                System.out.println("SERVICE_VERSION_U");
                infoTV.setText("SERVICE_VERSION_U");
                break;
            case SERVICE_DISABLED:
                System.out.println("SERVICE_DISABLED");
                infoTV.setText("SERVICE_DISABLED");
                break;
            case SERVICE_INVALID:
                System.out.println("SERVICE_INVALID");
                infoTV.setText("SERVICE_INVALID");
                break;
            default:
                System.out.println("default");
                infoTV.setText("default");
        }

    }


    public void start(View view) {
        System.out.println("~~button.start~~");
        lastLocation();
        nowTime();
    }

    private void lastLocation() {
        if (Objects.isNull(mFusedLocationClient))
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Task task = mFusedLocationClient.getLastLocation();

        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                System.out.println("~~onSuccess~~");
                if (Objects.isNull(location)) {
                    System.out.println("location is nulll");
                    return;
                }
                System.out.println("location is " + location);
                infoTV.setText(location.toString());
                System.out.println(interval(SystemClock.elapsedRealtimeNanos() - location.getElapsedRealtimeNanos()));
                statusTV.setText(interval(SystemClock.elapsedRealtimeNanos() - location.getElapsedRealtimeNanos()));

            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("Error|" + e);
            }
        });
    }

    public void setting(View view) {
        System.out.println("~~button.setting~~");
        setting();
    }

    private void setting() {
        //创建请求对象
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(1500);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        //创建配置构建器
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(locationRequest);

        //创建配置对象
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        //核查配置对象
        Task<LocationSettingsResponse> task = settingsClient.checkLocationSettings(locationSettingsRequest);


        //增加成功监听器
        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                System.out.println("~~onSuccess~~");
                System.out.println("locationSettingsResponse is " + locationSettingsResponse);

                LocationSettingsStates states = locationSettingsResponse.getLocationSettingsStates();
                System.out.println("isBlePresent is " + states.isBlePresent());
                System.out.println("isBleUsable is " + states.isBleUsable());
                System.out.println("isGpsPresent is " + states.isGpsPresent());
                System.out.println("isGpsUsable is " + states.isGpsUsable());
                System.out.println("isLocationPresent is " + states.isLocationPresent());
                System.out.println("isLocationUsable is " + states.isLocationUsable());
                System.out.println("isNetworkLocationPresent is " + states.isNetworkLocationPresent());
                System.out.println("isNetworkLocationUsable is " + states.isNetworkLocationUsable());

                if (locationSettingsResponse != null) {
                    infoTV.setText("status is ok!");
                }
            }
        });

        //增加失败监听器
        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("~~onFailure~~");
                System.out.println("Error|" + e);
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(MainActivity.this, 11);
                    } catch (IntentSender.SendIntentException sendEx) {
                        System.out.println("sendEx is " + sendEx);
                    }
                }
            }
        });
    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");
        if (Objects.isNull(mFusedLocationClient)) {
            System.out.println("again can't stop, it has stopped");
            infoTV.setText("");
            statusTV.setText("again can't stop, it has stopped");
            return;
        }

        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
    }

    public void request(View view) {
        System.out.println("~~button.request~~");

        if (Objects.isNull(mFusedLocationClient))
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(1500);
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, null);


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

    private void nowTime() {

        TimeZone tz = TimeZone.getTimeZone("GMT+8");
//        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");

        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);

        SimpleDateFormat sdf = new SimpleDateFormat("Y/M/d H:m:s z");
        sdf.setTimeZone(tz);
        String s = sdf.format(date);
        System.out.println("nowTime|" + s);

    }

    private String interval(long millis) {
        if (millis < 0) {
            return "Error|millis is " + millis;
        }

        long hours, minutes, seconds;
        hours = TimeUnit.NANOSECONDS.toHours(millis);
        minutes = TimeUnit.NANOSECONDS.toMinutes(millis) % 60;
        seconds = TimeUnit.NANOSECONDS.toSeconds(millis) % 60;

        return hours + ":" + minutes + ":" + seconds;
    }

}
