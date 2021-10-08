package mine.view;

import static android.graphics.PixelFormat.RGBA_8888;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Region;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;

/**
 * Created by Administrator on 2021/10/8.
 */

public class TextureViewActivity extends AppCompatActivity {
    final Random random = new Random();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_texture);

        TextureView textureView = findViewById(R.id.textureView);
        textureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                System.out.println("~~" + getClass().getSimpleName() + ".onSurfaceTextureAvailable~~");
                System.out.println("surface = " + surface + ", width = " + width + ", height = " + height);

            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
                System.out.println("~~" + getClass().getSimpleName() + ".onSurfaceTextureSizeChanged~~");
                System.out.println("surface = " + surface + ", width = " + width + ", height = " + height);
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                System.out.println("~~" + getClass().getSimpleName() + ".onSurfaceTextureDestroyed~~");
                System.out.println("surface = " + surface);
                return true;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
                System.out.println("~~" + getClass().getSimpleName() + ".onSurfaceTextureUpdated~~");
                System.out.println("surface = " + surface);
            }
        });


    }


    public void start(View view) {
        System.out.println("*********  button.start  *********");

        TextureView textureView = findViewById(R.id.textureView);

        Paint p = new Paint();
        p.setColor(getResources().getColor(R.color.AliceBlue, null));


        Canvas canvas = textureView.lockCanvas();
        System.out.println("canvas.isHardwareAccelerated() = " + canvas.isHardwareAccelerated() + ", textureView.isHardwareAccelerated() = " + textureView.isHardwareAccelerated());
        canvas.drawARGB(random.nextInt(255), random.nextInt(255), random.nextInt(255), random.nextInt(255));
        canvas.drawCircle(100f, 100f, 100f, p);


        textureView.unlockCanvasAndPost(canvas);


    }

    public void stop(View view) {
        System.out.println("*********  button.stop  *********");

        TextureView textureView = findViewById(R.id.textureView);
        ViewGroup.LayoutParams layoutParams = textureView.getLayoutParams();
        layoutParams.height += 100;
        textureView.setLayoutParams(layoutParams);
    }

    public void rect(View view) {
        System.out.println("*********  button.rect  *********");
        TextureView textureView = findViewById(R.id.textureView);


        //设置透明度
//        textureView.setAlpha(0.5f);

        //播放动画
//        textureView.animate()
//                .x(350f)
//                .setDuration(3000L)
//                .start();

    }
}




