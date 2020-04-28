package mine.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  **********");
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  **********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestoreInstanceState  **********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  " + getClass().getSimpleName() + ".onRestart  **********");
        getLoaderManager().enableDebugLogging(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  " + getClass().getSimpleName() + ".onResume  **********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  " + getClass().getSimpleName() + ".onPause  **********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  " + getClass().getSimpleName() + ".onBackPressed  **********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  " + getClass().getSimpleName() + ".onStop  **********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onSaveInstanceState  **********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  " + getClass().getSimpleName() + ".onDestroy  **********");
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

//        file();//使用文件创建位图
//        copyPixels();
        byteArray();//使用字节数组创建位图
//        stream();//使用流创建位图
//        resource();//使用资源ID创建位图

    }

    private void copyPixels() {

        //提取像素数据
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.w1);
        int size = bitmap.getByteCount();
        ByteBuffer buf = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(buf);//提取像素数据
        byte[] byteArray = buf.array();
        System.out.println(byteArray.length);


        //填充像素
        int w = bitmap.getWidth();//尺寸必须一致，否则ImageView将自动拉伸
        int h = bitmap.getHeight();
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);//创建空Bitmap
//        Bitmap bm = Bitmap.createBitmap(w/2, h/2, Bitmap.Config.ARGB_8888);//创建空Bitmap
//        Bitmap bm = Bitmap.createBitmap(w-50, h, Bitmap.Config.ARGB_8888);//创建空Bitmap
        bm.copyPixelsFromBuffer(ByteBuffer.wrap(byteArray));//填充像素


        ImageView image = new ImageView(this);
        image.setImageBitmap(bm);
        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(image);
    }

    private void resource() {
        ImageView image = new ImageView(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.w1);
        image.setImageBitmap(bitmap);


        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(image);
    }


    private void stream() {
        try {
            File file = new File(getCacheDir(), "w1.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);

            ImageView image = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeStream(fileInputStream);
            image.setImageBitmap(bitmap);


            ViewGroup viewGroup = findViewById(R.id.fl);
            viewGroup.addView(image);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void byteArray() {
        try {
            File file = new File(getCacheDir(), "w1.jpg");
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            byte[] bytes = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(bytes);

            ImageView image = new ImageView(this);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            image.setImageBitmap(bitmap);


            ViewGroup viewGroup = findViewById(R.id.fl);
            viewGroup.addView(image);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void file() {
        ImageView image = new ImageView(this);
        File file = new File(getCacheDir(), "w1.jpg");
        Bitmap bMap = BitmapFactory.decodeFile(file.toString());
        image.setImageBitmap(bMap);


        ViewGroup viewGroup = findViewById(R.id.fl);
        viewGroup.addView(image);
    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        float widownWidth = 1.0f;

        ImageView image = new ImageView(this);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;


        BitmapFactory.decodeResource(getResources(), R.drawable.w1, options);
        float width = options.outWidth;
        float height = options.outHeight;
        System.out.println("width=" + width + ", height=" + height);

        options.inSampleSize = 4;
        options.inJustDecodeBounds = false;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.w1, options);
        width = options.outWidth;
        height = options.outHeight;
        System.out.println("width=" + width + ", height=" + height);


//        image.setImageBitmap(bitmap);

//        ViewGroup viewGroup = findViewById(R.id.fl);
//        viewGroup.addView(image);

    }


    public void bind(View view) {
        System.out.println("~~button.bind~~");


//        try {
//            //方法一：写入到文件流
//            File file = new File(getCacheDir(), "ssdf.jpg");
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.w1, options);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }


        //方法二：写入到字节流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.w1, options);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();

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
