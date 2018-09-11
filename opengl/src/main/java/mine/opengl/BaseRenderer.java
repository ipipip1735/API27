package mine.opengl;

import android.content.Context;
import android.opengl.GLES31;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


/**
 * Created by Administrator on 2018/8/18.
 */
public class BaseRenderer implements GLSurfaceView.Renderer {

    private TriangleShader triangleShader;
    private int positionID, colorID, modelID, viewID, projectID;
    private IntBuffer arrayID;
    private float angle;

    public BaseRenderer(Context context) {
        System.out.println("++++  " + getClass().getSimpleName() + "  ++++");
        triangleShader = new TriangleShader(context);
        arrayID = IntBuffer.allocate(1);

    }

    private void assignData() {

        GLES31.glGenVertexArrays(1, arrayID);
        GLES31.glBindVertexArray(arrayID.get(0));


        IntBuffer bufferID = IntBuffer.allocate(1);

        float[] triangleCoords = {   // in counterclockwise order:
                0f, 0.5f, 1, 0, 0, // top
                -0.5f, -0.5f, 0, 1, 0, // bottom left
                0.5f, -0.5f, 0, 0, 1 // bottom right
        };


        FloatBuffer vertexBuffer = ByteBuffer.allocateDirect(triangleCoords.length * Float.BYTES)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(triangleCoords).flip();

        GLES31.glGenBuffers(1, bufferID);
        GLES31.glBindBuffer(GLES31.GL_ARRAY_BUFFER, bufferID.get(0));
        GLES31.glBufferData(GLES31.GL_ARRAY_BUFFER, vertexBuffer.limit() * Float.BYTES, vertexBuffer, GLES31.GL_STATIC_DRAW);


        GLES31.glEnableVertexAttribArray(positionID);
        GLES31.glVertexAttribPointer(positionID, 2, GLES31.GL_FLOAT, false, Float.BYTES * 5, 0);

        GLES31.glEnableVertexAttribArray(colorID);
        GLES31.glVertexAttribPointer(colorID, 3, GLES31.GL_FLOAT, false, Float.BYTES * 5, Float.BYTES * 2);


        GLES31.glBindVertexArray(0);
        GLES31.glBindBuffer(GLES31.GL_ARRAY_BUFFER, 0);

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onSurfaceCreated  **********");

        //check GL Version
        System.out.println("GL_Version is " + GLES31.glGetString(GLES31.GL_VERSION));
        System.out.println("GLSL_Version is " + GLES31.glGetString(GLES31.GL_SHADING_LANGUAGE_VERSION));
        System.out.println("GL_Extensions is " + GLES31.glGetString(GLES31.GL_EXTENSIONS));


//        System.out.println(triangleShader.getProgramID());


        GLES31.glUseProgram(triangleShader.getProgramID());
        System.out.println("programID is " + triangleShader.getProgramID());

        // get attribute ID
        positionID = GLES31.glGetAttribLocation(triangleShader.getProgramID(), "vPosition");
        System.out.println("positionID is " + positionID);
        colorID = GLES31.glGetAttribLocation(triangleShader.getProgramID(), "vColor");
        System.out.println("colorID is " + colorID);

        //get uniform ID
        modelID = GLES31.glGetUniformLocation(triangleShader.getProgramID(), "model");
        System.out.println("modelID is " + modelID);
        viewID = GLES31.glGetUniformLocation(triangleShader.getProgramID(), "view");
        System.out.println("viewID is " + viewID);
        projectID = GLES31.glGetUniformLocation(triangleShader.getProgramID(), "project");
        System.out.println("projectID is " + projectID);


        assignData();


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onSurfaceChanged  **********");
        System.out.println("width is " + width);
        System.out.println("height is " + height);

        gl.glViewport(0, 0, width, height);

        float ratio = (float) width / height;
        float[] project = new float[16];
        Matrix.perspectiveM(project, 0, 45, ratio, 0.1f, 100f);
        GLES31.glUniformMatrix4fv(projectID, 1,false, project, 0);


    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        System.out.println("**********  " + getClass().getSimpleName() + ".onDrawFrame  **********");


        GLES31.glClear(GLES31.GL_COLOR_BUFFER_BIT);
        GLES31.glBindVertexArray(arrayID.get(0));



        float[] model = new float[16];
        Matrix.setIdentityM(model, 0);
        Matrix.scaleM(model, 0, 1, 1, 1);
        Matrix.rotateM(model, 0, angle, 0, 1, 0);
        Matrix.translateM(model, 0, 0, 0, 0);
        GLES31.glUniformMatrix4fv(modelID, 1,false, model, 0);

        float[] view = new float[16];
        Matrix.setLookAtM(view, 0,
                0, 0, 5,
                0, 0, 0,
                0, 1, 0);
        GLES31.glUniformMatrix4fv(viewID, 1,false, view, 0);


        // Draw the triangle
        GLES31.glDrawArrays(GLES31.GL_TRIANGLES, 0, 3);

    }

}
