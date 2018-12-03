package mine.apptemp;

import android.content.Context;

import java.util.Objects;

/**
 * Created by Administrator on 2018/11/13.
 */
public class Singleton {
    private String name = "null";
    private static Singleton singleton;
    private static Context mCtx;

    public Singleton(String name) {
        System.out.println("~~Singleton Constructor~~");
        this.name = name;
    }

    static Singleton getThis(String name, Context context) {
        if (Objects.nonNull(singleton)) return singleton;
        singleton = new Singleton(name);
        mCtx = context;
        return singleton;
    }

    @Override
    public String toString() {
        return "Singleton{" +
                "name='" + name + '\'' +
                '}';
    }
}
