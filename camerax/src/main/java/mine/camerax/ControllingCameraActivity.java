package mine.camerax;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class ControllingCameraActivity extends AppCompatActivity {
    Camera camera;
    SurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);

        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            System.out.println("Camera is supported");
            camera = Camera.open();
//            paremeters(); //打印摄像头功能


        } else {
            System.out.println("Camera isn't supported");
            return;
        }


        surfaceView = new SurfaceView(this);

        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(surfaceView);

//        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
//        System.out.println("h is " + params.height);
//        System.out.println("w is " + params.width);
//        params.height = params.height /2 ;
//        surfaceView.setLayoutParams(params);

        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceCreated  ~~~~~~~");
                System.out.println("holder is " + holder);

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int frmt, int w, int h) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceChanged  ~~~~~~~");

                System.out.println("holder is " + holder);
                System.out.println("frmt is " + frmt);
                System.out.println("h is " + h);
                System.out.println("w is " + w);

//                ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
//                params.height = 700;
//                surfaceView.setLayoutParams(params);




            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceDestroyed  ~~~~~~~");
                System.out.println("holder is " + holder);

            }
        });


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


    public void start(View view) {
        System.out.println("~~button.start~~");



        preview();


    }

    private void preview() {



        try {


            camera.setPreviewDisplay(surfaceView.getHolder());

            camera.startPreview();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void paremeters() {

        System.out.println("camera is " + camera);
        Camera.Parameters parameters = camera.getParameters();
//            System.out.println("get is " + parameters.get(String key));
        System.out.println("getAntibanding is " + parameters.getAntibanding());
        System.out.println("getAutoExposureLock is " + parameters.getAutoExposureLock());
        System.out.println("getAutoWhiteBalanceLock is " + parameters.getAutoWhiteBalanceLock());
        System.out.println("getColorEffect is " + parameters.getColorEffect());
        System.out.println("getExposureCompensation is " + parameters.getExposureCompensation());
        System.out.println("getExposureCompensationStep is " + parameters.getExposureCompensationStep());
        System.out.println("getFlashMode is " + parameters.getFlashMode());
        System.out.println("getFocalLength is " + parameters.getFocalLength());
        System.out.println("getFocusAreas is " + parameters.getFocusAreas());
//            System.out.println("getFocusDistances is " + parameters.getFocusDistances(float[] output));
        System.out.println("getFocusMode is " + parameters.getFocusMode());
        System.out.println("getHorizontalViewAngle is " + parameters.getHorizontalViewAngle());
//            System.out.println("getInt is " + parameters.getInt(String key));
        System.out.println("getJpegQuality is " + parameters.getJpegQuality());
        System.out.println("getJpegThumbnailQuality is " + parameters.getJpegThumbnailQuality());
        System.out.println("getJpegThumbnailSize is " + parameters.getJpegThumbnailSize());
        System.out.println("getMaxExposureCompensation is " + parameters.getMaxExposureCompensation());
        System.out.println("getMaxNumDetectedFaces is " + parameters.getMaxNumDetectedFaces());
        System.out.println("getMaxNumFocusAreas is " + parameters.getMaxNumFocusAreas());
        System.out.println("getMaxNumMeteringAreas is " + parameters.getMaxNumMeteringAreas());
        System.out.println("getMaxZoom is " + parameters.getMaxZoom());
        System.out.println("getMeteringAreas is " + parameters.getMeteringAreas());
        System.out.println("getMinExposureCompensation is " + parameters.getMinExposureCompensation());
        System.out.println("getPictureFormat is " + parameters.getPictureFormat());
        System.out.println("getPictureSize is " + parameters.getPictureSize());
        System.out.println("getPreferredPreviewSizeForVideo is " + parameters.getPreferredPreviewSizeForVideo());
        System.out.println("getPreviewFormat is " + parameters.getPreviewFormat());
//            System.out.println("getPreviewFpsRange is " + parameters.getPreviewFpsRange(int[] range));
        System.out.println("getPreviewFrameRate is " + parameters.getPreviewFrameRate());
        System.out.println("getPreviewSize is " + parameters.getPreviewSize());
        System.out.println("getSceneMode is " + parameters.getSceneMode());
        System.out.println("getSupportedAntibanding is " + parameters.getSupportedAntibanding());
        System.out.println("getSupportedColorEffects is " + parameters.getSupportedColorEffects());
        System.out.println("getSupportedFlashModes is " + parameters.getSupportedFlashModes());
        System.out.println("getSupportedFocusModes is " + parameters.getSupportedFocusModes());
        System.out.println("getSupportedJpegThumbnailSizes is " + parameters.getSupportedJpegThumbnailSizes());
        System.out.println("getSupportedPictureFormats is " + parameters.getSupportedPictureFormats());
        System.out.println("getSupportedPictureSizes is " + parameters.getSupportedPictureSizes());
        System.out.println("getSupportedPreviewFormats is " + parameters.getSupportedPreviewFormats());
        System.out.println("getSupportedPreviewFpsRange is " + parameters.getSupportedPreviewFpsRange());
        System.out.println("getSupportedPreviewFrameRates is " + parameters.getSupportedPreviewFrameRates());
        System.out.println("getSupportedPreviewSizes is " + parameters.getSupportedPreviewSizes());
        System.out.println("getSupportedSceneModes is " + parameters.getSupportedSceneModes());
        System.out.println("getSupportedVideoSizes is " + parameters.getSupportedVideoSizes());
        System.out.println("getSupportedWhiteBalance is " + parameters.getSupportedWhiteBalance());
        System.out.println("getVerticalViewAngle is " + parameters.getVerticalViewAngle());
        System.out.println("getVideoStabilization is " + parameters.getVideoStabilization());
        System.out.println("getWhiteBalance is " + parameters.getWhiteBalance());
        System.out.println("getZoom is " + parameters.getZoom());
        System.out.println("getZoomRatios is " + parameters.getZoomRatios());
    }


    public void save(View view) {
        System.out.println("~~button.save~~");


    }

    public void gallery(View view) {
        System.out.println("~~button.gallery~~");


    }

    public void video(View view) {
        System.out.println("~~button.video~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");
//        Camera.Parameters parameters = camera.getParameters();
//        parameters.setPreviewSize(700, 700);
//        camera.setParameters(parameters);


        List<Camera.Size> localSizes = camera.getParameters().getSupportedPreviewSizes();

        for (Camera.Size size : localSizes) {
            System.out.println(size.width);
            System.out.println(size.height);
        }


    }


    public void query(View view) {
        System.out.println("~~button.query~~");


        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
        System.out.println(params.height);
        System.out.println(params.width);





    }
}
