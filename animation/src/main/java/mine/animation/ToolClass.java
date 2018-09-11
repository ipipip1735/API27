package mine.animation;

import android.os.Process;
import android.util.Log;

/**
 * Created by Administrator on 2017/4/18.
 */

public class ToolClass {


    static public void showThread() {
        long id =Thread.currentThread().getId();
        System.out.println("**********************thread'id is "+id+"!**********************");
    }

    static public void sout(String string) {
        System.out.println("*****************************************");
        System.out.println(string);
        System.out.println("******************************************");
    }

    static public void sleep(long time) {
        try {
            Thread.currentThread().sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static public void log(Object o) {

        Log.i("myLog", o.toString());
    }

    static public void showProcess() {

        System.out.println("process is " + Process.myPid());
        System.out.println("thread is " + Thread.currentThread().getId());
        System.out.println("calling thread is " + Process.myTid());
        System.out.println("my Uid is " + Process.myUid());
        System.out.println("my User Handle is " + Process.myUserHandle());
    }





}
