package mine.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
    }



    public void start(View view) {
        System.out.println("********start******");
        drawPic();

    }

    public void stop(View view) {
        System.out.println("********stop******");
        drawText();

    }

//        TextPaint textPaint = new TextPaint();

    private void drawText() {
        Bitmap bitmap = Bitmap.createBitmap(600, 600, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();


        paint.setColor(Color.YELLOW);
        paint.setTextSize(100);
        canvas.drawText("kkk", 300,300, paint);

        canvas.rotate(30);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);


//        Bitmap bitmap = Bitmap.createBitmap(600, 600, Bitmap.Config.ARGB_8888);
//        Paint paint = new Paint();
//        Canvas c = new Canvas(bitmap);
//        paint.setColor(Color.YELLOW);
//        paint.setTextSize(50);
//        c.drawText("Some Text", 10, 25, paint);


    }




    private void drawPic() {

//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
//        linearLayout.setBackgroundColor(Color.blue(R.color.colorPrimary));

//        System.out.println("oooooo" + linearLayout.getWidth());
//        System.out.println("oooooo" + linearLayout.getHeight());

//        ViewGroup.LayoutParams params = linearLayout.getLayoutParams();
//        params.height = 100;
//        linearLayout.setLayoutParams(params);

        Bitmap bitmap = Bitmap.createBitmap(600, 600, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        Canvas c = new Canvas(bitmap);
        paint.setColor(Color.YELLOW);
        paint.setTextSize(50);
        c.drawText("Some Text", 10, 25, paint);

//        paint.setColor(getResources().getColor(R.color.colorAccent, null));


//        c.drawColor(Color.BLACK);
//        c.drawRect(0, 0, 40, 20, paint);
//
//        c.drawText("ok", 2, 2, paint);
//        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);



//        c.drawPaint(paint);



        ImageView imageView = (ImageView) findViewById(R.id.imageView);
//        imageView.setImageDrawable(bitmapDrawable);
        imageView.setImageBitmap(bitmap);
//        imageView.setScaleX(2);




//        TextView textView = new TextView(this);
//        textView.setText("ok");
//        linearLayout.addView(textView);




//        System.out.println(imageView.getX());
//        System.out.println(imageView.getY());
//        System.out.println(imageView.getScaleX());
//        System.out.println(imageView.getScaleY());



    }

}


