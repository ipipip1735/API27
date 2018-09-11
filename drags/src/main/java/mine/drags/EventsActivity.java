package mine.drags;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Set;

/**
 * Created by Administrator on 2016/5/4.
 */
public class EventsActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView targetImageView;


    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.out.println("**********  Example  onCreate  ***********");

        setContentView(R.layout.activity_example);
        if (bundle == null) {
            System.out.println("Example bundle is empty");
        } else {
            System.out.println("bundle's total is " + bundle.size());
            Set<String> keySet = bundle.keySet();
            for (String i : keySet) {
                System.out.println(i);
            }
        }


        this.imageView = (ImageView) findViewById(R.id.imageView);
        this.targetImageView = (ImageView) findViewById(R.id.targetImageView);


    }


    // button callback
    public void start(View view) {
        System.out.println("######  start  ######");
        init();
    }

    public void stop(View view) {
        System.out.println("######  stop  ######");

    }


// drag event

    private void init() {
        this.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println(">>>>> imageView.onTouch <<<<<");
                System.out.println(v);
                System.out.println(event);
                if (event.getAction() == event.ACTION_UP) {
                    System.out.println("ACTION_UP");
                }
                return true;
//                return false;
            }
        });

        this.targetImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                System.out.println(">>>>> targetImageView.onTouch <<<<<");
                System.out.println(v);
                System.out.println(event);
                if (event.getAction() == event.ACTION_UP) {
                    System.out.println("ACTION_UP");
                }
                return true;
//                return false;
            }
        });
    }


}


