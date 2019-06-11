package mine.camerax;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

public class ControllingCameraActivity extends AppCompatActivity {
    Camera camera;
    SurfaceView surfaceView;
    int orientation = 0;
    OrientationEventListener orientationEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_horizontal);


        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            System.out.println("Camera is supported");
            camera = Camera.open();//获取摄像头

        } else {
            System.out.println("Camera isn't supported");
            return;
        }


        //监听器方向改变
        orientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_UI) {
            @Override
            public void onOrientationChanged(int orientation) {
                System.out.println("~~onOrientationChanged~~");
                System.out.println("orientation is " + orientation);

            }
        };


        surfaceView = new SurfaceView(this);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceCreated  ~~~~~~~");
                System.out.println("holder is " + holder);

                try {
                    camera.setPreviewDisplay(surfaceView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int frmt, int w, int h) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceChanged  ~~~~~~~");

                System.out.println("holder is " + holder);
                System.out.println("frmt is " + frmt);
                System.out.println("h is " + h);
                System.out.println("w is " + w);

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceDestroyed  ~~~~~~~");
                System.out.println("holder is " + holder);

            }
        });

        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(surfaceView);

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
        orientationEventListener.enable();
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        orientationEventListener.disable();
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


    public void preview(View view) {
        System.out.println("~~button.preview~~");

        camera.startPreview();

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        camera.stopPreview();

    }

    public void config(View view) {
        System.out.println("~~button.config~~");

//        camera.setDisplayOrientation(90);

//        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
//        camera.getCameraInfo(0, cameraInfo);


        //设置拍摄角度
//        Camera.Parameters parameters = camera.getParameters();
//        System.out.println("orientation is " + (orientation += 90));
//        parameters.setRotation(orientation % 360);
//        camera.setParameters(parameters);


        //设置预览尺寸
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(320, 240);
        camera.setParameters(parameters);


    }

    public void video(View view) {
        System.out.println("~~button.video~~");

    }

    public void modify(View view) {
        System.out.println("~~button.modify~~");

        //方式一
        camera.setDisplayOrientation(90);






    }


    public void take(View view) {
        System.out.println("~~button.take~~");




    }


    public void query(View view) {
        System.out.println("~~button.query~~");


        //获取SurfaceView尺寸
        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
        System.out.println("SurfaceView height is " + params.height);
        System.out.println("SurfaceView width is " + params.width);


        //获取屏幕角度
        int degrees = 0;
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                System.out.println("rotation is Surface.ROTATION_0");
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                System.out.println("rotation is Surface.ROTATION_90");
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                System.out.println("rotation is Surface.ROTATION_180");
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                System.out.println("rotation is Surface.ROTATION_270");
                break;
        }



        //Camera
        paremeters();//参数
//        face();//面
//        size();//尺寸
//        area();//区域


    }

    private void area() {

    }

    private void size() {

    }

    public void info(View view) {


        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            StringBuilder stringBuilder = new StringBuilder(128);
            camera.getCameraInfo(i, cameraInfo);
            switch (cameraInfo.facing) {
                case CAMERA_FACING_FRONT: //前置摄像头
                    stringBuilder.append("cameraInfo|facing=CAMERA_FACING_BACK, ");
                    break;
                case CAMERA_FACING_BACK: //后置摄像头，即主摄像头
                    stringBuilder.append("cameraInfo|facing=CAMERA_FACING_FRONT, ");
                    break;
                default:
                    System.out.println("unknown!!");
                    return;
            }

            stringBuilder.append("orientation=" + cameraInfo.orientation + ", " +
                    "canDisableShutterSound=" + cameraInfo.canDisableShutterSound);
            System.out.println(stringBuilder);
        }
    }

    private void face() {

    }


    private void paremeters() {

        System.out.println("camera is " + camera);
        Camera.Parameters parameters = camera.getParameters();
//            System.out.println("get is " + parameters.get(String key));
//            System.out.println("getInt is " + parameters.getInt(String key));
        System.out.println("getAntibanding is " + parameters.getAntibanding());
        System.out.println("getAutoExposureLock is " + parameters.getAutoExposureLock());
        System.out.println("getAutoWhiteBalanceLock is " + parameters.getAutoWhiteBalanceLock());
        System.out.println("getColorEffect is " + parameters.getColorEffect());
        System.out.println("getExposureCompensation is " + parameters.getExposureCompensation());
        System.out.println("getExposureCompensationStep is " + parameters.getExposureCompensationStep());
        System.out.println("getFlashMode is " + parameters.getFlashMode());//闪光模式
        System.out.println("getFocalLength is " + parameters.getFocalLength());
        System.out.println("getFocusAreas is " + parameters.getFocusAreas());
//            System.out.println("getFocusDistances is " + parameters.getFocusDistances(float[] output));
        System.out.println("getFocusMode is " + parameters.getFocusMode());
        System.out.println("getHorizontalViewAngle is " + parameters.getHorizontalViewAngle());
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
        System.out.println("getPreviewSize is height=" + parameters.getPreviewSize().height + ", width=" + parameters.getPreviewSize().width);
        for (Camera.Size size : camera.getParameters().getSupportedPreviewSizes()) {
            System.out.println("getSupportedPreviewSizes is width=" + size.width + ", height=" + size.height);
        }


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



        System.out.println("getSupportedSceneModes is " + parameters.getSupportedSceneModes());
        System.out.println("getSupportedVideoSizes is " + parameters.getSupportedVideoSizes());
        System.out.println("getSupportedWhiteBalance is " + parameters.getSupportedWhiteBalance());
        System.out.println("getVerticalViewAngle is " + parameters.getVerticalViewAngle());
        System.out.println("getVideoStabilization is " + parameters.getVideoStabilization());
        System.out.println("getWhiteBalance is " + parameters.getWhiteBalance());
        System.out.println("getZoom is " + parameters.getZoom());
        System.out.println("getZoomRatios is " + parameters.getZoomRatios());

    }
}
