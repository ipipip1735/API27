package mine.drags;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Set;

/**
 * Created by Administrator on 2016/5/4.
 */
public class ExampleActivity extends AppCompatActivity {

    private ImageView imageView;
    private ImageView targetImageView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        System.out.println("*******  Example  onCreateMenu!  *********");
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        System.out.println("*******  Example  onPrepareMenu!!!  *********");
        return super.onPrepareOptionsMenu(menu);
    }


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

    @Override
    protected void onNewIntent(Intent intent) {
        System.out.println("****  Example  onNewIntent  *****");
        super.onNewIntent(intent);
    }


    @Override
    protected void onStart() {
        System.out.println("****Example  onStart*****");
        super.onStart();

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("****  Example  onRestoreInstanceState  *****");
        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    protected void onRestart() {
        System.out.println("****  Example  onRestart  *****");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        System.out.println("****  Example  onResume  ***");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("****  Example  onPause  ***");
        super.onPause();
    }


    @Override
    public void onBackPressed() {
        System.out.println("********  Example  onBackPressed  **********");
        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("****  Example  onStop  *****");
        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("****  Example   onSaveInstanceState  *****");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("****  Example  onDestroy  *****");
        super.onDestroy();
    }


// button callback
    public void start(View view) {
        System.out.println("######  start  ######");
        bindLongClick(this.imageView);
        bindLongClick(this.targetImageView);


    }

    public void stop(View view) {
        System.out.println("######  stop  ######");
        bindDrag(this.imageView);
        bindDrag(this.targetImageView);
    }


// drag event
    private void startDrag(ImageView imageView) {
        System.out.println("######  startDrag  ######");
        View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageView);

        ClipData.Item item = new ClipData.Item("myImage");
        String[] MIME = {"AA", "BB"};
        ClipData dragData = new ClipData("Drag", MIME, item);

        imageView.startDrag(dragData, myShadow, null, 0);

    }


    private void bindLongClick(View imageView) {

        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                System.out.println("*****  onLongClick  ******");
                ImageView imageView = (ImageView) view;
                System.out.println(imageView);
                startDrag(imageView);
                return false;
            }
        });

    }


    private void bindDrag(final View imageView) {

        imageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                System.out.println("*****  onDrag  ******");
                // Defines a variable to store the action type for the incoming event
                final int action = dragEvent.getAction();

                System.out.println(imageView.toString() + " |  " + action);


                // Handles each of the expected events
                switch(action) {

                    case DragEvent.ACTION_DRAG_STARTED:
                        System.out.println("---->>  DRAG_started  <<----");
                            return true;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        System.out.println("---->>  DRAG_entered  <<----");
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        System.out.println("---->>  DRAG_location  <<----");
                        return true;

                    case DragEvent.ACTION_DRAG_EXITED:
                        System.out.println("---->>  DRAG_exited  <<----");
                        return true;

                    case DragEvent.ACTION_DROP:
                        System.out.println("---->>  DRAG_drop  <<----");
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        System.out.println("---->>  DRAG_ended  <<----");
                        return true;

                    default:
                        System.out.println("---->>  DRAG_default  <<----");
                        break;
                }

                return false;
            }
        });


    }




}


