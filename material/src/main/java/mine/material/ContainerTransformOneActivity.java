package mine.material;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

/**
 * Created by Administrator on 2020/7/15.
 */
public class ContainerTransformOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");

        findViewById(android.R.id.content).setTransitionName("shared");//根元素指定转换名

        long duration = 3000L;

        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());//绑定共享元素入场回调




        MaterialContainerTransform materialContainerTransform = new MaterialContainerTransform();
        materialContainerTransform.setStartShapeAppearanceModel(
                new ShapeAppearanceModel.Builder()
                        .setAllCornerSizes(90f)
                        .build());
        materialContainerTransform.addTarget(android.R.id.content)
                .setDuration(duration);



        getWindow().setSharedElementEnterTransition(materialContainerTransform);//绑定共享元素入场动画
        getWindow().setSharedElementReturnTransition(materialContainerTransform.clone());//绑定共享元素出场动画



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container_transform_one);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");

        super.onRestoreInstanceState(savedInstanceState);
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
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");

        super.onPause();
    }

    @Override
    public void onBackPressed() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");

        super.onBackPressed();
    }


    @Override
    protected void onStop() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");

        super.onStop();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");

        super.onDestroy();
    }


    public void start(View view) {
        System.out.println("~~button.start~~");

    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");
    }

    public void bind(View view) {
        System.out.println("~~button.bind~~");

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
