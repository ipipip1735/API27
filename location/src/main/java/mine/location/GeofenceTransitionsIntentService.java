package mine.location;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by Administrator on 2018/12/25.
 */
public class GeofenceTransitionsIntentService extends IntentService {

    public GeofenceTransitionsIntentService() {
        super("GeofenceTransitionsIntentService");
        System.out.println("++ GeofenceTransitionsIntentService.Constructor ++");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("~~onHandleIntent~~");
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        if (geofencingEvent.hasError()) {
            System.out.println("error");
//            String errorMessage = GeofenceErrorMessages.getErrorString(this,
//                    geofencingEvent.getErrorCode());
        }

        int geofenceTransition = geofencingEvent.getGeofenceTransition();

        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
            List<Geofence> triggeringGeofences = geofencingEvent.getTriggeringGeofences();
            System.out.println(triggeringGeofences);

//            String geofenceTransitionDetails = getGeofenceTransitionDetails(
//                    this,
//                    geofenceTransition,
//                    triggeringGeofences
//            );

        }

    }
}
