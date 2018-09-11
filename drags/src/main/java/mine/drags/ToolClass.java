package mine.drags;

import android.util.Log;

/**
 * Created by Administrator on 2016/5/9.
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
}
