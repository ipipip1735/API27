package mine.bitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;

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
        image.setScaleType(ImageView.ScaleType.MATRIX);
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

//        set();
//        post();
        map();
//        resource();

//        cala();

    }

    private void map() {


        //平移/缩放变换矩阵
//        Drawable drawable = image.getDrawable();
//        Rect rect = drawable.getBounds();
//
//        RectF rectF = new RectF(rect.left, rect.top, rect.right, rect.bottom);
//        System.out.println(rectF);
//        Matrix m = new Matrix();
//        m.setTranslate(10, 20);//平移变换矩阵
////        m.setScale(0.95f, 1.02f);//缩放变换矩阵
//        m.mapRect(rectF);
//        System.out.println(rectF);
//
//        drawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);


        //旋转变换矩阵（方式一），测试失败
        Drawable drawable = getDrawable(R.drawable.w2);
        Rect rect = drawable.getBounds();
        System.out.println(rect);

//        RectF rectF = new RectF(0, 0, 100, 100);
        RectF rectF = new RectF(rect.left, rect.top, rect.right, rect.bottom);
        System.out.println(rectF);
        Matrix m = new Matrix();
//        m.setRotate(degree+=90, rect.centerX(), rect.centerY());//旋转变换矩阵
        m.setRotate(degree += 30, 100, 100);//旋转变换矩阵
        m.mapRect(rectF);
        System.out.println(rectF);

        //这里是错误的，Bound仅能指定渲染范围，无法改变Bitmap本身像素数据，所以旋转矩阵不能使用此方法，见方式二
        drawable.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
        image.setImageDrawable(drawable);


        //旋转变换矩阵（方式二）
//        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable(R.drawable.w2);
//        Bitmap bitmap = bitmapDrawable.getBitmap();
//        int w = bitmap.getWidth();
//        int h = bitmap.getHeight();
//        System.out.println("h = " + h + ", w = " + w);
//        int iw = bitmapDrawable.getIntrinsicWidth();
//        int ih = bitmapDrawable.getIntrinsicHeight();
//        System.out.println("ih = " + ih + ", iw = " + iw);
//
//        Matrix m = new Matrix();
//        m.setRotate(degree += 10, 0, 0);
//        System.out.println("m = " + m);
//        bitmap = Bitmap.createBitmap(bitmapDrawable.getBitmap(), 0, 0, w, h, m, false);
//        System.out.println("h = " + bitmap.getHeight() + ", w = " + bitmap.getWidth());
//
////        Bitmap bitmap = Bitmap.createBitmap(bitmapDrawable.getBitmap(), 0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight(), m, false);
//        image.setImageDrawable(new BitmapDrawable(getResources(), bitmap));

    }

    private void cala() {
        Matrix m1 = new Matrix();
        m1.setValues(new float[]{1, 0, 0, 1, 1, 3, 3, 1, 0});

        Matrix m2 = new Matrix();
        m2.setValues(new float[]{1, 2, 3, 1, 1, 3, 3, 1, 0});

        Matrix m = new Matrix();
        m.postConcat(m2);
        m.postConcat(m1);
        System.out.println(m);
    }

    private void post() {
        Matrix m = new Matrix();
        System.out.println(m);

        Matrix mT = new Matrix();
        mT.setTranslate(0, 100);

        Matrix mR = new Matrix();
        mR.setRotate(degree += 10);

        Matrix mS = new Matrix();
        mS.setScale(1.5f, 1f);

        m.postConcat(mR);
        m.postConcat(mS);
        m.postConcat(mT);
        System.out.println(m);

//        m.postConcat(mT);
//        m.postConcat(mR);
//        m.postConcat(mS);
//        System.out.println(m);


        image.setScaleType(ImageView.ScaleType.MATRIX);
        image.setImageMatrix(m);
    }

    private void set() {
        System.out.println("~~button.start.set~~");

        Matrix matrix = new Matrix();

        //旋转矩阵
        matrix.setRotate(degree += 10);
//        matrix.setRotate(degree+=10, width / 2, height / 2);

        //缩放矩阵
//        matrix.setScale(1, scale += 1.5f);
//        matrix.setScale(1, scale += 0.1f, width / 2, height / 2);

        //移动矩阵
        matrix.setTranslate(0, degree += 10);

        //斜切
//        matrix.setSkew(1, skew += 1.5f);
//        matrix.setSkew(1, skew += 0.1f, width / 2, height / 2);

        image.setScaleType(ImageView.ScaleType.MATRIX);
        image.setImageMatrix(matrix);


        //矩阵相乘
//        float[] f1 = {1f, 2f, 0f, 3f, 4f, 0f, 0f, 0f, 0f};
//        float[] f2 = {1f, 2f, 3f, 4f, 5f, 6f, 7f, 8f, 9f};
//
//        Matrix m = new Matrix();
//        Matrix m1 = new Matrix();
//        Matrix m2 = new Matrix();
//        m1.setValues(f1);
//        m2.setValues(f2);
//
//        boolean r = m.setConcat(m1, m2); // 将M1 X M2结果赋值给当前矩阵
//        System.out.println("r is " + r);
//        System.out.println("m is " + m);
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


        //方式一
//        int width = 100, height = 100;
//        int[] colors = new int[width * height * 3];
//        Arrays.fill(colors, 255);
//        Bitmap bitmap = Bitmap.createBitmap(colors, width, height, Bitmap.Config.RGB_565);
//        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
//        image.setImageDrawable(bitmapDrawable);

        //方式二
//        Bitmap bitmap = Bitmap.createBitmap(getResources().getDisplayMetrics(), width, height, Bitmap.Config.ARGB_8888, false);
//        bitmap.setHasAlpha(false);
//        Paint paint = new Paint();
//        paint.setARGB(255, 255, 0, 0);
//        Canvas canvas = new Canvas(bitmap);
//        canvas.drawRect(new Rect(0, 0, 100, 100), paint);
//
//        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
//        image.setImageDrawable(bitmapDrawable);
//        System.out.println("displayMetrics is " + getResources().getDisplayMetrics());

        //方式三
        int width = 100, height = 100;
        int[] colors = new int[width * height * 3];
        Arrays.fill(colors, 255);
        Bitmap source = Bitmap.createBitmap(colors, width, height, Bitmap.Config.RGB_565);
        System.out.println("source.isMutable() = " + source.isMutable());

//        Bitmap source = BitmapFactory.decodeResource(getResources(), R.drawable.w1);
        System.out.println("source = " + source);
        Matrix m = new Matrix();
        m.setRotate(30);
        source = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), m, true);
        System.out.println("source = " + source);
        System.out.println("source.isMutable() = " + source.isMutable());
        System.out.println("isHard is " + source.getConfig().equals(Bitmap.Config.HARDWARE));
        image.setImageDrawable(new BitmapDrawable(getResources(), source));

    }


    public void del(View view) {
        System.out.println("~~button.del~~");


    }


    public void query(View view) {
        System.out.println("~~button.query~~");


    }

}
