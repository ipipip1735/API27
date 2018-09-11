package mine.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Set;


public class MainActivity extends AppCompatActivity {
    static final int REQUEST_ENABLE_BT = 1;
//    ArrayAdapter mArrayAdapter = new ArrayAdapter(this, )

    private final BroadcastReceiver mReceiverFound = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            System.out.println("**********  BRFound.onReceive  ***********");

            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
//                mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                System.out.println("name is " + device.getName());
                System.out.println("address is " + device.getAddress());
            }
        }
    };


    private final BroadcastReceiver mReceiverStarted = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            System.out.println("**********  BRStarted.onReceive  ***********");
        }
    };

    private final BroadcastReceiver mReceiverFinished = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("**********  BRFinished.onReceive  ***********");

        }
    };



    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example.onCreate  ***********");

        setContentView(R.layout.activity_oldmain);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("**********  Example.onNewIntent  ***********");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("**********  Example.onStart  ***********");
        super.onStart();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("**********  Example.onRestoreInstanceState  ***********");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        System.out.println("**********  Example.onRestart  ***********");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("**********  Example.onResume  ***********");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("**********  Example.onPause  ***********");
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("**********  Example.onBackPressed  ***********");
        super.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("**********  Example.onActivityResult  ***********");
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode is " + requestCode);
        System.out.println("resultCode is " + resultCode);
        System.out.println("data is " + data);
    }

    @Override
    protected void onStop() {
        System.out.println("**********  Example.onStop  ***********");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("**********  Example.onSaveInstanceState  ***********");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("**********  Example.onDestroy  ***********");
        super.onDestroy();
    }


// button event functions

    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    public void init(View view) {

        System.out.println("------>>  init  <<------");

        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            System.out.println("Device does not support Bluetooth");
        }

        if (!mBluetoothAdapter.isEnabled()) {
            System.out.println("Would you like enable your bluetooth?");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }


    }

    public void start(View view) {
        System.out.println("------>>  start  <<------");
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pairedDevices) {
                // Add the name and address to an array adapter to show in a ListView
//                    mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                System.out.println("name is " + device.getName());
                System.out.println("address is " + device.getAddress());
            }
        }

    }

    public void restart(View view) {
        System.out.println("------>>  restart  <<------");
        if(mBluetoothAdapter.startDiscovery()) {
            System.out.println("scaning blue tooth is true");
        }

    }

    public void stop(View view) {
        System.out.println("------>>  stop  <<------");

    }


    public void bind(View view) {
        System.out.println("------>>  bind  <<------");

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiverFound, filter); // Don't forget to unregister during onDestroy


        IntentFilter filterStarted = new IntentFilter(mBluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(mReceiverStarted, filterStarted); // Don't forget to unregister during onDestroy


        IntentFilter filterFinished = new IntentFilter(mBluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiverStarted, filterFinished); // Don't forget to unregister during onDestroy


    }

    public void unbind(View view) {
        System.out.println("------>>  unbind  <<------");
        unregisterReceiver(mReceiverFound);
        unregisterReceiver(mReceiverFinished);
        unregisterReceiver(mReceiverStarted);

    }

    public void info(View view) {
        System.out.println("------>>  info  <<------");

    }

// private functions

    private void typeArrayTrail() {

    }

}

