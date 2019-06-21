package mine.camerax;


import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;
import static android.media.CamcorderProfile.QUALITY_1080P;
import static android.media.CamcorderProfile.QUALITY_720P;
import static android.media.MediaRecorder.OutputFormat.MPEG_4;
import static android.media.MediaRecorder.OutputFormat.THREE_GPP;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class VideoActivity extends AppCompatActivity {
    Camera camera;
    SurfaceView surfaceView;
    MediaRecorder mediaRecorder;
    TextureView textureView;
    SurfaceTexture surfaceTexture;
    OrientationEventListener orientationEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_video);


        //判断是否设备有摄像头
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            System.out.println("Camera is supported");
        } else {
            System.out.println("Camera isn't supported");
            return;
        }


        if (camera == null) {
            camera = Camera.open();
        } else {
            camera.release();//先释放
            camera = Camera.open();//再获取实例
        }


        //监听器方向改变
//        orientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_UI) {
//            @Override
//            public void onOrientationChanged(int orientation) {
//                System.out.println("~~onOrientationChanged~~");
//                System.out.println("orientation is " + orientation);
//
//            }
//        };


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
                    setCameraDisplayOrientation(0, camera);//设置预览画面角度（默认是场景模式，画面是横向的），一般需要通过info对象来计算，这里直接改为90（模拟器上默认90）
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


                    //每帧画面显示后，将获取预览画面的像素数据交给监听器
//                    camera.setPreviewCallback(new Camera.PreviewCallback() {
//                        @Override
//                        public void onPreviewFrame(byte[] data, Camera camera) {
//                            System.out.println("~~onPreviewFrame~~");
//                            System.out.println("data is " + data.length);
//                            System.out.println("camera is " + camera);
//
//
//                        }
//                    });

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

                if (camera != null) {
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                }

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
//        orientationEventListener.enable();//监听方向改变

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
//        orientationEventListener.disable();//停止监听方向改变

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

        camera.startPreview();
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        camera.stopPreview();

    }

    public void begin(View view) {
        System.out.println("~~button.begin~~");

        mediaRecorder.start();


    }

    public void end(View view) {
        System.out.println("~~button.end~~");

//        mediaRecorder.stop();//stop()和release()等价
        mediaRecorder.reset();
        mediaRecorder.release();
        camera.lock();
//        try {
//            camera.reconnect();//隐式调用lock()方法
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void resume(View view) {
        System.out.println("~~button.resume~~");

        mediaRecorder.resume();

    }

    public void pause(View view) {
        System.out.println("~~button.pause~~");

        mediaRecorder.pause();

    }

    public void config(View view) {
        System.out.println("~~button.config~~");

        //设置预览尺寸
        int widht = 320, height = 240;
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(widht, height);
        camera.setParameters(parameters);
        System.out.println("PreView|height=" + camera.getParameters().getPreviewSize().height +
                ", width=" + camera.getParameters().getPreviewSize().width);

        ViewGroup.LayoutParams params = surfaceView.getLayoutParams();
        params.width = widht;
        params.height = height;
        surfaceView.setLayoutParams(params);
        System.out.println("SurfaceView|height=" + params.height + ", width=" + params.width);

    }

    public void change(View view) {
        System.out.println("~~button.change~~");

        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                System.out.println("~~onAutoFocus~~");
                System.out.println("success is " + success);
                System.out.println("camera is " + camera);
            }
        });

    }

    public void modify(View view) {
        System.out.println("~~button.modify~~");

        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(0, info);
        camera.setDisplayOrientation(info.orientation);


    }


    public void init(View view) {
        System.out.println("~~button.init~~");

        mediaRecorder = new MediaRecorder();


        // Step 1: Unlock and set camera to MediaRecorder
        camera.unlock();//解锁摄像头
        mediaRecorder.setCamera(camera);//控制器交给media


        // Step 2: Set sources
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);


        // Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
        CamcorderProfile profile = CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH);
        mediaRecorder.setProfile(profile);

        String fileName = "";
        switch (profile.fileFormat) {
                    case MPEG_4:
                        fileName = new Random().nextInt(100) + ".mp4";
                        break;
                    case THREE_GPP:
                        fileName = new Random().nextInt(100) + ".3gp";
                        break;
        }


        // Step 4: Set output file
        mediaRecorder.setOutputFile(new File(getCacheDir(), fileName).toString());//内部私有目录
//        mediaRecorder.setOutputFile(new File(getExternalMediaDirs()[0], fileName).toString());//外部私有目录
//        mediaRecorder.setOutputFile(new File(Environment.getExternalStoragePublicDiretory(Environment.DIRECTORY_DCIM), getPackageName() + fileName).toString());//共用目录


        // Step 5: Set the preview output
        mediaRecorder.setPreviewDisplay(surfaceView.getHolder().getSurface());


        // Step 6: Prepare configured MediaRecorder
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            mediaRecorder.release();
        }


    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        paremeters();//获取摄像头当前参数

    }


    private void size(String prix, List<Camera.Size> sizes) {
        for (Camera.Size size : sizes) {
            System.out.println(prix + " width=" + size.width + ", height=" + size.height);
        }

    }

    public void info(View view) {

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


        //获取摄像头信息
        for (int i = 0; i < Camera.getNumberOfCameras(); i++) {
            StringBuilder stringBuilder = new StringBuilder(128);

            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i, cameraInfo);

            switch (cameraInfo.facing) {
                case CAMERA_FACING_FRONT: //前置摄像头
                    stringBuilder.append("cameraInfo|id=" + i + ", facing=CAMERA_FACING_BACK, ");
                    break;
                case CAMERA_FACING_BACK: //后置摄像头，即主摄像头
                    stringBuilder.append("cameraInfo|id=" + i + ", facing=CAMERA_FACING_FRONT, ");
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


    private void setCameraDisplayOrientation(int cameraId, android.hardware.Camera camera) {
        android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
        android.hardware.Camera.getCameraInfo(cameraId, info);//获取摄像头的信息对象

        //获取屏幕方向
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        //计算修正角度
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);//保存设置
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
        System.out.println("getMaxNumMeteringAreas is " + parameters.getMaxNumMeteringAreas());
//        System.out.println("getMeteringAreas is " + parameters.getMeteringAreas());//亮度测量区真机上是0，要使用对应的set方法增加后才有，否则抛空指针异常


        //场景模式
        System.out.println("-------Scene--------");
        System.out.println("getSceneMode is " + parameters.getSceneMode());
        System.out.println("getSupportedSceneModes is " + parameters.getSupportedSceneModes());

    }
}
