package mine.sensors;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.google.android.gms.location.Geofence.NEVER_EXPIRE;

/**
 * Created by Administrator on 2019/1/17.
 */
public class GeofenceActivity extends AppCompatActivity {

    private GeofencingClient mGeofencingClient;
    private PendingIntent mGeofencePendingIntent;
    private List<String> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
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

    public void check(View view) {
        System.out.println("~~button.check~~");

    }

    public void start(View view) {
        System.out.println("~~button.start~~");

        if (Objects.isNull(mGeofencingClient))
            mGeofencingClient = LocationServices.getGeofencingClient(this);

        //创建Geofence对象
        Geofence.Builder builder = new Geofence.Builder();
        double latitude = 30.53723959615318D;
        double longitude = 114.30860318934754D;
        Geofence geofence = builder.setRequestId("r1")
                .setCircularRegion(latitude, longitude, 100F)
                .setExpirationDuration(NEVER_EXPIRE)
                .setLoiteringDelay(5000)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT | Geofence.GEOFENCE_TRANSITION_DWELL)
                .setNotificationResponsiveness(1000)
                .build();
        ids.add(geofence.getRequestId());

        //放入容器
        List<Geofence> geofenceList = Arrays.asList(geofence);


        //创建GeofencingRequest
        GeofencingRequest.Builder requestBuilder = new GeofencingRequest.Builder();
        requestBuilder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_DWELL);
//        requestBuilder.addGeofences(geofenceList); //增加多个Geofence
        requestBuilder.addGeofence(geofence); //增加单个Geofence
        GeofencingRequest geofencingRequest = requestBuilder.build();


        //创建服务的PendingIntent
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        if (Objects.isNull(mGeofencePendingIntent))
            mGeofencePendingIntent = PendingIntent.getService(
                    this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        //创建任务
        Task<Void> task = mGeofencingClient.addGeofences(geofencingRequest, mGeofencePendingIntent);
        task.addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                System.out.println("~~addGeofences.onSuccess~~");
            }
        });

        task.addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("~~addGeofences.onFailure~~");
                System.out.println(e);
            }
        });


    }

    public void setting(View view) {
        System.out.println("~~button.setting~~");

    }

    public void stop(View view) {
        System.out.println("~~button.stop~~");

        if (Objects.nonNull(mGeofencingClient) && Objects.nonNull(mGeofencePendingIntent)) {
            mGeofencingClient.removeGeofences(mGeofencePendingIntent)
                    .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            System.out.println("~~removeGeofences.onSuccess~~");
                        }
                    })
                    .addOnFailureListener(this, new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("~~removeGeofences.onFailure~~");
                        }
                    });
        }

        if (!ids.isEmpty()) {
            //清空所有Geofence
//            mGeofencingClient.removeGeofences(ids);
//            ids.clear();

            //清空单个Geofence
            mGeofencingClient.removeGeofences(Arrays.asList(ids.remove(0)));
        }


    }

    public void request(View view) {
        System.out.println("~~button.request~~");

    }

    public void query(View view) {
        System.out.println("~~button.query~~");

    }


}
