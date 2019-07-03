package mine.camerax;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

/**
 * 这个版本是自己写的，和官方提供的代码示例不同
 * Activity中仅保存会话对象和预览构建器
 * 使用单会话对象，每次录制时，自动转换会话对象，充分利用系统自动关闭功能（新会话对象创建时，系统自动关闭旧会话对象）
 */
public class Camera2RecordActivity extends AppCompatActivity {


    private TextureView textureView;
    private CameraCaptureSession cameraCaptureSession;
    private CaptureRequest.Builder captureRequestBuilder;
    private MediaRecorder mediaRecorder;
    private boolean isRecord = false;
    private HandlerThread handlerThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_camera2_video);

        surfaceTexture();
    }

    private void surfaceTexture() {
        textureView = new TextureView(this);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {//增加监听器
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                System.out.println("~~onSurfaceTextureAvailable~~");
                System.out.println("surfaceTexture is " + surface);
                System.out.println("width is " + width + ", height is " + height);
                openCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                System.out.println("~~onSurfaceTextureSizeChanged~~");
                System.out.println("surfaceTexture is " + surface);
                System.out.println("width is " + width + ", height is " + height);
                configureTransform(width, height);
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                System.out.println("~~onSurfaceTextureDestroyed~~");
                System.out.println("surfaceTexture is " + surface);

                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
//                System.out.println("~~onSurfaceTextureUpdated~~");
//                System.out.println("surfaceTexture is " + surfaceTexture);
            }
        });
        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(textureView);
    }


    private void configureTransform(int width, int height) {

    }

    private void openCamera() {


        //检查权限
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("have no PERMISSION of CAMERA");
            return;
        }

        if (handlerThread == null) {
            handlerThread = new HandlerThread("camera");
            handlerThread.start();
        }

        //配置摄像头
        CameraManager cameraManager = getSystemService(CameraManager.class);//获取管理对象
        try {
            for (String cameraId : cameraManager.getCameraIdList()) {
                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);

                //使用后置摄像头
                Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing != null && facing == CameraMetadata.LENS_FACING_FRONT) continue;

                //图片流配置
                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                if (map == null) continue;

                Size[] sizes = map.getOutputSizes(SurfaceTexture.class);
                for (Size s : sizes) System.out.println("SurfaceTexture|size = " + s);
                Size size = sizes[0];//获取最小尺寸
                textureView.getSurfaceTexture().setDefaultBufferSize(size.getWidth(), size.getHeight());


                //获取视频尺寸，其实没必要，太尺寸基本用不着，720p是手机最佳选择
                sizes = map.getOutputSizes(MediaRecorder.class);
                for (Size s : sizes) System.out.println("MediaRecorder|size = " + s);


                //打开摄像头
                cameraManager.openCamera(cameraId, new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(@NonNull CameraDevice camera) {
                        System.out.println("~~Camera.onOpened~~");
                        createCameraPreviewSession(camera);//创建预览会话
                    }

                    @Override
                    public void onClosed(@NonNull CameraDevice camera) {
                        System.out.println("~~Camera.onClosed~~");
                        super.onClosed(camera);
                    }

                    @Override
                    public void onDisconnected(@NonNull CameraDevice camera) {
                        System.out.println("~~Camera.onDisconnected~~");
                        camera.close();

                        if (handlerThread != null) {
                            handlerThread.quitSafely();
                            handlerThread = null;
                        }
                    }

                    @Override
                    public void onError(@NonNull CameraDevice camera, int error) {
                        System.out.println("~~Camera.onError~~");
                        printFieldValue(error, CameraAccessException.class, "ERROR_CAMERA");
                        camera.close();
                    }
                }, new Handler(handlerThread.getLooper()));

                break;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


    }

    private void prepare() {
        mediaRecorder.reset();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
        mediaRecorder.setVideoFrameRate(30);
        mediaRecorder.setVideoSize(640, 480);
        mediaRecorder.setVideoEncodingBitRate(3 * 1024 * 1024);
//        mediaRecorder.setVideoSize(1280, 720);
//        mediaRecorder.setVideoEncodingBitRate(5 * 1024 * 1024);

        File file = null;
        try {
            file = File.createTempFile("XXX", ".mp4", getExternalFilesDir(Environment.DIRECTORY_MOVIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(file);
        mediaRecorder.setOutputFile(file.toString());

        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
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


        if (mediaRecorder == null) mediaRecorder = new MediaRecorder();
        if (cameraCaptureSession == null && textureView.isAvailable()) openCamera();


    }

    @Override
    protected void onPause() {

        if (mediaRecorder != null) {
            mediaRecorder.reset();
            mediaRecorder.release();
            mediaRecorder = null;
        }

        if (null != cameraCaptureSession) {
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }


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
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
        super.onDestroy();
    }

    private void createCameraPreviewSession(CameraDevice cameraDevice) {

        List<Surface> surfaceList = Arrays.asList(new Surface(textureView.getSurfaceTexture()));
        try {
            //创建会话
            cameraDevice.createCaptureSession(surfaceList, new CameraCaptureSession.StateCallback() {
                @Override
                public void onReady(@NonNull CameraCaptureSession session) {
                    super.onReady(session);
                    System.out.println("~~PreviewSession.onReady~~");
                }

                @Override
                public void onActive(@NonNull CameraCaptureSession session) {
                    super.onActive(session);
                    System.out.println("~~PreviewSession.onActive~~");
                }

                @Override
                public void onCaptureQueueEmpty(@NonNull CameraCaptureSession session) {
                    super.onCaptureQueueEmpty(session);
                    System.out.println("~~PreviewSession.onCaptureQueueEmpty~~");
                }

                @Override
                public void onClosed(@NonNull CameraCaptureSession session) {
                    super.onClosed(session);
                    System.out.println("~~PreviewSession.onClosed~~");

                    if (isRecord) return;
                    CameraDevice cameraDevice = session.getDevice();
                    cameraDevice.close();
                }

                @Override
                public void onSurfacePrepared(@NonNull CameraCaptureSession session, @NonNull Surface surface) {
                    super.onSurfacePrepared(session, surface);
                    System.out.println("~~PreviewSession.onSurfacePrepared~~");
                }

                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    System.out.println("~~PreviewSession.onConfigured~~");
                    cameraCaptureSession = session;//保存会话对象

                    try {

                        //创建预览请求
                        captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                        captureRequestBuilder.addTarget(surfaceList.get(0));//增加Surface
                        captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_AUTO);
                        CaptureRequest captureRequest = captureRequestBuilder.build();

                        //请求预览
                        int id = session.setRepeatingRequest(captureRequest, null, null);
                        System.out.println("preview'id is " + id);
                        System.out.println(Thread.currentThread());

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    System.out.println("~~PreviewSession.onConfigureFailed~~");
                    session.close();
                }
            }, new Handler(handlerThread.getLooper()));


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void createCameraRecordSession(CameraDevice cameraDevice) {
        try {

            prepare();
            isRecord = true;
            List<Surface> surfaceList = Arrays.asList(new Surface(textureView.getSurfaceTexture()), mediaRecorder.getSurface());

            //创建会话
            cameraDevice.createCaptureSession(surfaceList, new CameraCaptureSession.StateCallback() {
                @Override
                public void onReady(@NonNull CameraCaptureSession session) {
                    super.onReady(session);
                    System.out.println("~~RecordSession.onReady~~");
                }

                @Override
                public void onActive(@NonNull CameraCaptureSession session) {
                    super.onActive(session);
                    System.out.println("~~RecordSession.onActive~~");
                }

                @Override
                public void onCaptureQueueEmpty(@NonNull CameraCaptureSession session) {
                    super.onCaptureQueueEmpty(session);
                    System.out.println("~~RecordSession.onCaptureQueueEmpty~~");
                }

                @Override
                public void onClosed(@NonNull CameraCaptureSession session) {
                    super.onClosed(session);
                    System.out.println("~~RecordSession.onClosed~~");

                    if (isRecord) {
                        CameraDevice cameraDevice = session.getDevice();
                        cameraDevice.close();
                    } else {
                        createCameraPreviewSession(session.getDevice());
                    }

                }

                @Override
                public void onSurfacePrepared(@NonNull CameraCaptureSession session, @NonNull Surface surface) {
                    super.onSurfacePrepared(session, surface);
                    System.out.println("~~RecordSession.onSurfacePrepared~~");
                }

                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    System.out.println("~~RecordSession.onConfigured~~");
                    cameraCaptureSession = session;//保存会话对象

                    try {
                        //创建预览请求
                        captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_RECORD);
                        for (Surface surface : surfaceList) {
                            captureRequestBuilder.addTarget(surface);//增加Surface
                        }
                        captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_AUTO);
                        CaptureRequest captureRequest = captureRequestBuilder.build();

                        //请求预览
                        int id = session.setRepeatingRequest(captureRequest, null, null);
                        System.out.println("record'id is " + id);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    System.out.println("~~RecordSession.onConfigureFailed~~");

                }
            }, null);


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    public void open(View view) {
        System.out.println("~~button.open~~");

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        try {
            cameraCaptureSession.stopRepeating();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void close(View view) {
        System.out.println("~~button.close~~");

        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }
    }

    public void restart(View view) {
        System.out.println("~~button.restart~~");

        try {
            cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null, null);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void record(View view) {
        System.out.println("~~button.record~~");
        if (isRecord) return;

        try {
            cameraCaptureSession.stopRepeating();
            cameraCaptureSession.abortCaptures();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
        createCameraRecordSession(cameraCaptureSession.getDevice());
        mediaRecorder.start();
    }

    public void cease(View view) {
        System.out.println("~~button.cease~~");
        if (!isRecord) return;
        mediaRecorder.stop();
        mediaRecorder.reset();
        isRecord = false;
        cameraCaptureSession.close();
    }

    public void pause(View view) {
        System.out.println("~~button.pause~~");

        if (isRecord) mediaRecorder.pause();
    }


    public void resume(View view) {
        System.out.println("~~button.resume~~");
        if (isRecord) mediaRecorder.resume();
    }


    public void preview(View view) {
        System.out.println("~~button.preview~~");

        if (cameraCaptureSession == null && textureView.isAvailable()) openCamera();

    }

    private void imageFormat(int format) {
        for (Field field : ImageFormat.class.getFields()) {
            try {
                if ((int) field.get(null) != format) continue;
                System.out.println("getFormat is " + field.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    public void config(View view) {
        System.out.println("~~button.config~~");
        
    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

        CameraManager cameraManager = getSystemService(CameraManager.class);
        try {
            for (String cameraId : cameraManager.getCameraIdList()) {
                System.out.println("-=-=-=camera" + cameraId + "=-=-=-");

                CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);

                //摄像头方向
                Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);
                if (facing != null) {
                    printFieldValue(facing, CameraMetadata.class, "LENS_FACING");
                }


                //图片流配置
                StreamConfigurationMap map = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
                if (map == null) continue;
//                System.out.println(map);//一次性打印所有信息


                for (int format : map.getOutputFormats()) {//获取所有支持格式
                    for (Field field : ImageFormat.class.getFields()) {
                        try {
                            if ((int) field.get(null) == format) {
                                System.out.println("Format is " + field.getName());
                                for (Size size : map.getOutputSizes(format)) {//获取格式支持的所有尺寸
                                    System.out.println("  size is " + size);
                                }
                            }

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

                for (Size size : map.getOutputSizes(SurfaceTexture.class)) //获取UI组件支持的所有尺寸
                    System.out.println("getOutputSizes is " + size);


            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private void printFieldValue(Object o, Class c, String name) {

        for (Field field : c.getFields()) {
            try {
                if (field.getName().contains(name) && field.get(null) == o)
                    System.out.println(name + " = " + field.getName());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
