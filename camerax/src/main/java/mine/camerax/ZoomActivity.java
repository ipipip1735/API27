package mine.camerax;


import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ZoomControls;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

public class ZoomActivity extends AppCompatActivity {
    Camera camera;
    SurfaceView surfaceView;
    int zoom = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_horizontal);


        if (camera == null) {
            camera = Camera.open();
        } else {
            camera.release();//先释放
            camera = Camera.open();//再获取实例
        }


        if (camera.getParameters().isSmoothZoomSupported())
            camera.setZoomChangeListener(new Camera.OnZoomChangeListener() {
                @Override
                public void onZoomChange(int zoomValue, boolean stopped, Camera camera) {
                    System.out.println("~~onZoomChange~~");
                    System.out.println("zoomValue is " + zoomValue);
                    System.out.println("stopped is " + stopped);
                    System.out.println("camera is " + camera);

                }
            });

        surfaceView(); //使用SurfaceView

    }

    private void surfaceView() {
        surfaceView = new SurfaceView(this);
        SurfaceHolder surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(final SurfaceHolder holder) {
                System.out.println("~~~~~~~  " + getClass().getSimpleName() + ".surfaceCreated  ~~~~~~~");
                System.out.println("holder is " + holder);

                try {
                    //配置预览
                    camera.setDisplayOrientation(90);//设置预览画面角度（默认是场景模式，画面是横向的）
                    camera.setPreviewDisplay(holder);//绑定展示画面用的SurfaceHolder
                    camera.startPreview();//开始预览

                    //监听器自动对焦
                    camera.autoFocus(new Camera.AutoFocusCallback() {
                        @Override
                        public void onAutoFocus(boolean success, Camera camera) {
                            System.out.println("~~onAutoFocus~~");
                            System.out.println("success is " + success);
                            System.out.println("camera is " + camera);
                        }
                    });

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

                camera.stopPreview();

            }
        });

        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(surfaceView);


        ZoomControls zoomControls = new ZoomControls(this);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~setOnZoomInClickListener~~");
                camera.startSmoothZoom(zoom += 2);
            }
        });

        viewGroup.addView(zoomControls);


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
        } else {
            camera.release();
            camera = Camera.open();
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


    public void start(View view) {
        System.out.println("~~button.start~~");

        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                System.out.println("~~onAutoFocus~~");
                System.out.println("success is " + success);
                System.out.println("camera is " + camera);
            }
        });

        camera.startPreview();
    }

    private void smoothZoom(int zoom) {


        Camera.Parameters parameters = camera.getParameters();

        if (parameters.isSmoothZoomSupported()) {

            this.zoom += zoom;

            if (this.zoom < 0) this.zoom = 0;//设置上限

            int max = camera.getParameters().getMaxZoom();
            if (this.zoom > max) this.zoom = max;//设置下限

            camera.startSmoothZoom(this.zoom);

        } else {
            System.out.println("Smooth Zoom isn't Supported");
        }


    }


    private void zoom(int zoom) {


        Camera.Parameters parameters = camera.getParameters();

        if (parameters.isZoomSupported()) {

            this.zoom += zoom;

            if (this.zoom < 0) this.zoom = 0;//设置上限

            int max = camera.getParameters().getMaxZoom();
            if (this.zoom > max) this.zoom = max;//设置下限


            parameters.setZoom(this.zoom); //修改焦距
            camera.setParameters(parameters);

        } else {
            System.out.println("Zoom isn't Supported");
        }


    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        camera.stopFaceDetection();
        camera.stopPreview();

    }

    public void config(View view) {
        System.out.println("~~button.config~~");

    }

    public void change(View view) {
        System.out.println("~~button.change~~");


        zoom(10); //普通变焦
//        smoothZoom(10);//平滑变焦
    }

    public void modify(View view) {
        System.out.println("~~button.modify~~");

        zoom(-10);//普通变焦
//        smoothZoom(-10);//普通变焦

//        camera.stopSmoothZoom();//停止变焦
    }


    public void take(View view) {
        System.out.println("~~button.take~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        paremeters();//参数

    }


    private void size(String prix, List<Camera.Size> sizes) {
        for (Camera.Size size : sizes) {
            System.out.println(prix + " width=" + size.width + ", height=" + size.height);
        }

    }

    public void info(View view) {

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
//        System.out.println("getFocusAreas is " + parameters.getFocusAreas());

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
        System.out.println("isSmoothZoomSupported is " + parameters.isSmoothZoomSupported());
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
        System.out.println("-------Picture--------");
        System.out.println("getJpegQuality is " + parameters.getJpegQuality());
        System.out.println("getPictureFormat is " + parameters.getPictureFormat());
        System.out.println("getSupportedPictureFormats is " + parameters.getSupportedPictureFormats());
        System.out.println("getPictureSize is height=" + parameters.getPictureSize().height + ", width=" + parameters.getPreviewSize().width);
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
//        System.out.println("getMeteringAreas is " + parameters.getMeteringAreas());
//        System.out.println("getMaxNumMeteringAreas is " + parameters.getMaxNumMeteringAreas());


        //场景模式
        System.out.println("-------Scene--------");
        System.out.println("getSceneMode is " + parameters.getSceneMode());
        System.out.println("getSupportedSceneModes is " + parameters.getSupportedSceneModes());

    }
}
