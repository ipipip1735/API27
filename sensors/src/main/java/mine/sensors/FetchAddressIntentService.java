package mine.sensors;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;

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
        System.out.println("~~onHandleIntent~~");

        if (intent == null) {
            return;
        }


        Location location = intent.getParcelableExtra("Location"); //获取Intent中的Location对象
        System.out.println(location);

        ResultReceiver resultReceiver = intent.getParcelableExtra("ResultReceiver");
        System.out.println("ResultReceiver is " + resultReceiver);


        Geocoder geocoder = new Geocoder(this, Locale.getDefault()); //实例化Geocoder

        //解析Location对象，将其转换为Address对象
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(),
                    1);
            System.out.println("size is " + addresses.size());

            Bundle bundle = new Bundle();
            bundle.putParcelable("Address", addresses.get(0));
            resultReceiver.send(25, bundle);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
