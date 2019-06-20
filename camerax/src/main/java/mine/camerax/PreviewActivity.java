package mine.camerax;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ZoomControls;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

public class PreviewActivity extends AppCompatActivity {
    Camera camera;
    int zoom = 0;
    SurfaceView surfaceView;
    TextureView textureView;
    SurfaceTexture surfaceTexture;
    OrientationEventListener orientationEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_horizontal);


        //判断是否设备有摄像头
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            System.out.println("Camera is supported");
        } else {
            System.out.println("Camera isn't supported");
            return;
        }


        if (camera == null) {
            camera = Camera.open();//获取摄像头
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


//        textureView(); //使用TextureView
        surfaceView(); //使用SurfaceView

    }

    private void textureView() {
        textureView = new TextureView(this);//实例化TextureView
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {//增加监听器
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                System.out.println("~~onSurfaceTextureAvailable~~");
                System.out.println("surface is " + surface);
                System.out.println("width is " + width + ", height is " + height);

                try {
                    setCameraDisplayOrientation(0, camera);//设置预览画面角度
                    camera.setPreviewTexture(surface);
                    camera.startPreview();
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
                    camera = null;
                }

                return true; //表示SurfaceTexture已无渲染任务，可以安全销毁SurfaceTexture
//                return false; //表示渲染任务还有结束，占不能销毁，等到SurfaceTexture.release()再销毁
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//                System.out.println("~~onSurfaceTextureUpdated~~");
//                System.out.println("surface is " + surface);
            }
        });
        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(textureView);


        //增加变焦控件
        ZoomControls zoomControls = new ZoomControls(this);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~setOnZoomInClickListener~~");
                camera.getParameters().setZoom(2);//放大
            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~setOnZoomInClickListener~~");
                camera.getParameters().setZoom(-2);//缩小
            }
        });
        viewGroup.addView(zoomControls);

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
                    setCameraDisplayOrientation(0, camera);//设置预览画面角度
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

        //增加变焦控件
        ZoomControls zoomControls = new ZoomControls(this);
        zoomControls.setOnZoomInClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~setOnZoomInClickListener~~");
                zoom(2);//放大
            }
        });
        zoomControls.setOnZoomOutClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("~~setOnZoomInClickListener~~");
                zoom(-2);//缩小
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


    public void take(View view) {
        System.out.println("~~button.take~~");


        //不知道怎么用
//        camera.setAutoFocusMoveCallback(new Camera.AutoFocusMoveCallback() {
//            @Override
//            public void onAutoFocusMoving(boolean start, Camera camera) {
//                System.out.println("~~onAutoFocusMoving~~");
//                System.out.println("start is " + start);
//
//            }
//        });

        //处理预览画面
        camera.setOneShotPreviewCallback(new Camera.PreviewCallback() {
            @Override
            public void onPreviewFrame(byte[] data, Camera camera) {
                System.out.println("~~onPreviewFrame~~");
                System.out.println("data is " + data.length);
                System.out.println("camera is " + camera);


                int width = camera.getParameters().getPreviewSize().width;
                int height = camera.getParameters().getPreviewSize().height;
                System.out.println("PreviewWidth=" + width + ", PreviewHeight=" + height);

                YuvImage yuv = new YuvImage(data, camera.getParameters().getPreviewFormat(), width, height, null);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                yuv.compressToJpeg(new Rect(0, 0, width, height), 100, out);
//                yuv.compressToJpeg(new Rect(0, 0, 320, 240), 100, out);


                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.toByteArray().length, options);
                System.out.println("width=" + options.outWidth + ", height=" + options.outHeight);


                File file = new File(getCacheDir(), "sscc.jpg");
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                    bufferedOutputStream.write(out.toByteArray());
                    bufferedOutputStream.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


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
        System.out.println("ROTATION is " + degrees);

        //计算修正角度
        int result;
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            System.out.println("CAMERA_FACING_FRONT's orientation is " + info.orientation);
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            System.out.println("CAMERA_FACING_BACK's orientation is " + info.orientation);
            result = (info.orientation - degrees + 360) % 360;
        }
        System.out.println("DisplayOrientation is " + result);
        camera.setDisplayOrientation(result);//保存设置
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
        System.out.println("getFocusAreas is " + parameters.getMaxNumFocusAreas());//获取对焦面的最大个数
//        System.out.println("getFocusAreas is " + parameters.getFocusAreas());//获取当前对焦面，真机没有默认对焦面，所以空指针抛异常，要使用对应的set方法设置后才能调用


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
