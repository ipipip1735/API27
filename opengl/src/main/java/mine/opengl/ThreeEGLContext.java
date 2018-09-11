package mine.opengl;

import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;


/**
 * Created by Administrator on 2018/8/18.
 */
public class ThreeEGLContext implements GLSurfaceView.EGLContextFactory {
    private double glVersion = -1;
    private int EGL_CONTEXT_CLIENT_VERSION = 0x3098;

    public ThreeEGLContext(double glVersion) {
        System.out.println("++++++  " + getClass().getSimpleName() + "  ++++++");
        this.glVersion = glVersion;
    }

    @Override
    public EGLContext createContext(EGL10 egl, EGLDisplay display, EGLConfig eglConfig) {
        System.out.println("*********  " + getClass().getSimpleName() + ".createContext  *********");

        System.out.println("egl is " + egl);
        System.out.println("display is " + display);
        System.out.println("eglConfig is " + eglConfig);

        System.out.println("creating OpenGL ES " + glVersion + " context");
        int[] attrib_list = {EGL_CONTEXT_CLIENT_VERSION, (int) glVersion,
                EGL10.EGL_NONE};

        // attempt to create a OpenGL ES 3.0 context
        EGLContext context = egl.eglCreateContext(
                display, eglConfig, EGL10.EGL_NO_CONTEXT, attrib_list);
        return context; // returns null if 3.0 is not supported;
    }

    @Override
    public void destroyContext(EGL10 egl, EGLDisplay display, EGLContext context) {
        System.out.println("*********  " + getClass().getSimpleName() + ".destroyContext  *********");

    }
}
