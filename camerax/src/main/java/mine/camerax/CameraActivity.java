package mine.camerax;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraX;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureConfig;
import androidx.camera.core.Preview;
import androidx.camera.core.PreviewConfig;
import androidx.core.content.FileProvider;
import androidx.lifecycle.LifecycleOwner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

public class CameraActivity extends AppCompatActivity {

    static int SHOW = 1;
    static int SAVE = 2;
    static int VIDEO = 3;
    private File image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_camera);


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onActivityResult  *********");
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode is " + requestCode);
        System.out.println("resultCode is " + resultCode); //-1为RESULT_OK
        System.out.println("data is " + data);


        //方式一：直接显示
        if (requestCode == SHOW && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(imageBitmap);
            ViewGroup viewGroup = findViewById(R.id.fl);
            viewGroup.addView(imageView);
        }


        //方式二：保存到文件
        if (requestCode == SAVE && resultCode == RESULT_OK) {


        }

        //方式二：录像
        if (requestCode == VIDEO && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();//获取URI
            VideoView videoView = new VideoView(this);
            videoView.setVideoURI(videoUri);//绑定URI
            videoView.setMediaController(new MediaController(this));//绑定控制器
            ViewGroup viewGroup = findViewById(R.id.fl);
            viewGroup.addView(videoView);
        }


    }

    public void start(View view) {
        System.out.println("~~button.start~~");


        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, SHOW);
        }


    }


    public void save(View view) {
        System.out.println("~~button.save~~");


        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "images");
        boolean b = storageDir.mkdir();//创建目录
        System.out.println(b);


        try {
            //创建临时文件（如果目录不存在，则创建到系统临时目录）
            image = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".jpg",   /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (image != null) {
            System.out.println("image path is " + image.getAbsolutePath());//打印绝对路径

            Uri photoURI = FileProvider.getUriForFile(this,
                    "TNT",
                    image);
            System.out.println("URI is " + photoURI);//打印路径对应URI

            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//创建Intent

            //授权是可选的，估计是使用的外部目录，所以摄像头可以直接访问文件
//            takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //授予临时读权限
//            takePictureIntent.setFlags(FLAG_GRANT_WRITE_URI_PERMISSION); //授予临时写权限

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI); //保存到文件
            startActivityForResult(takePictureIntent, SAVE); //启动摄像头
        }
    }

    public void gallery(View view) {
        System.out.println("~~button.gallery~~");

        //将图片公开到相册
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//创建媒体扫描器对应的Intent
        Uri contentUri = Uri.fromFile(image);
        System.out.println("uris is " + contentUri);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);

    }

    public void video(View view) {
        System.out.println("~~button.video~~");
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, VIDEO);
        }
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
