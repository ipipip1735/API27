package mine.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static java.lang.StrictMath.max;

/**
 * Created by Administrator on 2018/7/20.
 */

public class CustomView extends View {

    int direction;
    float x, y;

    public void setLabelYY(String labelYY) {
        this.labelYY = labelYY;
    }

    String labelYY;

    public void setDegree(float degree) {
        this.degree = degree;
        invalidate();
    }

    public void setXY(float x, float y) {
        this.x = x;
        this.y = y;
        invalidate();
    }

    private float degree;


    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        System.out.println("+++ " + this.getClass().getSimpleName() + ".CustomView +++");
        System.out.println(attrs);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CustomView);
        direction = array.getInt(R.styleable.CustomView_direction, 0);
        array.recycle();

//        for (int i = 0; i < attrs.getAttributeCount(); i++) {
//            System.out.println("AttributeName is " + attrs.getAttributeName(i));
//            System.out.println("AttributeNameResource is " + attrs.getAttributeNameResource(0));
//        }

//        System.out.println(direction);

//        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.direction);
//
//        Object direction = array.getString(R.styleable.direction_dir);
//        System.out.println(direction);
//        array.recycle();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onMeasure ...");
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int width, height, widthMode, heightMode;
//
//        widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        width = MeasureSpec.getSize(widthMeasureSpec);
//        System.out.println("width is " + width + ", widthMode is " + widthMode);
//        heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        height = MeasureSpec.getSize(heightMeasureSpec);
//        System.out.println("height is " + height + ", heightMode is " + heightMode);

        setMeasuredDimension(500, 601);
//        System.out.println("my height is " + getMeasuredHeight());

        Object o = getLayoutParams();
        System.out.println(o);


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onLayout ...");
        super.onLayout(changed, left, top, right, bottom);

//        int height, width, align;
//        width = getLayoutParams().width;
//        height = getLayoutParams().height;
//        align = ((CustomViewGroup.CVGLayoutParams)getLayoutParams()).align;
//        System.out.println("align is " + align);


////        System.out.print("changed=" + changed);
////        System.out.print(", left=" + left);
////        System.out.print(", top=" + top);
////        System.out.print(", right=" + right);
////        System.out.println(", bottom=" + bottom);

//        right = width >= right ? right : width;
//        bottom = height >= bottom ? bottom : height;


//        setLeft(left);
//        setTop(top);
//        setRight(right);
//        setBottom(bottom);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        System.out.println("... " + this.getClass().getSimpleName() + ".onDraw ...");
        super.onDraw(canvas);

//        System.out.println("canvas'Height is " + canvas.getHeight());
//        System.out.println("canvas'Width is " + canvas.getWidth());
//        System.out.println("canvas'MaximumBitmapHeight is " + canvas.getMaximumBitmapHeight());
//        System.out.println("canvas'MaximumBitmapHeight is " + canvas.getMaximumBitmapHeight());

        Paint paint = new Paint();


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.w1);

//        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
//        Bitmap bitmap = bitmapDrawable.getBitmap();


//        int w = 100, h = 100;
//        Drawable drawable = getResources().getDrawable(R.drawable.w1, null);
//        int w = drawable.getIntrinsicWidth();
//        int h = drawable.getIntrinsicHeight();
//        System.out.println("w is " + w + ", h is " +h);
//
//        Bitmap.Config config = Bitmap.Config.ARGB_8888;
//        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
//
//        Canvas c = new Canvas(bitmap);
//        c.drawCircle(w/2, h/2, max(w, h)-0.5f, paint);

//        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);


//        canvas.drawBitmap(bitmap, 0, 0, paint);


        canvas.drawColor(getResources().getColor(R.color.MediumOrchid, null));

        canvas.save();
        canvas.scale(1f, 0.5f);
        canvas.rotate(degree);
        canvas.translate(0, 3);
//        canvas.skew(0, degree);
        paint.setColor(getResources().getColor(R.color.Ivory, null));
        canvas.drawCircle(250, 200, 100, paint);


//        canvas.restore();
//        canvas.scale(1f, 1.5f);
//        canvas.rotate(-30);
//        canvas.translate(-160, 0);
//        paint.setColor(getResources().getColor(R.color.PERU, null));
//        canvas.drawCircle(350, 400, 100, paint);


//        Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher_background, null);
//        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), new Bitmap());

//        canvas.setBitmap();


    }
}
