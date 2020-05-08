package mine.media;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.IOException;

import static android.os.Environment.DIRECTORY_MUSIC;


/**
 * Created by Administrator on 2019/2/1.
 */
public class RecoderAudioActivity extends AppCompatActivity {

    MediaRecorder recorder;
    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onCreate  *********");
        setContentView(R.layout.activity_recorder);
        recorder = new MediaRecorder();


    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStart  *********");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onRestoreInstanceState  *********");
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
        super.onPause();
        System.out.println("*********  " + getClass().getSimpleName() + ".onPause  *********");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.out.println("*********  " + getClass().getSimpleName() + ".onBackPressed  *********");
    }


    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("*********  " + getClass().getSimpleName() + ".onStop  *********");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        System.out.println("*********  " + getClass().getSimpleName() + ".onSaveInstanceState  *********");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("*********  " + getClass().getSimpleName() + ".onDestroy  *********");
        recorder.release();
        recorder = null;
    }


    public void state(View view) {
        System.out.println("~~button.state~~");

//        try {
//            System.out.println("getActiveMicrophones is " + recorder.getActiveMicrophones());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        System.out.println("getAudioSourceMax is " + recorder.getAudioSourceMax());
//        System.out.println("getMaxAmplitude is " + recorder.getMaxAmplitude());
//        System.out.println("getMetrics is " + recorder.getMetrics());


//        System.out.println("getPreferredDevice is " + recorder.getPreferredDevice());
//        System.out.println("getRoutedDevice is " + recorder.getRoutedDevice());
//        System.out.println("getSurface is " + recorder.getSurface());




    }

    public void init(View view) {
        System.out.println("~~button.init~~");


        //initial
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC); //设置音频输入设备


        //DataSourceConfigured
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP); //设置输出格式
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB); //设置编码方式
        File path = new File(getExternalFilesDir(DIRECTORY_MUSIC), "/rc" + n + ".3gp"); //设置输出路径
        System.out.println(path);
        recorder.setOutputFile(path.toString());


        //绑定监听器
//        recorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
//            @Override
//            public void onError(MediaRecorder mr, int what, int extra) {
//                System.out.println("~~onError~~");
//                System.out.println("what is " + what);
//                System.out.println("extra is " + extra);
//            }
//        });
//
//        try {
//            recorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
//                @Override
//                public void onInfo(MediaRecorder mr, int what, int extra) {
//                    System.out.println("~~onInfo~~");
//                    System.out.println("what is " + what);
//                    System.out.println("extra is " + extra);
//
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    public void prepare(View view) {
        System.out.println("~~button.prepare~~");

        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(View view) {
        System.out.println("~~button.start~~");
        recorder.start();   // Recording is now started


    }


    public void stop(View view) {
        System.out.println("~~button.stop~~");

        recorder.stop();


    }

    public void pause(View view) {
        System.out.println("~~button.pause~~");
        recorder.pause();
    }

    public void resume(View view) {
        System.out.println("~~button.resume~~");
        recorder.resume();
    }

    public void reset(View view) {
        System.out.println("~~button.reset~~");
        recorder.reset();   // You can reuse the object by going back to setAudioSource() step

    }


    public void release(View view) {
        System.out.println("~~button.release~~");

        recorder.release(); // Now the object cannot be reused


    }


    public void query(View view) {
        System.out.println("~~button.query~~");


    }

}
