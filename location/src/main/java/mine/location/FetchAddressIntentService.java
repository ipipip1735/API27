package mine.location;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;

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
        System.out.println("~~onHandleIntent~~");

        if (intent == null) {
            return;
        }


        Location location = intent.getParcelableExtra("Location"); //获取Intent中的Location对象
        System.out.println(location);

        ResultReceiver resultReceiver = intent.getParcelableExtra("ResultReceiver");
        System.out.println("ResultReceiver is " + resultReceiver);


        //解析Location对象，将其转换为Address对象
        try {

            Geocoder geocoder = new Geocoder(this, Locale.getDefault()); //实例化Geocoder
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(),
                    1);

            Address address = addresses.get(0);
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                System.out.println(i + " is " + address.getAddressLine(i));
            }

            System.out.println("getLatitude is " + address.getLatitude());
            System.out.println("getLongitude is " + address.getLongitude());

            System.out.println("getAdminArea is " + address.getAdminArea());
            System.out.println("getCountryCode is " + address.getCountryCode());
            System.out.println("getCountryName is " + address.getCountryName());
            System.out.println("getFeatureName is " + address.getFeatureName());
            System.out.println("getLocale is " + address.getLocale());
            System.out.println("getLocality is " + address.getLocality());
            System.out.println("getPhone is " + address.getPhone());
            System.out.println("getPostalCode is " + address.getPostalCode());
            System.out.println("getPremises is " + address.getPremises());
            System.out.println("getSubAdminArea is " + address.getSubAdminArea());
            System.out.println("getSubLocality is " + address.getSubLocality());
            System.out.println("getSubThoroughfare is " + address.getSubThoroughfare());
            System.out.println("getThoroughfare is " + address.getThoroughfare());

            Bundle bundle = new Bundle();
            bundle.putParcelable("Address", addresses.get(0));
            resultReceiver.send(25, bundle);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
