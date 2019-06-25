package mine.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MatrixActivity extends AppCompatActivity {
    ImageView image;
    int degree = 0;
    float scale = 1f;
    float skew = 1f;
    int width, height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  " + getClass().getSimpleName() + ".onStart  **********");
        setContentView(R.layout.activity_main);

        image = new ImageView(this);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.w1);
        image.setImageBitmap(bitmap);
        image.setBackgroundColor(Color.parseColor("#abcdef"));


        ViewGroup viewGroup = findViewById(R.id.fl);
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        int factory = 2;
        height = bitmap.getHeight();
        width = bitmap.getWidth();
        layoutParams.height = height * factory;
        layoutParams.width = width * factory;
        viewGroup.setLayoutParams(layoutParams);
        viewGroup.addView(image);

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

        set();

    }

    private void set() {
        System.out.println("~~button.start.set~~");

        Matrix matrix = new Matrix();

        //旋转矩阵
//        matrix.setRotate(degree+=10);
//        matrix.setRotate(degree+=10, width / 2, height / 2);


        //缩放矩阵
//        matrix.setScale(1, scale += 1.5f);
//        matrix.setScale(1, scale += 0.1f, width / 2, height / 2);


        //移动矩阵
//        matrix.setTranslate(0, 100);


        //斜切
//        matrix.setSkew(1, skew += 1.5f);
//        matrix.setSkew(1, skew += 0.1f, width / 2, height / 2);


        //矩阵相乘
        float[] f1 = {1f, 2f, 0f, 3f, 4f, 0f, 0f, 0f, 0f};
        float[] f2 = {1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f};

        Matrix m = new Matrix();
        Matrix m1 = new Matrix();
        Matrix m2 = new Matrix();
        m1.setValues(f1);
        m2.setValues(f2);


        m.setConcat(m1, m2);
        System.out.println(m);


        image.setScaleType(ImageView.ScaleType.MATRIX);
        image.setImageMatrix(matrix);

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
