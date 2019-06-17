package mine.camerax;


import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
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

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK;
import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

public class TakePictureActivity extends AppCompatActivity {
    Camera camera;
    SurfaceView surfaceView;
    TextureView textureView;
    OrientationEventListener orientationEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_horizontal);


        //判断是否设备有摄像头
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            System.out.println("Camera is supported");
            camera = Camera.open();//获取摄像头
        } else {
            System.out.println("Camera isn't supported");
            return;
        }

        //是否支持自动对焦
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_AUTOFOCUS)) {
            System.out.println("Camera autofocus is supported");
        } else {
            System.out.println("Camera autofocus isn't supported");
            return;
        }


        if (camera == null) {
            camera = Camera.open();
        } else {
            camera.release();//先释放
            camera = Camera.open();//再获取实例
        }

        //监听器方向改变
        orientationEventListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_UI) {
            @Override
            public void onOrientationChanged(int orientation) {
                System.out.println("~~onOrientationChanged~~");
                System.out.println("orientation is " + orientation);

            }
        };

        textureView(); //使用TextureView
//        surfaceView(); //使用SurfaceView

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
//                    camera.setPreviewCallback(new Camera.PreviewCallback() { //每帧画面显示后，再将数据传递给监听器
//                        @Override
//                        public void onPreviewFrame(byte[] data, Camera camera) {
//                            BitmapFactory.Options options = new BitmapFactory.Options();
//                            options.inJustDecodeBounds = true;
//                            BitmapFactory.decodeByteArray(data, 0, data.length, options);
//                            System.out.println("width=" + options.outWidth + ", height=" + options.outHeight);
//                        }
//                    });
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
        orientationEventListener.enable();

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
        orientationEventListener.disable();


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

        camera.stopPreview();

        //设置预览尺寸
        int widht = 320, height = 240;
        Camera.Parameters parameters = camera.getParameters();
        parameters.setPreviewSize(widht, height);
        camera.setParameters(parameters);
        System.out.println("PreView|height=" + camera.getParameters().getPreviewSize().height +
                ", width=" + camera.getParameters().getPreviewSize().width);

        ViewGroup.LayoutParams params = textureView.getLayoutParams();
        params.width = widht;
        params.height = height;
        textureView.setLayoutParams(params);
        System.out.println("textureView|height=" + params.height + ", width=" + params.width);


        camera.startPreview();

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

        Camera.Parameters parameters = camera.getParameters();
        parameters.setRotation(90);
        parameters.setJpegQuality(100);
//        parameters.setPictureSize(1920, 1080);//模拟器上不支持这样的尺寸，但小米C4支持
//        parameters.setPictureFormat(ImageFormat.JPEG);//JPG是默认值，可以省略
        camera.setParameters(parameters);

        //直接拍照
//        camera.takePicture(new Camera.ShutterCallback() {
//            @Override
//            public void onShutter() {
//                System.out.println("~~onShutter~~");
//            }
//        }, new Camera.PictureCallback() {
//            @Override
//            public void onPictureTaken(byte[] data, Camera camera) {
//                System.out.println("~~raw~~");
////                System.out.println("data'size is " + data.length);
//                System.out.println("camera is " + camera);
//
//            }
//        }, new Camera.PictureCallback() {
//            @Override
//            public void onPictureTaken(byte[] data, Camera camera) {
//                System.out.println("~~postview~~");
////                System.out.println("data'size is " + data.length);
//                System.out.println("camera is " + camera);
//
//            }
//        }, new Camera.PictureCallback() {
//            @Override
//            public void onPictureTaken(byte[] data, Camera camera) {
//                System.out.println("~~jpeg~~");
//                System.out.println("data'size is " + data.length);
//                System.out.println("camera is " + camera);
//
//                try {
//                    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//                    File pic = File.createTempFile(timeStamp, ".jpg", getCacheDir());
//                    FileOutputStream fileOutputStream = new FileOutputStream(pic);
//                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//                    bufferedOutputStream.write(data);
//                    bufferedOutputStream.close();
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    camera.startPreview();
//                }
//
//
//            }
//        });


        //自动对焦后再拍照
        camera.autoFocus(new Camera.AutoFocusCallback() {
            @Override
            public void onAutoFocus(boolean success, Camera camera) {
                System.out.println("~~onAutoFocus~~");
                System.out.println("success is " + success);
                System.out.println("camera is " + camera);

                if (!success) return;

                camera.takePicture(null, null, new Camera.PictureCallback() {
                    @Override
                    public void onPictureTaken(byte[] data, Camera camera) {
                        try {
                            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                            File pic = File.createTempFile(timeStamp, ".jpg", getCacheDir());
                            FileOutputStream fileOutputStream = new FileOutputStream(pic);
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                            bufferedOutputStream.write(data);
                            bufferedOutputStream.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            camera.startPreview();
                        }
                    }
                });

            }
        });


    }


    public void query(View view) {
        System.out.println("~~button.query~~");


        //Camera
        paremeters();//参数


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
    }

    private void face() {
        camera.setFaceDetectionListener(new Camera.FaceDetectionListener() {
            @Override
            public void onFaceDetection(Camera.Face[] faces, Camera camera) {
                System.out.println("~~onFaceDetection~~");
                for (Camera.Face face : faces) {

                    System.out.println("id is " + face.id);
                    System.out.println("leftEye is " + face.leftEye);
                    System.out.println("rightEye is " + face.rightEye);
                    System.out.println("mouth is " + face.mouth);
                    System.out.println("rect is " + face.rect);
                    System.out.println("score is " + face.score);
                }
            }
        });
        camera.startFaceDetection();
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
//        System.out.println("getMeteringAreas is " + parameters.getMeteringAreas());
//        System.out.println("getMaxNumMeteringAreas is " + parameters.getMaxNumMeteringAreas());


        //场景模式
        System.out.println("-------Scene--------");
        System.out.println("getSceneMode is " + parameters.getSceneMode());
        System.out.println("getSupportedSceneModes is " + parameters.getSupportedSceneModes());

    }
}
