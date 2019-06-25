package mine.camerax;


import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraManager;
import android.media.Image;
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

public class Camera2Activity extends AppCompatActivity {

    private ImageCapture imageCapture;
    private Preview preview;
    private ImageAnalysis imageAnalysis;
    private TextureView textureView;
    private int degree = 0;

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

        CameraManager cameraManager = getSystemServiceName(CameraManager.class);






    }


    public void change(View view) {
        System.out.println("~~button.change~~");


    }


    public void take(View view) {
        System.out.println("~~button.take~~");

    }


    public void config(View view) {
        System.out.println("~~button.config~~");



    }

    public void analyze(View view) {
        System.out.println("~~button.analyze~~");


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
