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
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Camera2PreviewActivity extends AppCompatActivity {


    private TextureView textureView;
    private SurfaceView surfaceView;
    private ImageReader imageReader;
    private CameraCaptureSession cameraCaptureSession;
    private CaptureRequest.Builder captureRequestBuilder;
    private HandlerThread handlerThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_camera2);


        surfaceTexture();
//        surfaceView();

    }

    private void surfaceTexture() {
        textureView = new TextureView(this);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {//增加监听器
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                System.out.println("~~onSurfaceTextureAvailable~~");
                System.out.println("surface is " + surface);
                System.out.println("width is " + width + ", height is " + height);
                openCamera();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                System.out.println("~~onSurfaceTextureSizeChanged~~");
                System.out.println("surface is " + surface);
                System.out.println("width is " + width + ", height is " + height);
                configureTransform(width, height);
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                System.out.println("~~onSurfaceTextureDestroyed~~");
                System.out.println("surface is " + surface);

                return true;
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
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
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


    private void  openCamera() {

        //检查权限
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("have no PERMISSION of CAMERA");
            return;
        }

        if (handlerThread == null) {
            handlerThread = new HandlerThread("Camera");
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
                Size size = sizes[0];//获取最小尺寸
                textureView.getSurfaceTexture().setDefaultBufferSize(size.getWidth(), size.getHeight());


                sizes = map.getOutputSizes(ImageReader.class);
                size = sizes[sizes.length - 1];//获取最大尺寸
                imageReader = ImageReader.newInstance(size.getWidth(), size.getHeight(), ImageFormat.JPEG, 1);
                imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                    @Override
                    public void onImageAvailable(ImageReader reader) {
                        System.out.println("~~onImageAvailable~~");
                        System.out.println(Thread.currentThread());

                        Image image = reader.acquireLatestImage();

                        System.out.println("reader|width=" + reader.getWidth() + ", height=" + reader.getHeight());
                        System.out.println("reader|getMaxImages is " + reader.getMaxImages());

                        for (Field field : ImageFormat.class.getFields()) {
                            try {
                                if ((int) field.get(null) == reader.getImageFormat())
                                    System.out.println("reader|getImageFormat is " + field.getName());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }


                        System.out.println("image|width=" + image.getWidth() + ", height=" + image.getHeight());
                        System.out.println("image|getTimestamp is " + image.getTimestamp());
                        System.out.println("image|getCropRect is " + image.getCropRect());
                        for (Field field : ImageFormat.class.getFields()) {
                            try {
                                if ((int) field.get(null) == image.getFormat())
                                    System.out.println("image|getImageFormat is " + field.getName());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }


                        System.out.println("Buffer'capacity is " + image.getPlanes()[0].getBuffer().capacity());
                        System.out.println("getPixelStride is " + image.getPlanes()[0].getPixelStride());
                        System.out.println("getRowStride is " + image.getPlanes()[0].getRowStride());


                        try {
                            String fileName = Long.toString(image.getTimestamp());
                            File file = File.createTempFile(fileName.substring(fileName.length() - 9) + "_",
                                    ".jpg",
                                    getExternalFilesDir(Environment.DIRECTORY_PICTURES));
                            System.out.println(file);

                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                            while (image.getPlanes()[0].getBuffer().hasRemaining()) {
                                bufferedOutputStream.write(image.getPlanes()[0].getBuffer().get());
                            }
                            bufferedOutputStream.close();
                        } catch (IOException e) {

                            e.printStackTrace();
                        } finally {
                            image.close();
                        }

                    }
                }, new Handler(handlerThread.getLooper()));


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

                        if (handlerThread != null) {
                            handlerThread.quitSafely();
                            handlerThread = null;
                        }
                    }

                    @Override
                    public void onDisconnected(@NonNull CameraDevice camera) {
                        System.out.println("~~Camera.onDisconnected~~");
                        camera.close();
                    }

                    @Override
                    public void onError(@NonNull CameraDevice camera, int error) {
                        System.out.println("~~Camera.onError~~");
                        camera.close();
                    }
                }, new Handler(getMainLooper()));

                break;
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


    }


    private void createCameraPreviewSession(CameraDevice camera) {

        try {
            List<Surface> surfaceList = Arrays.asList(new Surface(textureView.getSurfaceTexture()), imageReader.getSurface());

            //创建会话
            camera.createCaptureSession(surfaceList, new CameraCaptureSession.StateCallback() {

                @Override
                public void onClosed(@NonNull CameraCaptureSession session) {
                    super.onClosed(session);
                    System.out.println("~~Session.onClosed~~");

                    if (null != imageReader) {
                        imageReader.close();
                        imageReader = null;
                    }
                    session.getDevice().close();


                }

                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    System.out.println("~~Session.onConfigured~~");
                    cameraCaptureSession = session;//保存会话对象


                    try {
                        //创建预览请求
                        CameraDevice cameraDevice = session.getDevice();
                        captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
                        captureRequestBuilder.addTarget(surfaceList.get(0));//增加Surface
                        captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_AUTO);

                        //请求预览（最简使用）
                        int id = session.setRepeatingRequest(captureRequestBuilder.build(), null, null);
                        System.out.println("setRepeatingRequest'id is " + id);


                        //请求预览（传递监听器）
//                        int id = session.setRepeatingRequest(previewRequest, new CameraCaptureSession.CaptureCallback() {
//                            @Override
//                            public void onCaptureStarted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, long timestamp, long frameNumber) {
//                                super.onCaptureStarted(session, request, timestamp, frameNumber);
//                                System.out.println("~~onCaptureStarted~~");
//                            }
//
//                            @Override
//                            public void onCaptureProgressed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureResult partialResult) {
//                                super.onCaptureProgressed(session, request, partialResult);
//                                System.out.println("~~onCaptureProgressed~~");
//                            }
//
//                            @Override
//                            public void onCaptureCompleted(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull TotalCaptureResult result) {
//                                super.onCaptureCompleted(session, request, result);
//                                System.out.println("~~onCaptureCompleted~~");
//                            }
//
//                            @Override
//                            public void onCaptureFailed(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull CaptureFailure failure) {
//                                super.onCaptureFailed(session, request, failure);
//                                System.out.println("~~onCaptureFailed~~");
//                            }
//
//                            @Override
//                            public void onCaptureSequenceCompleted(@NonNull CameraCaptureSession session, int sequenceId, long frameNumber) {
//                                System.out.println("~~onCaptureSequenceCompleted~~");
//                                System.out.println("sequenceId is " + sequenceId);
//                                System.out.println("frameNumber is " + frameNumber);
//                            }
//
//                            @Override
//                            public void onCaptureSequenceAborted(@NonNull CameraCaptureSession session, int sequenceId) {
//                                super.onCaptureSequenceAborted(session, sequenceId);
//                                System.out.println("~~onCaptureSequenceAborted~~");
//                            }
//
//                            @Override
//                            public void onCaptureBufferLost(@NonNull CameraCaptureSession session, @NonNull CaptureRequest request, @NonNull Surface target, long frameNumber) {
//                                super.onCaptureBufferLost(session, request, target, frameNumber);
//                                System.out.println("~~onCaptureBufferLost~~");
//                            }
//                        }, new Handler(getMainLooper()));
//                        System.out.println("setRepeatingRequest'id is " + id);

                    } catch (CameraAccessException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onConfigureFailed(@NonNull CameraCaptureSession session) {
                    System.out.println("~~Session.onConfigureFailed~~");
                    if (null != imageReader) {
                        imageReader.close();
                        imageReader = null;
                    }
                    session.getDevice().close();
                }
            }, new Handler(getMainLooper()));


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


    }

    private void configureTransform(int width, int height) {

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
        if (cameraCaptureSession == null && textureView.isAvailable()) openCamera();
    }

    @Override
    protected void onPause() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
        if (null != cameraCaptureSession) {
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }

        super.onPause();
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

        if (cameraCaptureSession == null && textureView.isAvailable()) openCamera();
//        if (!surfaceView.getHolder().isCreating()) openCamera();
    }


    public void close(View view) {
        System.out.println("~~button.close~~");

        if (cameraCaptureSession != null) {
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }

    }


    public void pause(View view) {
        System.out.println("~~button.pause~~");

        try {
            cameraCaptureSession.stopRepeating();
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }


    public void resume(View view) {
        System.out.println("~~button.resume~~");

        try {
            int id = cameraCaptureSession.setRepeatingRequest(captureRequestBuilder.build(), null, null);
            System.out.println("setRepeatingRequest'id is " + id);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void take(View view) {
        System.out.println("~~button.take~~");

        try {
            //创建拍摄请求
            CameraDevice cameraDevice = cameraCaptureSession.getDevice();
            CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(imageReader.getSurface());

            //请求拍摄
            int id = cameraCaptureSession.capture(captureBuilder.build(), null, null);
            System.out.println("setRepeatingRequest'id is " + id);


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

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

    public void end(View view) {
        System.out.println("~~button.end~~");
    }

    public void init(View view) {
        System.out.println("~~button.init~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void modify(View view) {
        System.out.println("~~button.modify~~");

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
