package mine.camerax;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.DIRECTORY_PICTURES;

public class CameraActivity extends AppCompatActivity {

    static int SHOW = 1;
    static int SAVE = 2;
    static int SAVE_VIDEO = 21;
    static int VIDEO = 3;
    static int PIC_CROP = 4;
    private File image, video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_camera);


        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onActivityResult  *********");
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("requestCode = " + requestCode + ", resultCode = " + resultCode + ", data = " + data);
//        System.out.println(data.getSerializableExtra("data"));
//        System.out.println(data.getExtras());


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

        //方式三：保存录像
        if (requestCode == SAVE_VIDEO && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();//获取URI
            System.out.println("uri is " + videoUri);
//            VideoView videoView = new VideoView(this);
//            videoView.setVideoURI(videoUri);//绑定URI
//            videoView.setMediaController(new MediaController(this));//绑定控制器
//            ViewGroup viewGroup = findViewById(R.id.fl);
//            viewGroup.addView(videoView);
        }

        //裁剪图片
        if (requestCode == PIC_CROP && resultCode == RESULT_OK) {
            Uri uri = data.getData();//获取URI
            System.out.println("uri is " + uri);
            System.out.println("type is " + data.getType());
            System.out.println("Extras is " + data.getParcelableExtra("cropped-rect"));
            System.out.println("Extras is " + data.getParcelableExtra("data"));

            try {
                if(uri != null)System.out.println("uri is " + getContentResolver().openInputStream(uri).available());
            } catch (IOException e) {
                e.printStackTrace();
            }
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


//        savePic();
        crop();
//        saveVideo();
    }

    private void crop() {

        //方式一：输入输出使用相同Uri
//        try {
//            File image = new File(getCacheDir(), "cross.png");
//            System.out.println("image is " + new FileInputStream(image).available());
//
//            Uri photoUri = FileProvider.getUriForFile(this, "TNT", image);
//            System.out.println("photoUri is " + getContentResolver().openInputStream(photoUri));
//
//
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            cropIntent.setDataAndType(photoUri, "image/*");
//
//            cropIntent.putExtra("crop", true);
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            cropIntent.putExtra("outputX", 50);
//            cropIntent.putExtra("outputY", 50);
//            cropIntent.putExtra("return-data", true);
//            cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
//            cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);
//            startActivityForResult(cropIntent, PIC_CROP);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //方式二：使用不同Uri
//        try {
//            File image = new File(getCacheDir(), "cross.png");
//            System.out.println("image is " + new FileInputStream(image).available());
//
//            Uri inUri = FileProvider.getUriForFile(this, "TNT", image);
//            System.out.println("inUri is " + getContentResolver().openInputStream(inUri));
//
//            File cropImage = File.createTempFile("CROP_", ".jpg", Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES));
//            Uri outUri = Uri.parse(cropImage.toURI().toString());
//            System.out.println("outUri is " + getContentResolver().openInputStream(outUri));
//
//
//            Intent cropIntent = new Intent("com.android.camera.action.CROP");
//            cropIntent.setData(inUri);//设置输入Uri
//            cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//            cropIntent.putExtra("crop", true);
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            cropIntent.putExtra("outputX", 50);
//            cropIntent.putExtra("outputY", 50);
//            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);//设置输出Uri
//            startActivityForResult(cropIntent, PIC_CROP);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //方式三：输出Bitmap
        try {
            File image = new File(getCacheDir(), "cross.png");
            System.out.println("image is " + new FileInputStream(image).available());

            Uri inUri = FileProvider.getUriForFile(this, "TNT", image);
            System.out.println("inUri is " + getContentResolver().openInputStream(inUri).available());

            File cropImage = File.createTempFile("CROP_", ".jpg", Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES));
            System.out.println(cropImage.exists() + "|cropImage = " + cropImage);
            Uri outUri = Uri.parse(cropImage.toURI().toString());
            System.out.println("outUri is " + getContentResolver().openInputStream(outUri).available());

//            File cropImage = File.createTempFile("CROP_", ".jpg", getCacheDir());
//            Uri outUri = FileProvider.getUriForFile(this, "TNT", cropImage);
//            System.out.println("outUri is " + getContentResolver().openInputStream(outUri).available());


            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setData(inUri);
            cropIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            cropIntent.putExtra("crop", true);
            cropIntent.putExtra("aspectX", 1);//剪切框比例
            cropIntent.putExtra("aspectY", 1);
//            cropIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG);//如果没有指定输出格式默认为.jpg
            cropIntent.putExtra("outputX", 10);
            cropIntent.putExtra("outputY", 100);
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, outUri);
//            cropIntent.putExtra("set-as-wallpaper", true);//是否设置为壁纸
//            cropIntent.putExtra("spotlightX", 100);//剪切后拉伸尺寸为spotlightX/spotlightY，再应用为壁纸
//            cropIntent.putExtra("spotlightY", 5);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, PIC_CROP);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private void savePic() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(getExternalFilesDir(DIRECTORY_PICTURES), "images");
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


    private void saveVideo() {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "VIDEO_" + timeStamp + "_";
        File storageDir = new File(getExternalFilesDir(Environment.DIRECTORY_MOVIES), "videos");
        boolean b = storageDir.mkdir();//创建目录
        System.out.println(b);


        try {
            //创建临时文件（如果目录不存在，则创建到系统临时目录）
            video = File.createTempFile(
                    imageFileName,  /* prefix */
                    ".mp4",   /* suffix */
                    storageDir      /* directory */
            );
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (video != null) {
            System.out.println("video path is " + video.getAbsolutePath());//打印绝对路径

            Uri videoURI = FileProvider.getUriForFile(this,
                    "TNT",
                    video);
            System.out.println("URI is " + videoURI);//打印路径对应URI

            Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);//创建Intent


            //不需要授权，启用摄像头就已经授权了，使用FileProvider仅仅为了让URI映射实际地址
//            takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //授予临时读权限
//            takePictureIntent.setFlags(FLAG_GRANT_WRITE_URI_PERMISSION); //授予临时写权限

            takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI); //保存到文件
            startActivityForResult(takeVideoIntent, SAVE_VIDEO); //启动摄像头
        }
    }


    public void gallery(View view) {
        System.out.println("~~button.gallery~~");

        //方式一：将图片公开到相册（官方文档上的例子，测试失败了）
//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);//创建媒体扫描器对应的Intent
//        Uri uri = Uri.fromFile(image);
//        System.out.println("uris is " + uri);
//        mediaScanIntent.setData(uri);
//        this.sendBroadcast(mediaScanIntent);


        //方式二：使用工具方法增加图片到相册，并增加对应的缩略图
//        File file = new File("/storage/emulated/0/Android/data/mine.camerax/files/Pictures/images/JPEG_20200813_000616_8583340753083822908.jpg");
//        String uri = "";
//        try {
//            uri = MediaStore.Images.Media.insertImage(getContentResolver(), file.toString(), "ttt", "dddd");
//            System.out.println("uri is " + uri);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        //获取缩略图
//        long id = ContentUris.parseId(Uri.parse(uri));
//        Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), id, MediaStore.Images.Thumbnails.MINI_KIND, null);
//        System.out.println("height = " + bitmap.getHeight() + ", " +
//                "width = " + bitmap.getWidth() + ", " +
//                "size = " + bitmap.getByteCount());
//        //获取微缩图
//        bitmap = MediaStore.Images.Thumbnails.getThumbnail(getContentResolver(), id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
//        System.out.println("height = " + bitmap.getHeight() + ", " +
//                "width = " + bitmap.getWidth() + ", " +
//                "size = " + bitmap.getByteCount());

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
