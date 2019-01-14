package mine.sensors;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2018/12/25.
 */
public class FetchAddressIntentService extends IntentService {

    public FetchAddressIntentService() {
        super("FetchAddressIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        System.out.println("~~onLocationResult~~");

        if (intent == null) {
            return;
        }
        String errorMessage = "";


        Location location = null;
//        Location location = intent.getParcelableExtra(
//                Constants.LOCATION_DATA_EXTRA);

        List<Address> addresses = null;



        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(),
                    1);



        } catch (IOException ioException) {
            System.out.println(ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            System.out.println("illegalArgumentException is " + illegalArgumentException +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " + location.getLongitude());
        }


    }
}
