package mine.camerax;


import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.media.Image;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.util.Rational;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageAnalysisConfig;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Random;

public class CameraXActivity extends AppCompatActivity {

    private ImageCapture imageCapture;
    private Preview preview;
    private ImageAnalysis imageAnalysis;
    private TextureView textureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_camerax);

        textureView = new TextureView(this);


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


        PreviewConfig config = new PreviewConfig.Builder()
                .setLensFacing(CameraX.LensFacing.BACK)
                .setTargetAspectRatio(new Rational(3, 4))
                .setTargetName("ConfigOne")
//                .setTargetResolution(new Size(1080, 720))
//                .setTargetRotation(Surface.ROTATION_180)
                .build();



        preview = new Preview(config);
        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
            @Override
            public void onUpdated(Preview.PreviewOutput previewOutput) {
                System.out.println("~~onUpdated~~");
                System.out.println("previewOutput is " + previewOutput);
                System.out.println("getTextureSize is " + previewOutput.getTextureSize());
                System.out.println("getRotationDegrees is " + previewOutput.getRotationDegrees());
                System.out.println("getSurfaceTexture is " + previewOutput.getSurfaceTexture());

                if (!textureView.isAvailable()) {

                    Matrix matrix = new Matrix();
                    matrix.postRotate(-previewOutput.getRotationDegrees());
//                    textureView.setTransform(matrix);

                    textureView.setSurfaceTexture(previewOutput.getSurfaceTexture());
                    System.out.println("...bind surface...");
//                    textureView.getSurfaceTexture().setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener() {
//                        @Override
//                        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
//                            System.out.println("~~onFrameAvailable~~");
//                            System.out.println("surfaceTexture is " + surfaceTexture);
//                        }
//                    });
                }


//                try {
//                    Thread.sleep(2000L);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

            }
        });


        CameraX.bindToLifecycle(this, preview);

        ViewGroup viewGroup = findViewById(R.id.fl);

        viewGroup.addView(textureView);


    }


    public void change(View view) {
        System.out.println("~~button.change~~");

//        PreviewConfig config = new PreviewConfig.Builder()
//                .setLensFacing(CameraX.LensFacing.BACK)
////                .setTargetAspectRatio(new Rational(3, 4))
////                .setTargetName("ConfigOne")
////                .setTargetResolution(new Size(1080, 720))
//                .setTargetRotation(Surface.ROTATION_90)
//                .build();
//
//        preview = new Preview(config);


//        System.out.println("name is " + preview.getName());
//        textureView.setRotation(90);



    }


    public void take(View view) {
        System.out.println("~~button.take~~");


        //方式一：拍照
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), new Random().nextInt(100) + ".jpg");

        imageCapture.takePicture(file, new ImageCapture.OnImageSavedListener() {
            @Override
            public void onImageSaved(File file) {
                System.out.println("~~onImageSaved~~");
                System.out.println("file is " + file);
            }

            @Override
            public void onError(ImageCapture.UseCaseError useCaseError, String message, Throwable cause) {
                System.out.println("~~onError~~");
                System.out.println("useCaseError is " + useCaseError);
                System.out.println("message is " + message);
                System.out.println("cause is " + cause);
            }
        });


        //方式二：拍照并增加图片位置信息
//        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), new Random().nextInt(100) + ".jpg");
//
//        ImageCapture.Metadata metadata = new ImageCapture.Metadata();
//        metadata.isReversedHorizontal = true;
//        metadata.isReversedVertical = false;
////        metadata.location = new Location() //获取定位信息
//
//        imageCapture.takePicture(file, new ImageCapture.OnImageSavedListener() {
//            @Override
//            public void onImageSaved(File file) {
//                System.out.println("~~onImageSaved~~");
//                System.out.println("file is " + file);
//            }
//
//            @Override
//            public void onError(ImageCapture.UseCaseError useCaseError, String message, Throwable cause) {
//                System.out.println("~~onError~~");
//                System.out.println("useCaseError is " + useCaseError);
//                System.out.println("message is " + message);
//                System.out.println("cause is " + cause);
//            }
//        }, metadata);
    }


    public void config(View view) {
        System.out.println("~~button.config~~");

        //获取纹理容器
        textureView = new TextureView(this);

        //创建预览UseCase
        PreviewConfig previewConfig = new PreviewConfig.Builder().build();//配置预览配置对象
        preview = new Preview(previewConfig);//实例化预览对象
        preview.setOnPreviewOutputUpdateListener(previewOutput -> { //绑定预览监听器
            System.out.println("~~onUpdated~~");
            System.out.println("previewOutput is " + previewOutput);
            System.out.println("getTextureSize is " + previewOutput.getTextureSize());
            System.out.println("getRotationDegrees is " + previewOutput.getRotationDegrees());
            System.out.println("getSurfaceTexture is " + previewOutput.getSurfaceTexture());
            textureView.setSurfaceTexture(previewOutput.getSurfaceTexture());
        });

        //创建抓拍UseCase
        ImageCaptureConfig imageCaptureConfig = new ImageCaptureConfig.Builder()
                .setLensFacing(CameraX.LensFacing.BACK)
                .setTargetRotation(Surface.ROTATION_90)
                .setTargetAspectRatio(new Rational(4, 3))
                .build();//配置抓拍配置对象
        imageCapture = new ImageCapture(imageCaptureConfig);//实例化抓图对象

        CameraX.bindToLifecycle(this, preview, imageCapture);//将预览UseCase和抓拍UseCase绑定到生命周期


        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(textureView);


    }

    public void analyze(View view) {
        System.out.println("~~button.analyze~~");

        //获取纹理容器
        textureView = new TextureView(this);

        //创建预览UseCase
        PreviewConfig previewConfig = new PreviewConfig.Builder().build();//配置预览配置对象
        preview = new Preview(previewConfig);//实例化预览对象
        preview.setOnPreviewOutputUpdateListener(previewOutput -> { //绑定预览监听器
            System.out.println("~~onUpdated~~");
            System.out.println("previewOutput is " + previewOutput);
            System.out.println("getTextureSize is " + previewOutput.getTextureSize());
            System.out.println("getRotationDegrees is " + previewOutput.getRotationDegrees());
            System.out.println("getSurfaceTexture is " + previewOutput.getSurfaceTexture());
            textureView.setSurfaceTexture(previewOutput.getSurfaceTexture());
        });

        //图片分析
        ImageAnalysisConfig config =
                new ImageAnalysisConfig.Builder()
//                        .setTargetResolution(new Size(1280, 720))
                        .build();

        imageAnalysis = new ImageAnalysis(config);


        imageAnalysis.setAnalyzer(
                new ImageAnalysis.Analyzer() {
                    @Override
                    public void analyze(ImageProxy image, int rotationDegrees) {
                        System.out.println("~~analyze~~");
                        System.out.println("rotationDegrees is " + rotationDegrees);
                        System.out.println("image is " + image);

                        System.out.println("----ImageProxy-----");
                        System.out.println("getCropRect is " + image.getCropRect());
                        System.out.println("getHeight is " + image.getHeight());
                        System.out.println("getWidth is " + image.getWidth());
                        System.out.println("getImageInfo is " + image.getImageInfo());
                        imageFormat(image.getFormat());
                        System.out.println("getPlanes's is " + image.getPlanes().length);
                        System.out.println("getTimestamp is " + image.getTimestamp());
                        for (ImageProxy.PlaneProxy planeProxy : image.getPlanes()) {
                            System.out.println("...plane...");
                            System.out.println("BufferSize is  " + planeProxy.getBuffer().capacity());
                            System.out.println("getRowStride() is  " + planeProxy.getRowStride());
                            System.out.println("getPixelStride() is  " + planeProxy.getPixelStride());
                        }

                        System.out.println("----Image-----");
                        System.out.println("getImage is " + image.getImage());
                        Image img = image.getImage();

                        System.out.println("getCropRect is " + img.getCropRect());
                        System.out.println("getHeight is " + img.getHeight());
                        System.out.println("getWidth is " + img.getWidth());
//                        System.out.println("getHardwareBuffer is " + img.getHardwareBuffer()); //API 28可用
                        imageFormat(img.getFormat());
                        System.out.println("getPlanes'size is " + img.getPlanes().length);
                        System.out.println("getTimestamp is " + img.getTimestamp());
                        for (Image.Plane plane : img.getPlanes()) {
                            System.out.println("...plane...");
                            System.out.println("BufferSize is  " + plane.getBuffer().capacity());
                            System.out.println("getRowStride is  " + plane.getRowStride());
                            System.out.println("getPixelStride is  " + plane.getPixelStride());
                        }

                        image.close();
                        imageAnalysis.removeAnalyzer();
                    }
                });

        CameraX.bindToLifecycle(this, imageAnalysis, preview);
        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(textureView);

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
        imageAnalysis.removeAnalyzer();
    }

    public void init(View view) {
        System.out.println("~~button.init~~");
        System.out.println(Thread.currentThread());
    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
}
