package mine.camerax;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureFailure;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.CaptureResult;
import android.hardware.camera2.TotalCaptureResult;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.media.Image;
import android.media.ImageReader;
import android.os.Bundle;
import android.os.Handler;
import android.util.Size;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;

public class Camera2PreviewActivity extends AppCompatActivity {


    private TextureView textureView;
    private SurfaceView surfaceView;
    private ImageReader imageReader;
    private CameraDevice cameraDevice;
    private CameraCaptureSession cameraCaptureSession;

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
//                openCamera();
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

    private void configureTransform(int width, int height) {

    }

    private void openCamera() {


        //检查权限
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("have no PERMISSION of CAMERA");
            return;
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
                Size size = sizes[0];//获取最大尺寸
                textureView.getSurfaceTexture().setDefaultBufferSize(size.getWidth(), size.getHeight());


                sizes = map.getOutputSizes(ImageReader.class);
                size = sizes[sizes.length - 1];
                imageReader = ImageReader.newInstance(size.getWidth(), size.getHeight(), ImageFormat.JPEG, 1);
                imageReader.setOnImageAvailableListener(new ImageReader.OnImageAvailableListener() {
                    @Override
                    public void onImageAvailable(ImageReader reader) {
                        System.out.println("~~onImageAvailable~~");
                        Image image = reader.acquireNextImage();
                        System.out.println("image|width=" + image.getWidth() + ", height=" + image.getHeight());


                    }
                }, new Handler(getMainLooper()));


                //打开摄像头
                cameraManager.openCamera(cameraId, new CameraDevice.StateCallback() {
                    @Override
                    public void onOpened(@NonNull CameraDevice camera) {
                        System.out.println("~~onOpened~~");

                        cameraDevice = camera;//保存摄像头对象
                        createCameraPreviewSession();//创建预览会话
                    }

                    @Override
                    public void onDisconnected(@NonNull CameraDevice camera) {
                        System.out.println("~~onDisconnected~~");

                    }

                    @Override
                    public void onError(@NonNull CameraDevice camera, int error) {
                        System.out.println("~~onError~~");

                    }
                }, new Handler(getMainLooper()));

                break;
            }
        } catch (CameraAccessException e) {
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


    public void preview(View view) {
        System.out.println("~~button.preview~~");

        if (textureView.isAvailable()) openCamera();
//        if (!surfaceView.getHolder().isCreating()) openCamera();
    }

    private void createCameraPreviewSession() {

        try {
            //创建预览请求
            CaptureRequest.Builder previewRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            Surface surface = new Surface(textureView.getSurfaceTexture());
//            Surface surface = surfaceView.getHolder().getSurface();

            previewRequestBuilder.addTarget(surface);//增加Surface
            previewRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CameraMetadata.CONTROL_AF_MODE_AUTO);
            CaptureRequest previewRequest = previewRequestBuilder.build();


            //创建会话
            cameraDevice.createCaptureSession(Arrays.asList(surface, imageReader.getSurface()), new CameraCaptureSession.StateCallback() {
                @Override
                public void onReady(@NonNull CameraCaptureSession session) {
                    super.onReady(session);
                    System.out.println("~~onReady~~");
                }

                @Override
                public void onActive(@NonNull CameraCaptureSession session) {
                    super.onActive(session);
                    System.out.println("~~onActive~~");
                }

                @Override
                public void onCaptureQueueEmpty(@NonNull CameraCaptureSession session) {
                    super.onCaptureQueueEmpty(session);
                    System.out.println("~~onCaptureQueueEmpty~~");
                }

                @Override
                public void onClosed(@NonNull CameraCaptureSession session) {
                    super.onClosed(session);
                    System.out.println("~~onClosed~~");
                }

                @Override
                public void onSurfacePrepared(@NonNull CameraCaptureSession session, @NonNull Surface surface) {
                    super.onSurfacePrepared(session, surface);
                    System.out.println("~~onSurfacePrepared~~");
                }

                @Override
                public void onConfigured(@NonNull CameraCaptureSession session) {
                    System.out.println("~~onConfigured~~");
                    cameraCaptureSession = session;//保存会话对象


                    try {

                        int id = session.setRepeatingRequest(previewRequest, null, null);
                        System.out.println("setRepeatingRequest'id is " + id);


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
                    System.out.println("~~onConfigureFailed~~");

                }
            }, new Handler(getMainLooper()));


        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


    }


    public void close(View view) {
        System.out.println("~~button.close~~");

        if (null != cameraCaptureSession) {
            cameraCaptureSession.close();
            cameraCaptureSession = null;
        }

        if (null != cameraDevice) {
            cameraDevice.close();
            cameraDevice = null;
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


    }

    public void take(View view) {
        System.out.println("~~button.take~~");

        try {
            CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_STILL_CAPTURE);
            captureBuilder.addTarget(imageReader.getSurface());

            cameraCaptureSession.stopRepeating();
            cameraCaptureSession.abortCaptures();
            cameraCaptureSession.capture(captureBuilder.build(), null, null);

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
