package mine.camerax;


import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

/**
 * 本对象专门用于测试图片格式转换，其他代码全部去掉了
 */
public class FormatConvertActivity extends AppCompatActivity {
    Camera camera;
    TextureView textureView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_horizontal);


        textureView = new TextureView(this);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                System.out.println("~~onSurfaceTextureAvailable~~");
                System.out.println("surface is " + surface);
                System.out.println("width is " + width + ", height is " + height);


                System.out.println("getTimestamp is " + surface.getTimestamp());


                try {
                    camera.setPreviewTexture(surface);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                System.out.println("~~onSurfaceTextureSizeChanged~~");
                System.out.println("surface is " + surface);
                System.out.println("width is " + width + ", height is " + height);
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                System.out.println("~~onSurfaceTextureDestroyed~~");
                System.out.println("surface is " + surface);

                if (camera != null) {
                    camera.stopPreview();
                    camera.release();
                }
                return true;//表示SurfaceTexture已无渲染任务，可以安全销毁SurfaceTexture
//                return false; //表示渲染任务还有结束，占不能销毁，等到SurfaceTexture.release()再销毁
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//                System.out.println("~~onSurfaceTextureUpdated~~");
//                System.out.println("surface is " + surface);
//                Bitmap bitmap = textureView.getBitmap();
//                System.out.println("getRowBytes is " + bitmap.getRowBytes());
//                System.out.println("getHeight is " + bitmap.getHeight());
//                System.out.println("getWidth is " + bitmap.getWidth());

            }
        });

        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(textureView);


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


        if (camera == null) {
            camera = Camera.open();
            System.out.println("re-open|" + camera);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");


        if (camera != null) {
            camera.stopPreview(); //停止预览
            camera.release(); //释放资源
            camera = null;
            System.out.println("camera is " + camera);
        }

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

        camera.stopPreview();

//        camera.setDisplayOrientation(90);

//        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
//        camera.getCameraInfo(0, cameraInfo);


        //设置拍摄角度
//        Camera.Parameters parameters = camera.getParameters();
//        System.out.println("orientation is " + (orientation += 90));
//        parameters.setRotation(orientation % 360);
//        camera.setParameters(parameters);


        //预览设置
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(320, 240);


        //拍照设置
//        parameters.setRotation(90);//配置拍摄图片的角度
//        parameters.setPictureSize(352, 288);//配置拍摄图片的尺寸


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

        camera.setOneShotPreviewCallback(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                System.out.println("~~onPreviewFrame~~");
                System.out.println(data.length);
            }
        });

    }


    public void query(View view) {
        System.out.println("~~button.query~~");


        //获取SurfaceView尺寸
//        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
//        System.out.println("SurfaceView height is " + params.height);
//        System.out.println("SurfaceView width is " + params.width);


        //获取屏幕角度
//        int degrees = 0;
//        int rotation = getWindowManager().getDefaultDisplay().getRotation();
//        switch (rotation) {
//            case Surface.ROTATION_0:
//                degrees = 0;
//                System.out.println("rotation is Surface.ROTATION_0");
//                break;
//            case Surface.ROTATION_90:
//                degrees = 90;
//                System.out.println("rotation is Surface.ROTATION_90");
//                break;
//            case Surface.ROTATION_180:
//                degrees = 180;
//                System.out.println("rotation is Surface.ROTATION_180");
//                break;
//            case Surface.ROTATION_270:
//                degrees = 270;
//                System.out.println("rotation is Surface.ROTATION_270");
//                break;
//        }


        //Camera
        paremeters();//参数
//        face();//面
//        size();//尺寸
//        area();//区域


    }

    private void area() {

    }

    private void size(String prix, List<Camera.Size> sizes) {
        for (Camera.Size size : sizes) {
            System.out.println(prix + " width=" + size.width + ", height=" + size.height);
        }

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

        //通过键值对获取
//        System.out.println("-------Key-Value--------");
//            System.out.println("get is " + parameters.get(String key));
//            System.out.println("getInt is " + parameters.getInt(String key));


        System.out.println("-------Antibanding--------");
        System.out.println("getAntibanding is " + parameters.getAntibanding());
        System.out.println("getSupportedAntibanding is " + parameters.getSupportedAntibanding());


        //曝光
        System.out.println("-------Exposure--------");
        System.out.println("getAutoExposureLock is " + parameters.getAutoExposureLock());
        System.out.println("getExposureCompensation is " + parameters.getExposureCompensation());
        System.out.println("getExposureCompensationStep is " + parameters.getExposureCompensationStep());
        System.out.println("getMaxExposureCompensation is " + parameters.getMaxExposureCompensation());
        System.out.println("getMinExposureCompensation is " + parameters.getMinExposureCompensation());

        //白平衡
        System.out.println("-------WhiteBalance--------");
        System.out.println("getAutoWhiteBalanceLock is " + parameters.getAutoWhiteBalanceLock());
        System.out.println("getWhiteBalance is " + parameters.getWhiteBalance());
        System.out.println("getSupportedWhiteBalance is " + parameters.getSupportedWhiteBalance());


        //色彩
        System.out.println("-------ColorEffect--------");
        System.out.println("getColorEffect is " + parameters.getColorEffect());
        System.out.println("getSupportedColorEffects is " + parameters.getSupportedColorEffects());


        //闪光灯
        System.out.println("-------Flash--------");
        System.out.println("getFlashMode is " + parameters.getFlashMode());
        System.out.println("getSupportedFlashModes is " + parameters.getSupportedFlashModes());


        //对焦
        System.out.println("-------Focus--------");
        System.out.println("getFocalLength is " + parameters.getFocalLength());
        System.out.println("getFocusAreas is " + parameters.getFocusAreas());

        float[] output = new float[3];
        parameters.getFocusDistances(output);
        System.out.println("FOCUS_DISTANCE_NEAR_INDEX is " + output[0]);
        System.out.println("FOCUS_DISTANCE_OPTIMAL_INDEX is " + output[1]);
        System.out.println("FOCUS_DISTANCE_FAR_INDEX is " + output[2]);

        System.out.println("getFocusMode is " + parameters.getFocusMode());
        System.out.println("getMaxNumFocusAreas is " + parameters.getMaxNumFocusAreas());
        System.out.println("getSupportedFocusModes is " + parameters.getSupportedFocusModes());


        //焦距
        System.out.println("-------Zoom--------");
        System.out.println("isZoomSupported is " + parameters.isZoomSupported());
        System.out.println("getMaxZoom is " + parameters.getMaxZoom());
        System.out.println("getZoom is " + parameters.getZoom());
        System.out.println("getZoomRatios is " + parameters.getZoomRatios());


        //防抖
        System.out.println("-------Angle--------");
        System.out.println("getHorizontalViewAngle is " + parameters.getHorizontalViewAngle());
        System.out.println("getVerticalViewAngle is " + parameters.getVerticalViewAngle());


        //脸部识别
        System.out.println("-------Faces--------");
        System.out.println("getMaxNumDetectedFaces is " + parameters.getMaxNumDetectedFaces());


        //缩略图
        System.out.println("-------Thumbnail--------");
        System.out.println("getJpegThumbnailQuality is " + parameters.getJpegThumbnailQuality());
        System.out.println("getJpegThumbnailSize is " + parameters.getJpegThumbnailSize());
        size("getSupportedJpegThumbnailSizes is", parameters.getSupportedJpegThumbnailSizes());


        //拍摄图片
        System.out.println("-------Format--------");
        System.out.println("getJpegQuality is " + parameters.getJpegQuality());
        System.out.println("getPictureFormat is " + parameters.getPictureFormat());
        System.out.println("getSupportedPictureFormats is " + parameters.getSupportedPictureFormats());
        System.out.println("getPictureSize is " + parameters.getPictureSize());
        size("getSupportedPictureSizes is", parameters.getSupportedPictureSizes());

        //录制
        System.out.println("-------Video--------");
        System.out.println("getPreferredPreviewSizeForVideo is " + parameters.getPreferredPreviewSizeForVideo());
        System.out.println("getSupportedVideoSizes is " + parameters.getSupportedVideoSizes());
        System.out.println("getVideoStabilization is " + parameters.getVideoStabilization());


        //预览
        System.out.println("-------Preview--------");
        System.out.println("getPreviewFormat is " + parameters.getPreviewFormat() + "|getBitsPerPixel is " + ImageFormat.getBitsPerPixel(parameters.getPreviewFormat()));
        System.out.println("getSupportedPreviewFormats is " + parameters.getSupportedPreviewFormats());
        System.out.println("getPreviewFrameRate is " + parameters.getPreviewFrameRate());
        System.out.println("getPreviewSize is height=" + parameters.getPreviewSize().height + ", width=" + parameters.getPreviewSize().width);
        size("getSupportedPreviewSizes is", parameters.getSupportedPreviewSizes());

        int[] range = new int[2];
        parameters.getPreviewFpsRange(range);
        System.out.println("range'max is " + range[0]);
        System.out.println("range'min is " + range[1]);

        System.out.println("getSupportedPreviewFpsRange is " + parameters.getSupportedPreviewFpsRange());
        System.out.println("getSupportedPreviewFrameRates is " + parameters.getSupportedPreviewFrameRates());


        //区域
        System.out.println("-------Areas--------");
        System.out.println("getMeteringAreas is " + parameters.getMeteringAreas());
        System.out.println("getMaxNumMeteringAreas is " + parameters.getMaxNumMeteringAreas());


        //场景模式
        System.out.println("-------Scene--------");
        System.out.println("getSceneMode is " + parameters.getSceneMode());
        System.out.println("getSupportedSceneModes is " + parameters.getSupportedSceneModes());

    }
}
