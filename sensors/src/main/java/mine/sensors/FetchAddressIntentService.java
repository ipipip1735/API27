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
    ResultReceiver resultReceiver = new ResultReceiver(null){
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            System.out.println("~~ResultReceiver.onReceiveResult~~");
            super.onReceiveResult(resultCode, resultData);

        }
    };

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

        Geocoder geocoder = new Geocoder(this, Locale.getDefault()); //实例化Geocoder

        //解析Location对象，将其转换为Address对象
        try {
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(),
                    1);
            System.out.println("size is " + addresses.size());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
