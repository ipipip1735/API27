package mine.camerax;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.provider.MediaStore.Video.Thumbnails.MINI_KIND;

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
            Bitmap imageBitmap = (Bitmap) extras.get("data");//获取缩略图对应的Bitmap
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(imageBitmap);
            ViewGroup viewGroup = findViewById(R.id.fl);
            viewGroup.addView(imageView);
        }


        //方式二：保存到文件
        if (requestCode == SAVE && resultCode == RESULT_OK) {

            if (image.exists()) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(image);
                    System.out.println("size is " + fileInputStream.available());//获取文件尺寸
                    if (fileInputStream.available() == 0) return;

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            ViewGroup viewGroup = findViewById(R.id.fl);
            ImageView imageView = new ImageView(this);
            viewGroup.addView(imageView);

            //设置imageView尺寸
            int targetW = 250;
            int targetH = 500;

            //获取Bitmap尺寸
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            bmOptions.inJustDecodeBounds = true; //仅解析图片信息，而不解析像素数据
            int photoW = bmOptions.outWidth; //获取尺寸
            int photoH = bmOptions.outHeight;

            //计算采样因子
            int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

            bmOptions.inJustDecodeBounds = false;//解析图片信息和像素数据
            bmOptions.inSampleSize = scaleFactor;//设置采用因子
            bmOptions.inPurgeable = true;//设置采用因子

            //解析图片
            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);


            imageView.setImageBitmap(bitmap);

        }

        //方式二：录像
        if (requestCode == VIDEO && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();//获取URI
            System.out.println("uri is " + videoUri);
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

            //不需要授权，启用摄像头就已经授权了，使用FileProvider仅仅为了让URI映射实际地址
//            takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //授予临时读权限
//            takePictureIntent.setFlags(FLAG_GRANT_WRITE_URI_PERMISSION); //授予临时写权限

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI); //保存到文件
            startActivityForResult(takePictureIntent, SAVE); //启动摄像头
        }
    }

    public void gallery(View view) {
        System.out.println("~~button.gallery~~");

        //将图片公开到相册
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//创建媒体扫描器对应的Intent
//        Uri uri = Uri.fromFile(image);
//        System.out.println("uris is " + uri);
//        mediaScanIntent.setData(uri);
//        this.sendBroadcast(mediaScanIntent);


//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//创建媒体扫描器对应的Intent
//        uri = FileProvider.getUriForFile(this, "TNT", image);
//        System.out.println("uris is " + uri);
//        mediaScanIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //授予临时读权限
//        mediaScanIntent.setFlags(FLAG_GRANT_WRITE_URI_PERMISSION); //授予临时写权限
//        mediaScanIntent.setData(uri);
//        this.sendBroadcast(mediaScanIntent);


        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.TITLE, "TTTTile");
        contentValues.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "description XXX");
        Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        System.out.println("uri is " + uri);
        if (uri == null) return;

        try (OutputStream outputStream = getContentResolver().openOutputStream(uri);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
             InputStream inputStream = new FileInputStream("/storage/emulated/0/Android/data/mine.camerax/files/Pictures/images/JPEG_20200813_000616_8583340753083822908.jpg");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream)) {

            byte[] bytes = new byte[1024];
            int n = 0;
            while ((n = bufferedInputStream.read(bytes)) != -1)
                bufferedOutputStream.write(bytes, 0, n);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;


    }


    public void query(View view) {
        System.out.println("~~button.query~~");

//        Uri uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        System.out.println("uri is " + uri);
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);

        System.out.println(cursor.getCount());

        while (cursor.moveToNext()) {
            for (String name : cursor.getColumnNames()) {
                System.out.println("name is " + name);
                System.out.println(name + " is " + cursor.getString(cursor.getColumnIndex(name)));
            }
        }
        cursor.close();

    }
}
