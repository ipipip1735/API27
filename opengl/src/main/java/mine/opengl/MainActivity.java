package mine.opengl;

import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.pm.ConfigurationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    private BaseGLSurfaceView baseGLSurfaceView;
    private MyGLSurfaceView myGLSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_opengl);

        baseGLSurfaceView = new BaseGLSurfaceView(this);
        setContentView(baseGLSurfaceView);

//        setContentView(R.layout.activity_alert_dialog);
//
//        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.linearLayout3);
//        MyGLSurfaceView myGLSurfaceView = new MyGLSurfaceView(this);
//        viewGroup.addView(myGLSurfaceView);



//        FrameLayout frameLayout = findViewById(R.id.fl);
//        frameLayout.addView(baseGLSurfaceView);
//        frameLayout.addView(myGLSurfaceView);


//        boolean deviceSupportsAEP = getPackageManager().hasSystemFeature
//                (PackageManager.FEATURE_OPENGLES_EXTENSION_PACK);
//        System.out.println(deviceSupportsAEP);
//        String version = gl.glGetString(GL10.GL_VERSION);


    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  **********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestoreInstanceState  **********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestart  **********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  " + getClass().getSimpleName() + ".onResume  **********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  " + getClass().getSimpleName() + ".onPause  **********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  " + getClass().getSimpleName() + ".onBackPressed  **********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStop  **********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onSaveInstanceState  **********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  " + getClass().getSimpleName() + ".onDestroy  **********");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");



        baseGLSurfaceView.requestRender();




//        ThreeEGLContext eglContext = new ThreeEGLContext(3.0);
//        baseGLSurfaceView.setEGLContextFactory(eglContext);


//        checkVersion();


    }

    private void checkVersion() {

        final ActivityManager activityManager =
                (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo =
                activityManager.getDeviceConfigurationInfo();
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
        System.out.println("supportsEs2 is " + supportsEs2);

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");


    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

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
}
