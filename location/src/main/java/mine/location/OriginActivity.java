package mine.location;

import android.Manifest;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

/**
 * Created by Administrator on 2020/05/24.
 */
public class OriginActivity extends AppCompatActivity {
    TextView textView1;
    TextView textView2;
    TextView textView3;
    LocationListener locationListener;
    GnssStatus.Callback callback;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_origin);

        textView1 = findViewById(R.id.textView);
        textView1.setText(this.getClass().getName());
        textView2 = findViewById(R.id.textView2);
        textView2.setText("");
        textView3 = findViewById(R.id.textView3);
        textView3.setText("Address");
    }

    @Override
    protected void onResume() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onResume  *********");
        super.onResume();

//        if(listener != null) getSystemService(LocationManager.class).removeUpdates(listener);

    }

    @Override
    protected void onPause() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        super.onPause();
        if (locationListener != null)
            getSystemService(LocationManager.class).removeUpdates(locationListener);
        if (callback != null)
            getSystemService(LocationManager.class).unregisterGnssStatusCallback(callback);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.out.println("~~onRequestPermissionsResult~~");
        System.out.println("requestCode is " + requestCode);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        for (int i = 0; i < permissions.length; i++) {

            if (grantResults[i] == PERMISSION_GRANTED) {
                System.out.println(permissions[i] + " is OK");
            } else {
                System.out.println(permissions[i] + " is Refused");
            }
        }
    }

    public void start(View view) {
        System.out.println("~~button.start~~");


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PERMISSION_GRANTED) {
            System.out.println("have no permission ACCESS_FINE_LOCATION ");

            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                System.out.println("show permission " + Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                System.out.println("show permission " + Manifest.permission.ACCESS_COARSE_LOCATION);
            }

            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
            return;
        }


        LocationManager locationManager = getSystemService(LocationManager.class);

        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            textView1.setText("GPS is disable!");
            return;
        }

        textView1.setText("GPS is enable!");

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                System.out.println("~~onLocationChanged~~");
                System.out.println("location is " + location);
                OriginActivity.this.location = location;

                StringBuilder sb = new StringBuilder();
                sb.append("getLongitude is " + location.getLongitude() + "\n") //精度
                        .append("getLatitude is " + location.getLatitude() + "\n") //纬度
                        .append("getAltitude is " + location.getAltitude() + "\n") //高度
                        .append("getSpeed is " + location.getSpeed() + "\n") //速度
                        .append("getBearing is " + location.getBearing() + "\n") //方向
                        .append("getAccuracy is " + location.getAccuracy() + "\n"); //定位精度

                textView1.setText(sb.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                textView1.setText(new StringBuilder()
                        .append("~~onStatusChanged~~" + "\n")
                        .append("provider is " + provider + "\n")
                        .append("status is " + status + "\n")
                        .append("extras is " + extras + "\n")
                        .toString());
            }

            @Override
            public void onProviderEnabled(String provider) {
                textView1.setText(new StringBuilder()
                        .append("~~onProviderEnabled~~" + "\n")
                        .append("provider is " + provider + "\n")
                        .toString());
            }

            @Override
            public void onProviderDisabled(String provider) {
                textView1.setText(new StringBuilder()
                        .append("~~onProviderDisabled~~" + "\n")
                        .toString());
            }
        };
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 0f, locationListener);


        GnssStatus.Callback callback = new GnssStatus.Callback() {
            @Override
            public void onStarted() {
                super.onStarted();
                textView2.setText(new StringBuilder()
                        .append("~~onStarted~~" + "\n")
                        .toString());
            }

            @Override
            public void onStopped() {
                super.onStopped();
                textView2.setText(new StringBuilder()
                        .append("~~onStopped~~" + "\n")
                        .toString());
            }

            @Override
            public void onFirstFix(int ttffMillis) {
                super.onFirstFix(ttffMillis);
                textView2.setText(new StringBuilder()
                        .append("~~onFirstFix~~" + "\n")
                        .append("ttffMillis is " + ttffMillis + "\n")
                        .toString());
            }

            @Override
            public void onSatelliteStatusChanged(GnssStatus status) {
                super.onSatelliteStatusChanged(status);
                StringBuilder stringBuffer = new StringBuilder();
                stringBuffer.append("~~onSatelliteStatusChanged~~" + "\n")
                        .append("getSatelliteCount is " + status.getSatelliteCount() + "\n");

                for (int i = 0; i < status.getSatelliteCount(); i++) {
                    System.out.println("getAzimuthDegrees is " + status.getAzimuthDegrees(i));
//                    System.out.println("getCarrierFrequencyHz is " + status.getCarrierFrequencyHz(i));
                    System.out.println("getCn0DbHz is " + status.getCn0DbHz(i));
                    System.out.println("getElevationDegrees is " + status.getElevationDegrees(i));
                    System.out.println("getSvid is " + status.getSvid(i));
                    System.out.println("getConstellationType is " + status.getConstellationType(i));

                    stringBuffer.append("getAzimuthDegrees(" + i + ") is " + status.getAzimuthDegrees(i) + "\n")
//                            .append("getCarrierFrequencyHz(" + i + ") is " + status.getCarrierFrequencyHz(i) + "\n")
                            .append("getCn0DbHz(" + i + ") is " + status.getCn0DbHz(i) + "\n")
                            .append("getElevationDegrees(" + i + ") is " + status.getElevationDegrees(i) + "\n")
                            .append("getSvid(" + i + ") is " + status.getSvid(i) + "\n")
                            .append("getConstellationType(" + i + ") is " + status.getConstellationType(i) + "\n");
                }
                textView2.setText(stringBuffer.toString());


            }
        };
        boolean b1 = locationManager.registerGnssStatusCallback(callback);
        textView2.setText("registerGnssStatusCallback is " + b1);


    }

    public void address(View view) {
        textView3.setText("~~button.address~~");

        if (location == null) return;

        try {
            Geocoder gc = new Geocoder(this);
            List<Address> addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            StringBuilder stringBuffer = new StringBuilder();
            for (int i = 0; i < addresses.get(0).getMaxAddressLineIndex(); i++) {
                System.out.println(i + " is " + addresses.get(0).getAddressLine(i) );
                stringBuffer.append(i + " is " + addresses.get(0).getAddressLine(i) + "\n");
            }
            textView3.setText(stringBuffer.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void check(View view) {
        System.out.println("~~button.check~~");
        LocationManager locationManager = getSystemService(LocationManager.class);
        for (String p : locationManager.getAllProviders()) {
            System.out.println(p);
        }

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);//设置过滤条件
        String provider = locationManager.getBestProvider(criteria, false);
        System.out.println(provider);

    }

    public void resume(View view) {
        System.out.println("~~button.resume~~");
    }

    public void status(View view) {
        System.out.println("~~button.status~~");
    }

}
