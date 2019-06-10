package mine.camerax;


import android.os.Bundle;
import android.view.TextureView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.lifecycle.LifecycleOwner;

public class MainActivity extends AppCompatActivity {

    private ImageCapture imageCapture;
    private Preview preview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_main);





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


    public void start(View view) {
        System.out.println("~~button.start~~");

        PreviewConfig config = new PreviewConfig.Builder().build();
        Preview preview = new Preview(config);

        final TextureView textureView = findViewById(R.id.textureView);

        preview.setOnPreviewOutputUpdateListener(new Preview.OnPreviewOutputUpdateListener() {
            @Override
            public void onUpdated(Preview.PreviewOutput previewOutput) {
                System.out.println("~~onUpdated~~");
                System.out.println("previewOutput is " + previewOutput);
                textureView.setSurfaceTexture(previewOutput.getSurfaceTexture());
            }
        });

        CameraX.bindToLifecycle((LifecycleOwner) this, preview);
    }


    public void capture(View view) {
        System.out.println("~~button.capture~~");

        //获取纹理容器
        TextureView textureView = findViewById(R.id.textureView);

        //创建预览UseCase
        PreviewConfig previewConfig = new PreviewConfig.Builder().build();//配置预览配置对象
        preview = new Preview(previewConfig);//实例化预览对象
        preview.setOnPreviewOutputUpdateListener(previewOutput -> { //绑定预览监听器
            System.out.println("~~onUpdated~~");
            System.out.println("previewOutput is " + previewOutput);
            textureView.setSurfaceTexture(previewOutput.getSurfaceTexture());
        });

        //创建抓拍UseCase
        ImageCaptureConfig imageCaptureConfig = new ImageCaptureConfig.Builder().build();//配置抓拍配置对象
        imageCapture = new ImageCapture(imageCaptureConfig);//实例化抓图对象

        CameraX.bindToLifecycle((LifecycleOwner) this, preview, imageCapture);//将预览UseCase和抓拍UseCase绑定到生命周期

    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

    }

    public void unbind(View view) {
        System.out.println("~~button.unbind~~");

    }

    public void reloading(View view) {
        System.out.println("~~button.reloading~~");

    }


    public void del(View view) {
        System.out.println("~~button.del~~");

    }


    public void query(View view) {
        System.out.println("~~button.query~~");

    }
}
