package mine.apptemp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;


public class WeakReferenceActivity extends AppCompatActivity {

    WeakReference<Runnable> refA;
    WeakReference<Runnable> refB;
    WeakReference<Runnable> refC;
    WeakReference<Runnable> refD;
    WeakReference<Runnable> refE;
    WeakReference<Runnable> refF;
    String tag = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("**********  Client.onStart  ***********");
        setContentView(R.layout.activity_client);

        Runnable a = new Runnable() {
            @Override
            public void run() {
                System.out.println("~~run()~~");
            }
        };
        Runnable b = new Runnable() {
            @Override
            public void run() {
                System.out.println(tag + "|~~run()~~");
                System.out.println("this = " + WeakReferenceActivity.this);
            }
        };
        refA = new WeakReference<>(a);
        refB = new WeakReference<>(b);


        Runnable c = () -> System.out.println("|~~run()~~");
        Runnable d = () -> System.out.println(tag + "|~~run()~~");
        refC = new WeakReference<>(c);
        refD = new WeakReference<>(d);


        Runnable e = this::go;
        Runnable f = this::come;
        refE = new WeakReference<>(e);
        refF = new WeakReference<>(f);

        System.out.println("refA.get() = " + refA.get());
        System.out.println("refB.get() = " + refB.get());
        System.out.println("refC.get() = " + refC.get());
        System.out.println("refD.get() = " + refD.get());
        System.out.println("refE.get() = " + refE.get());
        System.out.println("refF.get() = " + refF.get());
    }


    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("**********  Client.onStart  ***********");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("**********  Client.onRestart  ***********");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("**********  Client.onResume  ***********");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("**********  Client.onPause  ***********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("**********  Client.onBackPressed  ***********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("**********  Client.onStop  ***********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("**********  Client.onSaveInstanceState  ***********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("**********  Client.onDestroy  ***********");
    }


    public void start(View view) {
        System.out.println("~~start~~");
        System.out.println("refA.get() = " + refA.get());
        System.out.println("refB.get() = " + refB.get());
        System.out.println("refC.get() = " + refC.get());
        System.out.println("refD.get() = " + refD.get());
        System.out.println("refE.get() = " + refE.get());
        System.out.println("refF.get() = " + refF.get());
    }


    public void stop(View view) {
        System.out.println("~~stop~~");
        Runtime.getRuntime().gc();
    }

    private void come() {
        System.out.println("~~come()~~");
    }

    private void go() {
        System.out.println(tag + "|~~go()~~");
    }


}