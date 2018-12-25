package mine.sensors;

import android.content.IntentSender;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Created by Administrator on 2018/12/24.
 */
public class MainActivity extends AppCompatActivity {
    TextView textView;
    FusedLocationProviderClient mFusedLocationClient;
    LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);
        ViewGroup group = findViewById(R.id.fl);
        textView = new TextView(this);
        group.addView(textView);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationAvailability(LocationAvailability locationAvailability) {
                super.onLocationAvailability(locationAvailability);
                System.out.println("~onLocationAvailability~");
                System.out.println(locationAvailability);
            }

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                System.out.println("~onLocationResult~");
                if (locationResult == null) {
                    System.out.println("null");
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    System.out.println("location is " + location);
                    textView.setText("location is " + location);
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
        mFusedLocationClient.removeLocationUpdates(mLocationCallback);
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
        System.out.println("~~button.start~~");

        lastLocation();


    }

    private void lastLocation() {
        if (Objects.isNull(mFusedLocationClient))
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        Task task = mFusedLocationClient.getLastLocation();

        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                System.out.println("~~onSuccess~~");
                System.out.println("location is " + location);

                if (location != null) {
                    textView.setText("location is " + location);
                }
            }
        });
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        setting();

    }

    private void setting() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(500);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
        task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                System.out.println("~~onSuccess~~");
                System.out.println("locationSettingsResponse is " + locationSettingsResponse);
                System.out.println("status is " + locationSettingsResponse.getLocationSettingsStates());

                if (locationSettingsResponse != null) {
                    textView.setText("locationSettingsResponse is " + locationSettingsResponse);
                    textView.setText("status is " + locationSettingsResponse.getLocationSettingsStates());
                }
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("~~onFailure~~");
                if (e instanceof ResolvableApiException) {
                    try {
                        ResolvableApiException resolvable = (ResolvableApiException) e;
                        resolvable.startResolutionForResult(MainActivity.this, 11);
                    } catch (IntentSender.SendIntentException sendEx) {
                        // Ignore the error.
                    }
                }


            }
        });


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

        if (Objects.isNull(mFusedLocationClient))
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, null);
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

    private void getMethods(Class<?> c) {
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            System.out.print(method.getName());
        }
    }


}
